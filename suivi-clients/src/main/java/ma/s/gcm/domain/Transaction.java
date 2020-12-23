package ma.s.gcm.domain;

import lombok.*;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Collection;
import java.util.Date;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Transaction {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_TRANSACTION_SEQ")
    @SequenceGenerator(name = "ID_TRANSACTION_SEQ", sequenceName = "ID_TRANSACTION_SEQo")
    private Long id;

    private String nTrasaction;

    @Temporal(TemporalType.DATE)
    private Date dateDepot;

    private BigDecimal montant;

    @ManyToOne
    private Compte compte;

    @OneToMany(mappedBy = "transaction")
    private Collection<Encaissement> encaissements;

}
