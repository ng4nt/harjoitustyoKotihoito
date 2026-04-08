package harjoitustyo.kotihoito.repository;

import harjoitustyo.kotihoito.model.Hoitaja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface HoitajaRepository extends JpaRepository<Hoitaja, Integer> {

    // Hae sähköpostin perusteella tietty hoitaja
    Optional<Hoitaja> findBySahkoposti(String sahkoposti);

}