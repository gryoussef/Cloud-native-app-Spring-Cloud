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
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_CLIENT_SEQ")
    @SequenceGenerator(name = "ID_CLIENT_SEQ", sequenceName = "ID_CLIENT_SEQ")
    private Long id;

    @NotNull
    private String nom;

    @ManyToOne
    private Client groupe;


    @ManyToOne
    private Entreprise entreprise;

    @OneToMany(mappedBy = "client")
    private Collection<Facture> factures;

    @OneToMany(mappedBy = "client")
    private Collection<Encaissement> encaissements;
}
