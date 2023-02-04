package api.DataPetstoreAPI;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class UpdNamePetClass {
    private Integer id;
    private String name;
    private String status;

    public UpdNamePetClass(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public UpdNamePetClass() {
        super();
    }
}
