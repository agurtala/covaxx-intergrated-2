/************************** Alfiyyah's Contribution *********************/

package ASS.covaxx.repo;

import ASS.covaxx.model.Certificate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;
import java.util.Collection;

@Repository
public class CertificateRepo {

    @Autowired
    private MongoTemplate mongo;

    public void save(Certificate certificate) {this.mongo.save(certificate); }

    public Certificate getById(String certID) { return mongo.findById(certID, Certificate.class) ; }

    public Collection<Certificate> find(String patientID, String vacID) {

        Query query = new Query() ;

        if (patientID != null)
            query.addCriteria(Criteria.where("patientID").is(patientID));


        if (vacID != null)
            query.addCriteria(Criteria.where("vacID").is(vacID));

        return this.mongo.find(query, Certificate.class) ;
    }

    public Collection<Certificate> getAll() {return mongo.findAll(Certificate.class);}
}


/************************** Alfiyyah's Contribution *********************/