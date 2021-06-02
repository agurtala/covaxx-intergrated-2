/************************** Shino's Contribution *********************/

package ASS.covaxx.controller;

import ASS.covaxx.model.Patient;
import ASS.covaxx.model.Practice;
import ASS.covaxx.repo.PatientRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class PatientController {

    @Autowired
    private PatientRepo PatientRepo;

    @GetMapping("/patients")
    public @ResponseBody Collection<Patient> getAll(
            @RequestParam(required = false) String patientName) {

        return this.PatientRepo.find(patientName);
    }

    @GetMapping("/patients/{patientsID}")
    public @ResponseBody
    Patient getOne(
            @PathVariable String patientID) {
        Patient patient = this.PatientRepo.getById(patientID);

        if (patient == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no patient with this patientID");

        return patient;

    }

    @PostMapping("/patients")
    public @ResponseBody
    Patient createNew(@RequestBody Patient patient) {

        if (patient.patientID == null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "Patient must specify a patientID");

        Patient existingPatient = this.PatientRepo.getById(patient.patientID);
        if (existingPatient != null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "This patientID is already used");
        }

        this.PatientRepo.save(patient);

        return patient;

    }

    @PatchMapping("/patients/{patientID}")
    public @ResponseBody
    Patient updateExisting(@PathVariable String patientID, @RequestBody Patient changes) {

        Patient existingPatient = this.PatientRepo.getById(patientID);

        if (existingPatient == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This patientID does not exist");
        }

        if (changes.patientName != null)
            existingPatient.patientName = changes.patientName;

        this.PatientRepo.save(existingPatient);

        return existingPatient;

    }
}

/************************** Shino's Contribution *********************/