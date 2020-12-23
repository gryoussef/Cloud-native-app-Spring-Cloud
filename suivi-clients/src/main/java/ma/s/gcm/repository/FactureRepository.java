package ma.s.gcm.repository;

import ma.s.gcm.domain.Facture;
import org.springframework.data.jpa.repository.JpaRepository;


public interface FactureRepository extends JpaRepository<Facture,Long> {
}
