package ma.s.gcm.domain;

import lombok.*;
import ma.s.gcm.type.ModeEncaissement;
import ma.s.gcm.type.StatutEncaissement;
import ma.s.gcm.type.TypeEncaissement;

import javax.persistence.*;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Encaissement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_ENCAISSEMENT_SEQ")
    @SequenceGenerator(name = "ID_ENCAISSEMENT_SEQ", sequenceName = "ID_ENCAISSEMENT_SEQ")
    private Long id;

    @ManyToOne
    private Client client;

    @Temporal(TemporalType.DATE)
    private Date dateReception;
    @Temporal(TemporalType.DATE)
    private Date dateReleveBancaire;

    private BigDecimal montant;
    private String devise;

    @Enumerated(EnumType.STRING)
    private TypeEncaissement type;

    @Enumerated(EnumType.STRING)
    private ModeEncaissement mode;

    @Enumerated(EnumType.STRING)
    private StatutEncaissement statut;

    private String banque;

    private String nCheque;
    @Temporal(TemporalType.DATE)
    private Date dateCheque;
    private String cheqAuNomDe;

    private String nEffet;
    @Temporal(TemporalType.DATE)
    private Date dateEffet;

    private String nVirement;
    private String nCompteVirement;

    private String motif;

    @ManyToOne
    private Transaction transaction;

    @OneToMany(mappedBy = "encaissement")
    private Collection<Rapprochement> rapprochements;
}
