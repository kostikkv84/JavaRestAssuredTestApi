package api.DataApi.dummyapi.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class PutUpdateUserReq {
    private String firstName;
    private String lastName;

    public PutUpdateUserReq() {
        super();
    }

    public PutUpdateUserReq(String firstName, String lastName) {
        this.firstName = firstName;
        this.lastName = lastName;
    }

}
