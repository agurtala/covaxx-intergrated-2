/************************** Alfiyyah's Contribution *********************/

package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Vaccine {

    @Id
    public String vacID;

    @Indexed
    public String vacType;
    public String vacResult;
    public String facility;

}

/************************** Alfiyyah's Contribution *********************/

