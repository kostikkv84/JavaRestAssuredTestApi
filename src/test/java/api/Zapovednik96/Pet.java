package api.Zapovednik96;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class Pet {
    @JsonProperty("ID")
    public int iD;
    @JsonProperty("OWNER")
    public String oWNER;
    @JsonProperty("ANIMAL")
    public Animal aNIMAL;
    @JsonProperty("BIRTHDAY")
    public String bIRTHDAY;
    @JsonProperty("BREED")
    public String bREED;
    @JsonProperty("ANIMAL_NAME")
    public String aNIMAL_NAME;
    @JsonProperty("NAME")
    public String nAME;

    public Pet() {
        super();
    }

    public Pet(int iD, String oWNER, Animal aNIMAL, String bIRTHDAY, String bREED, String aNIMAL_NAME, String nAME) {
        this.iD = iD;
        this.oWNER = oWNER;
        this.aNIMAL = aNIMAL;
        this.bIRTHDAY = bIRTHDAY;
        this.bREED = bREED;
        this.aNIMAL_NAME = aNIMAL_NAME;
        this.nAME = nAME;
    }
}
