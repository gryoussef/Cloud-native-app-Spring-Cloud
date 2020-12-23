package ma.s.gcm.service;

import ma.s.gcm.dto.ClientDto;
import ma.s.gcm.dto.FactureDto;
import ma.s.gcm.dto.VilleDto;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.mapper.ClientMapper;
import ma.s.gcm.mapper.FactureMapper;
import ma.s.gcm.mapper.VilleMapper;
import ma.s.gcm.repository.ClientRepository;
import ma.s.gcm.repository.FactureRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

@Service
@Transactional
public class FactureService {
    private static final Logger LOGGER = LoggerFactory.getLogger(ClientService.class);
    private final FactureRepository factureRepository;
    private ClientRepository clientRepository;

    public FactureService(FactureRepository factureRepository, ClientRepository clientRepository) {
        this.factureRepository = factureRepository;
        this.clientRepository = clientRepository;
    }
    public FactureDto findById(Long id) throws SuiviClientsException {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(factureRepository.findById(id))
                .map(v-> FactureMapper.toDto(v.get()))
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Facture not found"));
    }


    public void save(FactureDto factureDto, Long id) {
        LOGGER.debug("START SERVICE save by id {}, name: {}", factureDto.getId(), factureDto.getNFacture());
        Optional.ofNullable(clientRepository.findById(id))
                .map(v->factureRepository.save(FactureMapper.toEntity(factureDto,v.get())))
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Client not found"));
        LOGGER.debug("START SERVICE save by id {}, name: {}", factureDto.getId(), factureDto.getNFacture());
    }

    public void save(FactureDto factureDto) {
        LOGGER.debug("START SERVICE save by id {}", factureDto.getId());
        factureRepository.save(FactureMapper.toEntity(factureDto));
        LOGGER.debug("START SERVICE save by id {}", factureDto.getId());
    }
}
