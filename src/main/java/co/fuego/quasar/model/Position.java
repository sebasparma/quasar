package co.fuego.quasar.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
public class Position {

    private double x;
    private double y;

    @JsonIgnore
    public double[] getPoint() {
        return new double[]{x, y};
    }

}
