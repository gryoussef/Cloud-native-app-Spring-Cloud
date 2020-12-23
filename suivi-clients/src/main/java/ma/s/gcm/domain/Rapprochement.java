package ma.s.gcm.domain;

import lombok.*;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Getter
@Setter
@Builder
@AllArgsConstructor
@NoArgsConstructor
public class Rapprochement {
    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ID_RAPPROCHEMENT_SEQ")
    @SequenceGenerator(name = "ID_RAPPROCHEMENT_SEQ", sequenceName = "ID_RAPPROCHEMENT_SEQ")
    private Long id;

    private BigDecimal montant;

    @ManyToOne
    private Encaissement encaissement;

    @ManyToOne
    private Facture facture;
}
