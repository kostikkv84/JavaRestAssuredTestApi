package api.DataApi.dummyapi.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CreateUserReq {
    private String firstName;
    private String lastName;
    private String email;

    public CreateUserReq() {
        super();
    }

    public CreateUserReq(String firstName, String lastName, String email) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
    }
}
