package harjoitustyo.kotihoito.repository;

import harjoitustyo.kotihoito.model.Potilas;
import harjoitustyo.kotihoito.model.Hoitosuunnitelma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.Optional;

public interface PotilasRepository extends JpaRepository<Potilas, Integer> {

    // Hae potilas hetun perusteella
    Optional<Potilas> findByHetu(String hetu);

    // Hae kaikki potilaat tietylle hoitosuunnitelmalle
    List<Potilas> findByHoitosuunnitelma(Hoitosuunnitelma hoitosuunnitelma);

}