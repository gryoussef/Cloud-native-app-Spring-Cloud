package ma.s.gcm.dto;

import lombok.*;
import ma.s.gcm.type.ModeEncaissement;
import ma.s.gcm.type.StatutEncaissement;
import ma.s.gcm.type.TypeEncaissement;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class EncaissementDto {
    private Long id;
    private ClientDto client;

    private Date dateReception;
    private Date dateReleveBancaire;

    private BigDecimal montant;
    private String devise;

    private TypeEncaissement type;
    private ModeEncaissement mode;
    private StatutEncaissement statut;

    private String banque;

    private String nCheque;
    private Date dateCheque;
    private String cheqAuNomDe;

    private String nEffet;
    private Date dateEffet;

    private String nVirement;
    private String nCompteVirement;

    private String motif;
    private TransactionDto transaction;
}
