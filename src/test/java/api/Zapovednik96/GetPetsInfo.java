package api.Zapovednik96;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetPetsInfo {

    private String type;
    private Value value;

    public GetPetsInfo() {
        super();
    }

    public GetPetsInfo(String type, Value value) {
        this.type = type;
        this.value = value;
    }
}
