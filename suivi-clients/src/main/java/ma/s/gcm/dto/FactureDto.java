package ma.s.gcm.dto;

import lombok.*;
import ma.s.gcm.type.StatutFacture;
import ma.s.gcm.type.TypeFacture;

import java.sql.Date;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Builder
public class FactureDto {
    private Long id;

    private TypeFacture type;

    private String nFacture;
    private Date date;

    private ClientDto client;

    private Double montantHt;
    private Double pcRemise;
    private String devise;
    private Double tauxChange;

    private StatutFacture statut;

}