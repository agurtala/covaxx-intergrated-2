/************************** Alfiyyah's Contribution *********************/

package ASS.covaxx.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
public class Certificate {

    @Id
    public ObjectId certID;

    public Map<String, String> files = new HashMap<>();

    public String certDate;
    public String certTime;

    public String patientID;
    public String vacID;


}

/************************** Alfiyyah's Contribution *********************/