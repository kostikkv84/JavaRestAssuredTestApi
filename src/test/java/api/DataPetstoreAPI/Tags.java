package api.DataPetstoreAPI;

import lombok.*;


public class Tags {
    private Long id;
    private String name;

    public Tags() {super();
    }

    public Tags(Long id, String name) {
        this.id = id;
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public void setName(String name) {
        this.name = name;
    }
}

