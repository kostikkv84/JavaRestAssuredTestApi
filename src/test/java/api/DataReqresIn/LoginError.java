package api.DataReqresIn;

public class LoginError {
    private String error;

    public LoginError() {
        super();
    }

    public LoginError(String error) {
        this.error = error;
    }

    public String getError() {
        return error;
    }

    public void setError(String error) {
        this.error = error;
    }
}
