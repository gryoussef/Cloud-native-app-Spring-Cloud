package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;
import ma.s.gcm.dto.FactureDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.service.FactureService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/facture")
public class FactureResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(FactureResource.class);
    private final FactureService factureService;

    public FactureResource(FactureService factureService) { this.factureService = factureService; }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public FactureDto get(@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE find facture by id : {}", id);
        FactureDto factureDto = factureService.findById(id);
        LOGGER.debug("END RESOURCE find facture by id : {}", id);
        return factureDto;
    }

    @PostMapping("/client/{id}")
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody FactureDto factureDto,@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE add facture by number : {}", factureDto.getNFacture());
        factureService.save(factureDto,id);
        LOGGER.debug("END RESOURCE add facture by number: {} is ok", factureDto.getId(), factureDto.getNFacture());
    }

    @PutMapping()
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody FactureDto factureDto) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE add facture by number : {}", factureDto.getNFacture());
        factureService.save(factureDto);
        LOGGER.debug("END RESOURCE add facture by number: {} is ok", factureDto.getNFacture());
    }

}
