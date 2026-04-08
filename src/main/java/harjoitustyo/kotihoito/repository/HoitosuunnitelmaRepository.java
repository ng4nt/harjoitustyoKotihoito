package harjoitustyo.kotihoito.repository;

import harjoitustyo.kotihoito.model.Hoitosuunnitelma;
import org.springframework.data.jpa.repository.JpaRepository;

public interface HoitosuunnitelmaRepository extends JpaRepository<Hoitosuunnitelma, Integer> {

}