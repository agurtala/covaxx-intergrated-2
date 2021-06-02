/************************** Shino's Contribution *********************/
package ASS.covaxx.model;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Document
public class Practice {

    @Id
    public String practiceId;

    @Indexed
    public String practiceName;

}

/************************** Shino's Contribution *********************/
