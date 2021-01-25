package co.fuego.quasar.model;

import co.fuego.quasar.entity.Satellites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Arrays;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Satellite {

    private Position position;
    private String name;
    private List<String> message;
    private double distance;

    @JsonIgnore
    public Satellite(Satellites entity) {
        this.position = new Position(entity.getX(), entity.getY());
        this.name = entity.getName();
        this.message = Arrays.asList(entity.getMessage() != null ? entity.getMessage().split(",", -1) : new String[]{});
        this.distance = entity.getDistance() != null ? entity.getDistance() : 0;
    }

}
