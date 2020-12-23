package ma.s.gcm.service;

import ma.s.gcm.domain.Compte;
import ma.s.gcm.dto.CompteDto;
import ma.s.gcm.dto.EntrepriseDto;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.mapper.CompteMapper;
import ma.s.gcm.mapper.EntrepriseMapper;
import ma.s.gcm.repository.CompteRepository;
import ma.s.gcm.repository.EntrepriseRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class EntrepriseService {
    private static final Logger LOGGER = LoggerFactory.getLogger(EntrepriseService.class);

    private EntrepriseRepository entrepriseRepository;
    private CompteRepository compteRepository;

    public EntrepriseService(EntrepriseRepository entrepriseRepository, CompteRepository compteRepository) {
        this.entrepriseRepository = entrepriseRepository;
        this.compteRepository = compteRepository;
    }



    public EntrepriseDto findById(Long id) throws SuiviClientsException {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(entrepriseRepository.findById(id))
                .map(v->EntrepriseMapper.toDto(v.get()))
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Entreprise not found"));
    }

    public List<EntrepriseDto> findAll()throws SuiviClientsException{
        LOGGER.debug("START SERVICE find ALL");
        return  Optional.ofNullable(entrepriseRepository.findAll())
                .map(EntrepriseMapper::toDtos)
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Entreprises not found"));
    }

    public List<CompteDto> findAccounts(Long entrepriseId)throws SuiviClientsException{
        LOGGER.debug("START SERVICE find accounts");
        return Optional.ofNullable(compteRepository.findAllByEntreprise_Id(entrepriseId))
                .map(CompteMapper::toDtos)
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Comptes not found"));

    }


}
