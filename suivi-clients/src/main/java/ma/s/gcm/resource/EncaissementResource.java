package ma.s.gcm.resource;

import ma.s.gcm.dto.EncaissementDto;
import ma.s.gcm.dto.EncaissementSearchCriteriaDto;
import ma.s.gcm.dto.TransactionDto;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.service.EncaissementService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entreprise/{entrepriseId}")
//@PreAuthorize("isAuthenticated()")
public class EncaissementResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EncaissementResource.class);

    private final EncaissementService encaissementService;

    public EncaissementResource(EncaissementService encaissementService) {
        this.encaissementService = encaissementService;
    }

    @PostMapping("/reception/encaissement")
    public void add(@RequestBody EncaissementDto encaissementDto) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE add collection by mode : {}", encaissementDto.getMode());
        encaissementService.receive(encaissementDto);
        LOGGER.debug("END RESOURCE add collection by id : {}, mode: {} is ok", encaissementDto.getId(), encaissementDto.getMode());
    }

    @PostMapping("/search")
    public List<EncaissementDto> searchByFilters(@PathVariable Long entrepriseId, @RequestBody EncaissementSearchCriteriaDto filters) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE get all collections with corporate id : {}", entrepriseId);
        List<EncaissementDto> encaissementDtos = encaissementService.searchByCriteria(entrepriseId, filters);
        LOGGER.debug("END RESOURCE get all collections with corporate id : {}, size : {}", entrepriseId, encaissementDtos.size());
        return encaissementDtos;
    }

    @PostMapping("/depot/encaissements")
    public void depose(@RequestBody TransactionDto transactionDto) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE depose collection by amount : {}", transactionDto.getMontant());
        encaissementService.deposite(transactionDto);
        LOGGER.debug("END RESOURCE depose collection by id : {}, amount: {} is ok", transactionDto.getId(), transactionDto.getMontant());
    }

    @PutMapping("/encaissements")
    public void update(@RequestBody List<EncaissementDto> encaissements) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE statement collection by size : {}", encaissements.size());
        encaissementService.update(encaissements);
        LOGGER.debug("END RESOURCE depose collection by size: {} is ok", encaissements.size());
    }
}
