package harjoitustyo.kotihoito.repository;

import harjoitustyo.kotihoito.model.Hoitopaivakirja;
import harjoitustyo.kotihoito.model.Potilas;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface HoitopaivakirjaRepository extends JpaRepository<Hoitopaivakirja, Integer> {
    
    // Hae kaikki paivakirjamerkinnat tietylle potilaalle
    List<Hoitopaivakirja> findByPotilas(Potilas potilas);
   
}