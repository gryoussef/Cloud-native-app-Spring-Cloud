package ma.s.gcm.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Entreprise {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_ENTREPRISE_SEQ")
    @SequenceGenerator(name = "ID_ENTREPRISE_SEQ", sequenceName = "ID_ENTREPRISE_SEQ")
    private Long id;
    @NotNull
    private String nom;
    @NotNull
    private String pattente;

    @OneToMany(mappedBy = "entreprise")
    private Collection<Client> clients;

    @OneToMany(mappedBy = "entreprise")
    private Collection<Compte> comptes;


}
