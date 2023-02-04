package api.Zapovednik96;

import com.fasterxml.jackson.annotation.JsonProperty;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SessAuth {
    @JsonProperty("AUTHORIZED")
    private String aUTHORIZED;
    @JsonProperty("USER_ID")
    private String uSER_ID;
    @JsonProperty("LOGIN")
    private String lOGIN;
    @JsonProperty("EMAIL")
    private String eMAIL;
    @JsonProperty("TITLE")
    private Object tITLE;
    @JsonProperty("NAME")
    private String nAME;
    @JsonProperty("FIRST_NAME")
    private String fIRST_NAME;
    @JsonProperty("SECOND_NAME")
    private String sECOND_NAME;
    @JsonProperty("LAST_NAME")
    private String lAST_NAME;
    @JsonProperty("PERSONAL_PHOTO")
    private Object pERSONAL_PHOTO;
    @JsonProperty("PERSONAL_GENDER")
    private String pERSONAL_GENDER;
    @JsonProperty("PERSONAL_WWW")
    private Object pERSONAL_WWW;
    @JsonProperty("EXTERNAL_AUTH_ID")
    private Object eXTERNAL_AUTH_ID;
    @JsonProperty("XML_ID")
    private Object xML_ID;
    @JsonProperty("ADMIN")
    private boolean aDMIN;
    @JsonProperty("AUTO_TIME_ZONE")
    private String aUTO_TIME_ZONE;
    @JsonProperty("TIME_ZONE")
    private Object tIME_ZONE;
    @JsonProperty("TIME_ZONE_OFFSET")
    private Object tIME_ZONE_OFFSET;
    @JsonProperty("APPLICATION_ID")
    private Object aPPLICATION_ID;
    @JsonProperty("BX_USER_ID")
    private Object bX_USER_ID;
    @JsonProperty("SESSION_HASH")
    private String sESSION_HASH;
    @JsonProperty("CONTROLLER_ADMIN")
    private boolean cONTROLLER_ADMIN;
    @JsonProperty("STORED_AUTH_ID")
    private String sTORED_AUTH_ID;
    @JsonProperty("PERSONAL_PHONE")
    private String pERSONAL_PHONE;

    public SessAuth() {
        super();
    }

    public SessAuth(String aUTHORIZED, String uSER_ID, String lOGIN, String eMAIL, Object tITLE, String nAME, String fIRST_NAME, String sECOND_NAME, String lAST_NAME, Object pERSONAL_PHOTO, String pERSONAL_GENDER, Object pERSONAL_WWW, Object eXTERNAL_AUTH_ID, Object xML_ID, boolean aDMIN, String aUTO_TIME_ZONE, Object tIME_ZONE, Object tIME_ZONE_OFFSET, Object aPPLICATION_ID, Object bX_USER_ID, String sESSION_HASH, boolean cONTROLLER_ADMIN, String sTORED_AUTH_ID, String pERSONAL_PHONE) {
        this.aUTHORIZED = aUTHORIZED;
        this.uSER_ID = uSER_ID;
        this.lOGIN = lOGIN;
        this.eMAIL = eMAIL;
        this.tITLE = tITLE;
        this.nAME = nAME;
        this.fIRST_NAME = fIRST_NAME;
        this.sECOND_NAME = sECOND_NAME;
        this.lAST_NAME = lAST_NAME;
        this.pERSONAL_PHOTO = pERSONAL_PHOTO;
        this.pERSONAL_GENDER = pERSONAL_GENDER;
        this.pERSONAL_WWW = pERSONAL_WWW;
        this.eXTERNAL_AUTH_ID = eXTERNAL_AUTH_ID;
        this.xML_ID = xML_ID;
        this.aDMIN = aDMIN;
        this.aUTO_TIME_ZONE = aUTO_TIME_ZONE;
        this.tIME_ZONE = tIME_ZONE;
        this.tIME_ZONE_OFFSET = tIME_ZONE_OFFSET;
        this.aPPLICATION_ID = aPPLICATION_ID;
        this.bX_USER_ID = bX_USER_ID;
        this.sESSION_HASH = sESSION_HASH;
        this.cONTROLLER_ADMIN = cONTROLLER_ADMIN;
        this.sTORED_AUTH_ID = sTORED_AUTH_ID;
        this.pERSONAL_PHONE = pERSONAL_PHONE;
    }
}
