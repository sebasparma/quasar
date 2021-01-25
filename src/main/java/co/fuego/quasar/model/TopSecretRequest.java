package co.fuego.quasar.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;
import java.util.stream.Collectors;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class TopSecretRequest {

    private List<Satellite> satellites;

    public double[][] getPositions() {
        List<Position> lsPositions = satellites.stream().map(Satellite::getPosition).collect(Collectors.toList());
        double[][] points = new double[lsPositions.size()][];
        for (int i = 0; i < lsPositions.size(); i++) {
            points[i] = lsPositions.get(i).getPoint();
        }
        return points;
    }

    public double[] getDistances() {
        List<Double> distancesD = satellites.stream().map(Satellite::getDistance).collect(Collectors.toList());
        return distancesD.stream().mapToDouble(d -> d).toArray();
    }

    public List<List<String>> getMessages() {
        return satellites.stream().map(Satellite::getMessage).collect(Collectors.toList());
    }
}
