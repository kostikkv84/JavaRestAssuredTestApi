package api.dummiapi.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserList {
    private String id;
    private String title;
    private String firstName;
    private String lastName;
    private String picture;

    public GetUserList() {super();
    }

    public GetUserList(String id, String title, String firstName, String lastName, String picture) {
        this.id = id;
        this.title = title;
        this.firstName = firstName;
        this.lastName = lastName;
        this.picture = picture;
    }



}
