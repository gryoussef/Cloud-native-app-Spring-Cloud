package ma.s.gcm.service;

import ma.s.gcm.dto.VilleDto;
import ma.s.gcm.exception.ExceptionCode;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.mapper.VilleMapper;
import ma.s.gcm.repository.VilleRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.Optional;

@Service
@Transactional
public class VilleService {
    private static final Logger LOGGER = LoggerFactory.getLogger(VilleService.class);

    private VilleRepository villeRepository;

    public VilleService(VilleRepository villeRepository) {
        this.villeRepository = villeRepository;
    }

    public VilleDto findById(Long id) throws SuiviClientsException {
        LOGGER.debug("START SERVICE find by id {}", id);
        return Optional.ofNullable(villeRepository.findById(id))
                .map(v->VilleMapper.toDto(v.get()))
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "City not found"));
    }



    public List<VilleDto> findAll() throws SuiviClientsException {
        LOGGER.debug("START SERVICE find all");
        return Optional.ofNullable(villeRepository.findAll())
                .map(VilleMapper::toDtos)
                .orElseThrow(() -> new SuiviClientsException(ExceptionCode.API_RESOURCE_NOT_FOUND, "Cities not found"));
    }

    public void delete(Long id) {
        LOGGER.debug("START SERVICE delete by id {}", id);
        villeRepository.deleteById(id);
        LOGGER.debug("START SERVICE delete by id {}", id);
    }

    public void save(VilleDto villeDto) {
        LOGGER.debug("START SERVICE save by id {}, name: {}", villeDto.getId(), villeDto.getNom());
        villeRepository.save(VilleMapper.toEntity(villeDto));
        LOGGER.debug("START SERVICE save by id {}, name: {}", villeDto.getId(), villeDto.getNom());
    }
}
