package api.DataReqresIn;

public class RegisterError {
    private String error;

    public RegisterError() {
        super();
    }

    public RegisterError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
