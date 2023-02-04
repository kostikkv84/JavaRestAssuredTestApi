package api.Zapovednik96;

import lombok.Getter;
import lombok.Setter;

import java.util.ArrayList;

@Getter
@Setter
public class Value {
    public ArrayList<String> pets;

    public Value() {
        super();
    }

    public Value(ArrayList<String> pets) {
        this.pets = pets;
    }
}
