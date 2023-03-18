package api.PetShopUsers;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UserAddResponce {
    private String id;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private String password;
    private String phone;
    private Integer userStatus;

    public UserAddResponce() { super();
    }

    public UserAddResponce(String id, String username, String firstName, String lastName, String email, String password, String phone, Integer userStatus) {
        this.id = id;
        this.username = username;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.password = password;
        this.phone = phone;
        this.userStatus = userStatus;
    }
}
