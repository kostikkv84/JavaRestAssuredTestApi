package api.PetShopUsers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAdd {
    private Integer code;
    private String type;
    private String message;

    public UserAdd() {
        super();
    }


    public UserAdd(Integer code, String type, String message) {
        this.code = code;
        this.type = type;
        this.message = message;
    }
}
