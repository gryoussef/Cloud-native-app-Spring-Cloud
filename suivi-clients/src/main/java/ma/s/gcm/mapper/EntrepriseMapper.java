package ma.s.gcm.mapper;

import ma.s.gcm.domain.Client;
import ma.s.gcm.domain.Compte;
import ma.s.gcm.domain.Entreprise;
import ma.s.gcm.domain.Ville;
import ma.s.gcm.dto.CompteDto;
import ma.s.gcm.dto.EntrepriseDto;
import ma.s.gcm.dto.VilleDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class EntrepriseMapper {
    public EntrepriseMapper() {
    }
    public static EntrepriseDto toDto(Entreprise entreprise) {
        return EntrepriseDto.builder()
                .id(entreprise.getId())
                .nom(entreprise.getNom())
                .pattente(entreprise.getPattente())
                .build();
    }

    public static List<EntrepriseDto> toDtos(List<Entreprise> entreprises) {
        return entreprises.stream().filter(Objects::nonNull)
                .map(EntrepriseMapper::toDto)
                .collect(Collectors.toList());
    }
    public static Entreprise toEntity(EntrepriseDto entrepriseDto) {
        return Entreprise.builder()
                .id(entrepriseDto.getId())
                .nom(entrepriseDto.getNom())
                .pattente(entrepriseDto.getPattente())
                .build();
    }

    public static List<Compte> toDtoAccounts(Entreprise entreprise) {
        return entreprise.getComptes().stream().filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

    public static List<Client> toDtoClients(Entreprise entreprise) {
        return entreprise.getClients().stream().filter(Objects::nonNull)
                .collect(Collectors.toList());
    }
}
