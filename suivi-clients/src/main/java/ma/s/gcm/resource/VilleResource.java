package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;
import ma.s.gcm.dto.VilleDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.service.VilleService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/ville")
//@PreAuthorize("isAuthenticated()")
public class VilleResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(VilleResource.class);

    private final VilleService villeService;

    public VilleResource(VilleService villeService) {
        this.villeService = villeService;
    }

    @PostMapping
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody VilleDto villeDto) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE add city by name : {}", villeDto.getNom());
        villeService.save(villeDto);
        LOGGER.debug("END RESOURCE add city by id : {}, name: {} is ok", villeDto.getId(), villeDto.getNom());
    }

    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody VilleDto villeDto) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE update city by name : {}, id: {}", villeDto.getNom(), villeDto.getId());
        villeService.save(villeDto);
        LOGGER.debug("END RESOURCE update city by id : {}, name: {} is ok", villeDto.getId(), villeDto.getNom());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public VilleDto get(@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE find city by id : {}", id);
        VilleDto villeDto = villeService.findById(id);
        LOGGER.debug("END RESOURCE find city by id : {}, name :{}", id, villeDto.getNom());
        return villeDto;
    }

    @GetMapping
    @JsonView(UserView.Basic.class)
    public List<VilleDto> getAll() throws SuiviClientsException {
        LOGGER.debug("START RESOURCE all find cities");
        List<VilleDto> villeDtos = villeService.findAll();
        LOGGER.debug("END RESOURCE find all cities, size: {}", villeDtos.size());
        return villeDtos;
    }

    @DeleteMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void delete(@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE delete city by id : {}", id);
        villeService.delete(id);
        LOGGER.debug("END RESOURCE delete city by id : {}, is ok", id);
    }
}
