package ma.s.gcm.mapper;

import ma.s.gcm.domain.Client;
import ma.s.gcm.domain.Entreprise;
import ma.s.gcm.domain.Facture;
import ma.s.gcm.dto.ClientDto;
import ma.s.gcm.dto.FactureDto;

public class FactureMapper {

    public static Facture toEntity(FactureDto factureDto, Client c) {
        return Facture.builder()
                .id(factureDto.getId())
                .nFacture(factureDto.getNFacture())
                .date(factureDto.getDate())
                .devise(factureDto.getDevise())
                .montantHt(factureDto.getMontantHt())
                .pcRemise(factureDto.getPcRemise())
                .statut(factureDto.getStatut())
                .tauxChange(factureDto.getTauxChange())
                .type(factureDto.getType())
                .client(c)
                .build();
    }
    public static FactureDto toDto(Facture facture) {
        return FactureDto.builder()
                .id(facture.getId())
                .nFacture(facture.getNFacture())
                .date(facture.getDate())
                .devise(facture.getDevise())
                .montantHt(facture.getMontantHt())
                .pcRemise(facture.getPcRemise())
                .statut(facture.getStatut())
                .tauxChange(facture.getTauxChange())
                .type(facture.getType())
                .client(ClientMapper.toDto(facture.getClient()))
                .build();
    }
    public static Facture toEntity(FactureDto factureDto) {
        return Facture.builder()
                .id(factureDto.getId())
                .nFacture(factureDto.getNFacture())
                .date(factureDto.getDate())
                .devise(factureDto.getDevise())
                .montantHt(factureDto.getMontantHt())
                .pcRemise(factureDto.getPcRemise())
                .statut(factureDto.getStatut())
                .tauxChange(factureDto.getTauxChange())
                .type(factureDto.getType())
                .client(ClientMapper.toEntity(factureDto.getClient()))
                .build();
    }

}
