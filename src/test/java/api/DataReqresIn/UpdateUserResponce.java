package api.DataReqresIn;

public class UpdateUserResponce {
    private String name;
    private String job;
    private String updatedAt;

    public UpdateUserResponce() {
        super();
    }

    public UpdateUserResponce(String name, String job, String updatedAt) {
        this.name = name;
        this.job = job;
        this.updatedAt = updatedAt;
    }

    public String getName() {
        return name;
    }

    public String getJob() {
        return job;
    }

    public String getUpdatedAt() {
        return updatedAt;
    }

}
