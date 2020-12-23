package ma.s.gcm.mapper;

import ma.s.gcm.domain.Client;
import ma.s.gcm.domain.Entreprise;
import ma.s.gcm.domain.Facture;
import ma.s.gcm.domain.Ville;
import ma.s.gcm.dto.ClientDto;
import ma.s.gcm.dto.EntrepriseDto;
import ma.s.gcm.dto.VilleDto;

import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

public class ClientMapper {


    public static Client toEntity(ClientDto clientDto, Entreprise e) {
        return Client.builder()
                .id(clientDto.getId())
                .nom(clientDto.getNom())
                .entreprise(e)
                .build();
    }
    public static ClientDto toDto(Client client) {
        return ClientDto.builder()
                .id(client.getId())
                .nom(client.getNom())
                .build();
    }

    public static List<ClientDto> toDtos(List<Client> clients) {
        return clients.stream().filter(Objects::nonNull)
                .map(ClientMapper::toDto)
                .collect(Collectors.toList());
    }

    public static Client toEntity(ClientDto clientDto) {
        return Client.builder()
                .id(clientDto.getId())
                .nom(clientDto.getNom())
                .build();
    }

    public static List<Facture> toDtoFactures(Client client) {
        return client.getFactures()
                .stream()
                .filter(Objects::nonNull)
                .collect(Collectors.toList());
    }

}
