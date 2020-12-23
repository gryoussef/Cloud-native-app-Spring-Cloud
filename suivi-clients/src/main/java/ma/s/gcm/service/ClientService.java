package ma.s.gcm.service;

import ma.s.gcm.domain.Client;
import ma.s.gcm.domain.Facture;
import ma.s.gcm.dto.ClientDto;
import ma.s.gcm.dto.EntrepriseDto;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.mapper.ClientMapper;
import ma.s.gcm.mapper.EntrepriseMapper;
import ma.s.gcm.mapper.FactureMapper;
import ma.s.gcm.repository.ClientRepository;
import ma.s.gcm.repository.EntrepriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;
@Service
@Transactional
public class ClientService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);

    private ClientRepository clientRepository;
    private EntrepriseRepository entrepriseRepository;
    public ClientService(ClientRepository clientRepository,EntrepriseRepository entrepriseRepository) {
        this.clientRepository = clientRepository;
        this.entrepriseRepository = entrepriseRepository;
    }

    public List<ClientDto> findAll(Long entrepriseId) throws SuiviClientsException{
        return Optional.ofNullable(clientRepository.findAllByEntreprise_Id(entrepriseId))
                .map(ClientMapper::toDtos)
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Entreprise not found"));
    }

    public void save(ClientDto clientDto,Long id) {
        LOGGER.debug("START SERVICE save by id {}, name: {}", clientDto.getId(), clientDto.getNom());
        Optional.ofNullable(entrepriseRepository.findById(id))
                .map(v->clientRepository.save(ClientMapper.toEntity(clientDto,v.get())))
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Entreprise not found"));
        LOGGER.debug("START SERVICE save by id {}, name: {}", clientDto.getId(), clientDto.getNom());
    }

    public ClientDto findById(Long id) throws SuiviClientsException {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(clientRepository.findById(id))
                .map(v->ClientMapper.toDto(v.get()))
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Client not found"));
    }

    public List<Facture> findAllFactures(Long id) throws SuiviClientsException{
        return Optional.ofNullable(clientRepository.findById(id))
                .map(v->ClientMapper.toDtoFactures(v.get()))
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Entreprise not found"));
    }

    public void save(ClientDto clientDto) {
        LOGGER.debug("START SERVICE save by id {}, name: {}", clientDto.getId(), clientDto.getNom());
        clientRepository.save(ClientMapper.toEntity(clientDto));
        LOGGER.debug("START SERVICE save by id {}, name: {}", clientDto.getId(), clientDto.getNom());
    }


}
