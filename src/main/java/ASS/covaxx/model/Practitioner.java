/************************** Shino's Contribution *********************/
package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Practitioner {

    @Id
    public String practitionerID;

    public String practitionerName;



}
/************************** Shino's Contribution *********************/