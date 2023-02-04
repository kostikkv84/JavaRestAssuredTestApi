package api.DataApi.dummyapi.io;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PutUpdateUserResp {
    private String id;
    private String firstName;
    private String lastName;
    private String email;
    private String registerDate;
    private String updatedDate;

    public PutUpdateUserResp() {
        super();
    }

    public PutUpdateUserResp(String id, String firstName, String lastName, String email, String registerDate, String updatedDate) {
        this.id = id;
        this.firstName = firstName;
        this.lastName = lastName;
        this.email = email;
        this.registerDate = registerDate;
        this.updatedDate = updatedDate;
    }
}
