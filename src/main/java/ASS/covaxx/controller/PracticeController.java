/************************** Shino's Contribution *********************/

package ASS.covaxx.controller;

import ASS.covaxx.model.Practice;
import ASS.covaxx.repo.PracticeRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class PracticeController {

    @Autowired
    private PracticeRepo PracticeRepo;

    @GetMapping("/practices")
    public @ResponseBody Collection<Practice> getAll(
            @RequestParam(required = false) String practiceName){

        return this.PracticeRepo.find(practiceName);
    }

    @GetMapping("/practices/{practiceId}")
   public @ResponseBody
    Practice getOne(
           @PathVariable String practiceId)
    {

        Practice practice = this.PracticeRepo.getById(practiceId);

        if (practice == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no practice with this practiceId");

            return practice;
   }

   @PostMapping("/practices")
   public @ResponseBody
   Practice createNew(@RequestBody Practice practice) {

       if (practice.practiceId == null)
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Practice must specify a practice_Id");

       Practice existingPractice = this.PracticeRepo.getById(practice.practiceId);
       if (existingPractice != null) {
           throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This practice_Id is already used");
       }

        this.PracticeRepo.save(practice);

        return practice;
   }

   @PatchMapping("/practices/{practiceId}")
   public @ResponseBody
   Practice updateExisting(@PathVariable String practiceId, @RequestBody Practice changes) {

        Practice existingPractice = this.PracticeRepo.getById(practiceId);

        if(existingPractice == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This practice_id does not exist");
        }

        if (changes.practiceName != null)
            existingPractice.practiceName = changes.practiceName;

        this.PracticeRepo.save(existingPractice);

        return existingPractice;


   }

}

/************************** Shino's Contribution *********************/