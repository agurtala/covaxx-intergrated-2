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

    @GetMapping("/practitioner/{practitionerId}")
    public @ResponseBody
    Practitioner getOne(
            @PathVariable String practitionerId)
    {

        Practitioner practitioner = this.PractitionerRepo.getById(practitionerId);

        if (practitioner == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no practitioner with this practitionerId");

        return practitioner;
    }

    @PostMapping("/practitioner")
    public @ResponseBody
    Practitioner createNew(@RequestBody Practitioner practitioner) {

        if (practitioner.practitionerId == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Practitioner must specify a practitionerId");

        Practitioner existingPractitioner = this.PractitionerRepo.getById(practitioner.practitionerId);
        if (existingPractitioner != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This practitionerId is already used");
        }

        this.PractitionerRepo.save(practitioner);

        return practitioner;
    }

    @PatchMapping("/practitioner/{practitionerId}")
    public @ResponseBody
    Practitioner updateExisting(@PathVariable String practitionerId, @RequestBody Practitioner changes) {

        Practitioner existingPractitioner = this.PractitionerRepo.getById(practitionerId);

        if(existingPractitioner == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This practitionerId does not exist");
        }

        if (changes.practitionerFname != null) {
            existingPractitioner.practitionerFname = changes.practitionerFname;
        }

        if (changes.practitionerLname != null) {
            existingPractitioner.practitionerLname = changes.practitionerLname;
        }

        this.PractitionerRepo.save(existingPractitioner);

        return existingPractitioner;


    }

}

/************************** Shino's Contribution *********************/