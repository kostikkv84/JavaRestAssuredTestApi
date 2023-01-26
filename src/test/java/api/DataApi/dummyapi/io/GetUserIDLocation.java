package api.DataApi.dummyapi.io;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class GetUserIDLocation {
    private String street;
    private String city;
    private String state;
    private String country;
    private String timezone;

    public GetUserIDLocation() {super();
    }

    public GetUserIDLocation(String street, String city, String state, String country, String timezone) {
        this.street = street;
        this.city = city;
        this.state = state;
        this.country = country;
        this.timezone = timezone;
    }


}
