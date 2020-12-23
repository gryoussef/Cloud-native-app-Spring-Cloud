package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;
import ma.s.gcm.domain.Compte;
import ma.s.gcm.domain.Entreprise;
import ma.s.gcm.dto.CompteDto;
import ma.s.gcm.dto.EntrepriseDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.service.EntrepriseService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/entreprise")
public class EntrepriseResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(EntrepriseResource.class);
    private final EntrepriseService entrepriseService;

    public EntrepriseResource(EntrepriseService entrepriseService) { this.entrepriseService = entrepriseService;}


    @GetMapping
    public List<EntrepriseDto> getAll() throws SuiviClientsException {
        LOGGER.debug("START RESOURCE all find entreprises");
        List<EntrepriseDto> entreprises = entrepriseService.findAll();
        LOGGER.debug("END RESOURCE find all entreprises, size: {}", entreprises.size());
        return entreprises;
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public EntrepriseDto get(@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE find entreprise by id : {}", id);
        EntrepriseDto entreprise = entrepriseService.findById(id);
        LOGGER.debug("END RESOURCE find entreprise by id : {}", id);
        return entreprise;
    }


    @GetMapping("/{entrepriseId}/compte")
    public List<CompteDto> getAccounts(@PathVariable Long entrepriseId) throws SuiviClientsException{
        LOGGER.debug("START RESOURCE all find entreprises");
        List<CompteDto> comptes = entrepriseService.findAccounts(entrepriseId);
        LOGGER.debug("END RESOURCE find all entreprises, size: {}", comptes.size());
        return comptes;
    }
}
