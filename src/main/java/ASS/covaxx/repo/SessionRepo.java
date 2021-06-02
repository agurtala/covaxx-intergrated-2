/************************** Shino's Contribution *********************/

package ASS.covaxx.repo;

import ASS.covaxx.model.Session;
import org.bson.types.ObjectId;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public class SessionRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Session session) {this.mongo.save(session); }

    public Session getById(String sessionId) { return mongo.findById(sessionId, Session.class) ; }

    public Collection<Session> find(String practiceID, String practitionerID, String patientID) {

        Query query = new Query() ;

        if (practiceID != null)
            query.addCriteria(Criteria.where("practiceID").is(practiceID));


        if (practitionerID != null)
            query.addCriteria(Criteria.where("practitionerID").is(practitionerID));

        if (patientID != null)
            query.addCriteria(Criteria.where("patientID").is(patientID));

        return this.mongo.find(query, Session.class) ;
    }

}


/************************** Shino's Contribution *********************/