package ma.s.gcm.mapper;

import ma.s.gcm.domain.Compte;
import ma.s.gcm.domain.Entreprise;
import ma.s.gcm.dto.CompteDto;
import ma.s.gcm.dto.EntrepriseDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class CompteMapper {
    public static CompteDto toDto(Compte compte) {
        return CompteDto.builder()
                .id(compte.getId())
                .nCompte(compte.getNCompte())
                .banque(compte.getBanque())
                .entreprise(EntrepriseMapper.toDto(compte.getEntreprise()))
                .build();
    }

    public static List<CompteDto> toDtos(List<Compte> comptes) {
        return comptes.stream().filter(Objects::nonNull)
                .map(CompteMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Compte toEntity(CompteDto compteDto) {
        return Compte.builder()
                .id(compteDto.getId())
                .nCompte(compteDto.getNCompte())
                .banque(compteDto.getBanque())
                .entreprise(EntrepriseMapper.toEntity(compteDto.getEntreprise()))
                .build();
    }
}
