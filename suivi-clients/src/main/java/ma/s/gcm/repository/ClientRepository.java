package ma.s.gcm.repository;

import ma.s.gcm.domain.Client;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface ClientRepository extends JpaRepository<Client,Long> {
    List<Client> findAllByEntreprise_Id(Long entrepriseId);
}
