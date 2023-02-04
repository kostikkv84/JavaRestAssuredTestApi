package api.DataApi.dummyapi.io;

import lombok.Getter;

@Getter
public class GetUserError {
    private String error;

    public GetUserError() {
        super();
    }

    public GetUserError(String error) {
        this.error = error;
    }
}
