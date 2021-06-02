/************************** Alfiyyah's Contribution *********************/

package ASS.covaxx.repo;

import ASS.covaxx.model.Vaccine;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public class VaccineRepo {

    @Autowired
    private MongoTemplate mongo;
    public void save(Vaccine vaccine) {
        this.mongo.save(vaccine);
    }

    public Vaccine getById(String vacID) {
        return this.mongo.findById(vacID, Vaccine.class);

    }


    public Collection<Vaccine> getAll() {
        return this.mongo.findAll(Vaccine.class);

    }

    public Collection<Vaccine> find(String vacType) {

        Query query = new Query();

        if (vacType != null)
            query.addCriteria(Criteria.where("vacType").is(vacType));

        return this.mongo.find(query, Vaccine.class);
    }

}

/************************** Alfiyyah's Contribution *********************/
