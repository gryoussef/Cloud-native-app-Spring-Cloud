package ma.s.gcm.domain;

import lombok.*;
import ma.s.gcm.type.StatutFacture;
import ma.s.gcm.type.TypeFacture;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.sql.Date;
import java.util.Collection;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_FACTURE_SEQ")
    @SequenceGenerator(name = "ID_FACTURE_SEQ", sequenceName = "ID_FACTURE_SEQ")
    private Long id;

    @Enumerated(EnumType.STRING)
    private TypeFacture type; // Avoir ou Facture

    @NotNull
    private String nFacture; // numéro incremental
    private Date date;

    @ManyToOne
    private Client client;

    private Double montantHt; // montantant Hors Taxe
    private Double pcRemise; // % remise
    private String devise;
    private Double tauxChange; // taux change à la date de facturation

    @Enumerated(EnumType.STRING)
    private StatutFacture statut; // Par défaut non payée

    @OneToMany(mappedBy = "facture")
    private Collection<Rapprochement> rapprochements;
}
