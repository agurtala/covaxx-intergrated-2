/************************** Shino's Contribution *********************/

package ASS.covaxx.repo;

import ASS.covaxx.model.Practitioner;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;
import java.util.HashMap;

@Repository
public class PractitionerRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Practitioner practitioner) { mongo.save(practitioner); }

    public Practitioner getById(String practitionerId) { return mongo.findById(practitionerId, Practitioner.class); }

    public Collection<Practitioner> getAll() { return mongo.findAll(Practitioner.class); }
}

/************************** Shino's Contribution *********************/