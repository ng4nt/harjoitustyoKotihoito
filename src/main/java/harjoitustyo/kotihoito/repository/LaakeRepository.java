package harjoitustyo.kotihoito.repository;

import harjoitustyo.kotihoito.model.Laake;
import harjoitustyo.kotihoito.model.Hoitosuunnitelma;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface LaakeRepository extends JpaRepository<Laake, Integer> {

    // Hae kaikki lääkkeet tietylle hoitosuunnitelmalle
    List<Laake> findByHoitosuunnitelma(Hoitosuunnitelma hoitosuunnitelma);
}