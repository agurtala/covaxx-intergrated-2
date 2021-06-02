/************************** Alfiyyah's Contribution *********************/

package ASS.covaxx.controller;

import ASS.covaxx.model.Vaccine;
import ASS.covaxx.repo.VaccineRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
@CrossOrigin
public class VaccineController {

    @Autowired
    private VaccineRepo VaccineRepo;

    @GetMapping("/vaccine")
    public @ResponseBody Collection<Vaccine> getAll(
            @RequestParam(required = false) String vacType){

        return this.VaccineRepo.find(vacType);
    }

    @GetMapping("/vaccine/{vacID}")
    public @ResponseBody
    Vaccine getOne(
            @PathVariable String vacID)
    {

        Vaccine vaccine = this.VaccineRepo.getById(vacID);

        if (vaccine == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no vaccine with this vacID");

        return vaccine;
    }

    @PostMapping("/vaccine")
    public @ResponseBody
    Vaccine createNew(@RequestBody Vaccine vaccine) {

        if (vaccine.vacID == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Vaccine must specify a vacID");

        Vaccine existingVaccine = this.VaccineRepo.getById(vaccine.vacID);
        if (existingVaccine != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This vacID is already used");
        }

        this.VaccineRepo.save(vaccine);

        return vaccine;
    }

    @PatchMapping("/vaccine/{vaccineID}")
    public @ResponseBody
    Vaccine updateExisting(@PathVariable String vacID, @RequestBody Vaccine changes) {

        Vaccine existingVaccine = this.VaccineRepo.getById(vacID);

        if(existingVaccine == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This vacID does not exist");
        }

        if (changes.vacType != null)
            existingVaccine.vacType = changes.vacType;

        if (changes.vacResult != null)
            existingVaccine.vacResult = changes.vacResult;

        if (changes.facility != null)
            existingVaccine.facility = changes.facility;

        this.VaccineRepo.save(existingVaccine);

        return existingVaccine;


    }

}

/************************** Alfiyyah's Contribution *********************/