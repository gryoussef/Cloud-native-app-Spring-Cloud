package ma.s.gcm.mapper;

import ma.s.gcm.domain.Encaissement;
import ma.s.gcm.dto.EncaissementDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class EncaissementMapper {
    private EncaissementMapper() {}

    public static EncaissementDto toDto(Encaissement encaissement) {
        return EncaissementDto.builder()
                .id(encaissement.getId())
                .banque(encaissement.getBanque())
                .cheqAuNomDe(encaissement.getCheqAuNomDe())
                .client(ClientMapper.toDto(encaissement.getClient()))
                .dateCheque(encaissement.getDateCheque())
                .dateEffet(encaissement.getDateEffet())
                .dateReception(encaissement.getDateReception())
                .dateReleveBancaire(encaissement.getDateReleveBancaire())
                .mode(encaissement.getMode())
                .montant(encaissement.getMontant())
                .devise(encaissement.getDevise())
                .motif(encaissement.getMotif())
                .nCheque(encaissement.getNCheque())
                .nCompteVirement(encaissement.getNCompteVirement())
                .nEffet(encaissement.getNEffet())
                .nVirement(encaissement.getNVirement())
                .statut(encaissement.getStatut())
                .transaction(TransactionMapper.toDto(encaissement.getTransaction()))
                .type(encaissement.getType())
                .build();
    }

    public static List<EncaissementDto> toDtos(List<Encaissement> encaissements) {
        return encaissements.stream().filter(Objects::nonNull)
                .map(EncaissementMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Encaissement toEntity(EncaissementDto encaissement) {
        return Encaissement.builder()
                .id(encaissement.getId())
                .banque(encaissement.getBanque())
                .cheqAuNomDe(encaissement.getCheqAuNomDe())
                .client(ClientMapper.toEntity(encaissement.getClient()))
                .dateCheque(encaissement.getDateCheque())
                .dateEffet(encaissement.getDateEffet())
                .dateReception(encaissement.getDateReception())
                .dateReleveBancaire(encaissement.getDateReleveBancaire())
                .mode(encaissement.getMode())
                .montant(encaissement.getMontant())
                .devise(encaissement.getDevise())
                .motif(encaissement.getMotif())
                .nCheque(encaissement.getNCheque())
                .nCompteVirement(encaissement.getNCompteVirement())
                .nEffet(encaissement.getNEffet())
                .nVirement(encaissement.getNVirement())
                .statut(encaissement.getStatut())
                .type(encaissement.getType())
                .build();
    }
}
