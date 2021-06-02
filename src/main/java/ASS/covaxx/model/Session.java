/************************** Shino's Contribution *********************/
package ASS.covaxx.model;

import org.bson.types.ObjectId;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.util.HashMap;
import java.util.Map;

@Document
public class Session {

    @Id
    public ObjectId sessionId;

    public Map<String, String> booking = new HashMap<>();

    public String sessionDate;
    public String sessionTime;

    public String practiceID;
    public String practitionerID;
    public String patientID;


}
/************************** Shino's Contribution *********************/