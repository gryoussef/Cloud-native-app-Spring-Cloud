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
public class EncaissementSearchCriteriaDto {
    private Long clientId;

    private Date dateDReception;
    private Date dateFReception;

    private Date dateDReleveBancaire;
    private Date dateFReleveBancaire;

    private Date dateDDepot;
    private Date dateFDepot;

    private BigDecimal montantMin;
    private BigDecimal montantMax;

    private StatutEncaissement statut;

    private TypeEncaissement type;
    private ModeEncaissement mode;


    private String banque;

    private String nCheque;
    private Date dateDCheque;
    private Date dateFCheque;
    private String cheqAuNomDe;

    private String nEffet;
    private Date dateDEffet;
    private Date dateFEffet;

    private String nVirement;
    private String nCompteVirement;

    private String motif;

    private String nTransaction;
    private Long compteId;
}
