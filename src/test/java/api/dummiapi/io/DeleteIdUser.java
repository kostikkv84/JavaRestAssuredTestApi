package api.dummiapi.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class DeleteIdUser {
    private String id;

    public DeleteIdUser() { super();
    }

    public DeleteIdUser(String id) {
        this.id = id;
    }
}
