/************************** Shino's Contribution *********************/
package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Practitioner {

    @Id
    public String practitionerId;

    public String practitionerFname;
    public String practitionerLname;


}
/************************** Shino's Contribution *********************/