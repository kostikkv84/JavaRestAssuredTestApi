package api.DataPetstoreAPI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PetNotFoundClass {
    private Integer code;
    private String type;
    private String message;

    public PetNotFoundClass() {super();
    }

    public PetNotFoundClass(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
