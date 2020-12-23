package ma.s.gcm.repository;

import ma.s.gcm.domain.Compte;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;


public interface CompteRepository extends JpaRepository<Compte,Long> {
    List<Compte> findAllByEntreprise_Id(Long entrepriseId);
}
