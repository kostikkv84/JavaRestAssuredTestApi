package api.DataPetstoreAPI;

import lombok.Getter;
import lombok.Setter;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
public class UpdatePetResponce {
    private int id;
    private String name;
    private List<String> photoUrls;
    private List<String> tags;
    private String status;

    public UpdatePetResponce(int id, String name, List<String> photoUrls, List<String> tags, String status) {
        this.id = id;
        this.name = name;
        this.photoUrls = photoUrls;
        this.tags = tags;
        this.status = status;
    }

    public UpdatePetResponce() {super();
    }

}
