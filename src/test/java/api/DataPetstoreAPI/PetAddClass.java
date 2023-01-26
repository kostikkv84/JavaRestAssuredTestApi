package api.DataPetstoreAPI;

import lombok.Getter;
import lombok.Setter;

public class PetAddClass {

        private Integer id;
        private String name;
        private String status;

    public PetAddClass(Integer id, String name, String status) {
        this.id = id;
        this.name = name;
        this.status = status;
    }

    public PetAddClass() {super();
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public String getStatus() {
        return status;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
