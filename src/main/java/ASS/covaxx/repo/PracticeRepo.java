/************************** Shino's Contribution *********************/
package ASS.covaxx.repo;

import ASS.covaxx.model.Practice;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;


@Repository
public class PracticeRepo {

    @Autowired
    private MongoTemplate mongo;
    public void save(Practice practice) {
        this.mongo.save(practice);
    }

    public Practice getById(String practiceId) {
        return this.mongo.findById(practiceId, Practice.class);

    }


    public Collection<Practice> getAll() {
        return this.mongo.findAll(Practice.class);

    }

    public Collection<Practice> find(String practiceName) {

        Query query = new Query();

        if (practiceName != null)
            query.addCriteria(Criteria.where("practiceName").is(practiceName));

        return this.mongo.find(query, Practice.class);
    }

}

/************************** Shino's Contribution *********************/
