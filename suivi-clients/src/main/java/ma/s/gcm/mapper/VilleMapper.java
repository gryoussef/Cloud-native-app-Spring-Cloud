package ma.s.gcm.mapper;

import ma.s.gcm.domain.Ville;
import ma.s.gcm.dto.VilleDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public final class VilleMapper {
    private VilleMapper() {}

    public static VilleDto toDto(Ville ville) {
        return VilleDto.builder()
                .id(ville.getId())
                .nom(ville.getNom())
                .build();
    }

    public static List<VilleDto> toDtos(List<Ville> villes) {
        return villes.stream().filter(Objects::nonNull)
                .map(VilleMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Ville toEntity(VilleDto villeDto) {
        return Ville.builder()
                .id(villeDto.getId())
                .nom(villeDto.getNom())
                .build();
    }
}
