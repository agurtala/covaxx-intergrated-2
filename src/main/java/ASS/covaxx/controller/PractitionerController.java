/************************** Shino's Contribution *********************/
package ASS.covaxx.controller;

import ASS.covaxx.model.Practitioner;
import ASS.covaxx.repo.PractitionerRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class PractitionerController {

    @Autowired
    private PractitionerRepo PractitionerRepo;

    @GetMapping("/practitioner")
    public @ResponseBody Collection<Practitioner> getAll(){

        return this.PractitionerRepo.getAll();
    }

    @GetMapping("/practitioner/{practitionerID}")
    public @ResponseBody
    Practitioner getOne(
            @PathVariable String practitionerID)
    {

        Practitioner practitioner = this.PractitionerRepo.getById(practitionerID);

        if (practitioner == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no practitioner with this practitionerId");

        return practitioner;
    }

    @PostMapping("/practitioner")
    public @ResponseBody
    Practitioner createNew(@RequestBody Practitioner practitioner) {

        if (practitioner.practitionerID == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Practitioner must specify a practitionerId");

        Practitioner existingPractitioner = this.PractitionerRepo.getById(practitioner.practitionerID);
        if (existingPractitioner != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This practitionerId is already used");
        }

        this.PractitionerRepo.save(practitioner);

        return practitioner;
    }

    @PatchMapping("/practitioner/{practitionerID}")
    public @ResponseBody
    Practitioner updateExisting(@PathVariable String practitionerID, @RequestBody Practitioner changes) {

        Practitioner existingPractitioner = this.PractitionerRepo.getById(practitionerID);

        if(existingPractitioner == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This practitionerId does not exist");
        }

        if (changes.practitionerName != null) {
            existingPractitioner.practitionerName = changes.practitionerName;
        }

        this.PractitionerRepo.save(existingPractitioner);

        return existingPractitioner;


    }

}

/************************** Shino's Contribution *********************/