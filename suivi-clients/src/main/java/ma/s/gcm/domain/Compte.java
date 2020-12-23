package ma.s.gcm.domain;

import lombok.*;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Compte {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_COMPTE_SEQ")
    @SequenceGenerator(name = "ID_COMPTE_SEQ", sequenceName = "ID_COMPTE_SEQ")
    private Long id;

    private String banque;

    private String nCompte;

    @OneToMany(mappedBy = "compte")
    private Collection<Transaction> transactions;

    @ManyToOne
    private Entreprise entreprise;
}
