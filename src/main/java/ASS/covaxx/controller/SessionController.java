/************************** Shino's Contribution *********************/
package ASS.covaxx.controller;
import ASS.covaxx.model.Practice;
import ASS.covaxx.model.Session;
import ASS.covaxx.repo.PracticeRepo;
import ASS.covaxx.repo.PractitionerRepo;
import ASS.covaxx.repo.SessionRepo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;

@Controller
public class SessionController {

    @Autowired
    private SessionRepo sessions;

    @Autowired
    private PracticeRepo practices;

    @Autowired
    private PractitionerRepo practitioners;

    @GetMapping("/sessions")
    private @ResponseBody
    Collection<Session> getSessions(
            @PathVariable String practiceID
    ) {
        Practice practice = this.practices.getById(practiceID);

        if (practice == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "There is no practice with this practiceId");

        return sessions.find(practiceID, null, null);
    }


    @GetMapping("/sessions/{sessionID}")
    public @ResponseBody
    Session getOne(
            @PathVariable String sessionID)
    {

        Session session = this.sessions.getById(sessionID);

        if (session == null)
            throw new ResponseStatusException(HttpStatus.NOT_FOUND,"There is no sessions with this sessionId");

        return session;
    }

    @PatchMapping("/sessions/{sessionID}")
    public @ResponseBody
    Session updateExisting(@PathVariable String sessionID, @RequestBody Session changes) {

        Session existingSession = this.sessions.getById(sessionID);

        if (existingSession == null) {
            throw new ResponseStatusException(HttpStatus.NOT_FOUND, "This sessionId does not exist");
        }

        if (changes.booking != null) {
            existingSession.booking = changes.booking;
        }

        if (changes.sessionDate != null) {
            existingSession.sessionDate = changes.sessionDate;
        }

        if (changes.sessionTime != null) {
            existingSession.sessionTime = changes.sessionTime;
        }
        if (changes.practiceID != null) {
            existingSession.practiceID = changes.practiceID;
        }
        if (changes.practitionerID != null) {
            existingSession.practitionerID = changes.practitionerID;
        }
        if (changes.patientID != null) {
            existingSession.patientID = changes.patientID;
        }

        this.sessions.save(existingSession);

        return existingSession;


    }





    @PostMapping("/sessions")
    private @ResponseBody
    Session createSession(
//            @PathVariable String practiceID,
            @RequestBody Session session

    ) {

        if (session.sessionID != null)
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "New sessions should not specify any ID");

//        session.practiceID = practiceID;

        validate(session);

        this.sessions.save(session);
        return session;


    }


    private void validate(Session session) {
        if (session.practiceID == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No practice ID specified");

        }
        if (practices.getById(session.practiceID) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no practice with this ID");


        }
        if (session.practitionerID == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "No practitioner ID specified");

        }
        if (practitioners.getById(session.practitionerID) == null) {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST, "There is no practitioner with this ID");


        }

    }

}
/************************** Shino's Contribution *********************/


