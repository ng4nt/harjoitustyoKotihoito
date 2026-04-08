package harjoitustyo.kotihoito.repository;

import harjoitustyo.kotihoito.model.Potilaslista;
import harjoitustyo.kotihoito.model.Hoitaja;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface PotilaslistaRepository extends JpaRepository<Potilaslista, Integer> {

    // Hae kaikki potilaat tietylle hoitajalle
    List<Potilaslista> findByHoitaja(Hoitaja hoitaja);

}