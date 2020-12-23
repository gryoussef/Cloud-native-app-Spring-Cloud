package ma.s.gcm.dto;

import lombok.*;

import java.math.BigDecimal;
import java.util.Date;
import java.util.List;

@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class TransactionDto {
    private Long id;
    private String nTrasaction;
    private Date dateDepot;
    private BigDecimal montant;
    private CompteDto compte;
    private List<EncaissementDto> encaissements;
}
