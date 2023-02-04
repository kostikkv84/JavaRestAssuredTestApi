package api.Zapovednik96;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class ForgetEmail {
    public String type;
    public ArrayList<String> value;

    public ForgetEmail(String type, ArrayList<String> value) {
        this.type = type;
        this.value = value;
    }

    public ForgetEmail() {
        super();
    }
}
