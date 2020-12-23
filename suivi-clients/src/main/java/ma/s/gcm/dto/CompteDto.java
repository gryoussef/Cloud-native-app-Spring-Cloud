package ma.s.gcm.dto;

import com.fasterxml.jackson.annotation.JsonView;
import lombok.*;
import ma.s.gcm.domain.Entreprise;
import ma.s.gcm.domain.Transaction;
import ma.s.gcm.dto.views.UserView;

import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import java.util.Collection;


@Data
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Builder
public class CompteDto {

    private Long id;
    private String banque;
    private String nCompte;
    private EntrepriseDto entreprise;

}
