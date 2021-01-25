package co.fuego.quasar.repository;

import co.fuego.quasar.entity.Satellites;
import org.springframework.data.jpa.repository.JpaRepository;

public interface SatellitesRepository extends JpaRepository<Satellites, String> {
}
