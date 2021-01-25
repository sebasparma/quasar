package co.fuego.quasar.model;

import co.fuego.quasar.entity.Satellites;
import com.fasterxml.jackson.annotation.JsonIgnore;
import io.swagger.annotations.ApiModelProperty;
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

    @ApiModelProperty(notes = "Posición del satélite",name="position",required=false)
    private Position position;
    @ApiModelProperty(notes = "Nombre del Satélite",name="name",required=false)
    private String name;
    @ApiModelProperty(notes = "Mensaje recibido en el satélite",name="message",required=false)
    private List<String> message;
    @ApiModelProperty(notes = "Distancia a la fuente del mensaje",name="distance",required=false)
    private double distance;

    @JsonIgnore
    public Satellite(Satellites entity) {
        this.position = new Position(entity.getX(), entity.getY());
        this.name = entity.getName();
        this.message = Arrays.asList(entity.getMessage() != null ? entity.getMessage().split(",", -1) : new String[]{});
        this.distance = entity.getDistance() != null ? entity.getDistance() : 0;
    }

}
