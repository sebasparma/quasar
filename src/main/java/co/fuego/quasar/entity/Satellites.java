package co.fuego.quasar.entity;

import lombok.Getter;
import lombok.Setter;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@Table(name = "SATELLITES")
@XmlRootElement
@Getter
@Setter
public class Satellites {

    @Id
    private String name;
    private Double distance;
    private Double x;
    private Double y;
    private String message;

}
