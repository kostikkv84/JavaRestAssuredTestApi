package api.Zapovednik96;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Animal {
    @JsonProperty("ID")
    public int iD;
    @JsonProperty("PROPERTY_ID")
    public int pROPERTY_ID;
    @JsonProperty("VALUE")
    public String vALUE;
    @JsonProperty("DEF")
    public boolean dEF;
    @JsonProperty("SORT")
    public int sORT;
    @JsonProperty("XML_ID")
    public String xML_ID;
    @JsonProperty("TMP_ID")
    public String tMP_ID;

    public Animal() {
    }

    public Animal(int iD, int pROPERTY_ID, String vALUE, boolean dEF, int sORT, String xML_ID, String tMP_ID) {
        this.iD = iD;
        this.pROPERTY_ID = pROPERTY_ID;
        this.vALUE = vALUE;
        this.dEF = dEF;
        this.sORT = sORT;
        this.xML_ID = xML_ID;
        this.tMP_ID = tMP_ID;
    }
}
