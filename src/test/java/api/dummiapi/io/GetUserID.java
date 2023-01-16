package api.dummiapi.io;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class GetUserID {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;
    private String gender;
    private String email;
    private String dateOfBirth;
    private String phone;
    private GetUserIDLocation location;
    private String registerDate;
    private String updatedDate;

    public GetUserID() {super();
    }

    public GetUserID(String id, String title, String firstName, String lastName, String picture, String gender, String email, String dateOfBirth, String phone, GetUserIDLocation location, String registerDate, String updatedDate) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
        this.gender = gender;
        this.email = email;
        this.dateOfBirth = dateOfBirth;
        this.phone = phone;
        this.location = location;
        this.registerDate = registerDate;
        this.updatedDate = updatedDate;
    }


}
