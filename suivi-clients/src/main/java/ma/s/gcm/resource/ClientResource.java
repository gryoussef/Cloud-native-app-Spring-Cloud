package ma.s.gcm.resource;

import com.fasterxml.jackson.annotation.JsonView;
import ma.s.gcm.domain.Client;
import ma.s.gcm.domain.Facture;
import ma.s.gcm.dto.ClientDto;
import ma.s.gcm.dto.VilleDto;
import ma.s.gcm.dto.views.UserView;
import ma.s.gcm.exception.SuiviClientsException;
import ma.s.gcm.service.ClientService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;


@RestController
@RequestMapping("/client")
public class ClientResource {

    private static final Logger LOGGER = LoggerFactory.getLogger(VilleResource.class);

    private final ClientService clientService;

    public ClientResource(ClientService clientService) {
        this.clientService = clientService;
    }


    @GetMapping("/entreprise/{entrepriseId}")
    public List<ClientDto> getAll(@PathVariable Long entrepriseId) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE all find client");
        List<ClientDto> clients = clientService.findAll(entrepriseId);
        LOGGER.debug("END RESOURCE find all client, size: {}", clients.size());
        return clients;
    }


    @PostMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public void add(@RequestBody ClientDto clientDto,@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE add client by name : {}", clientDto.getNom());
        clientService.save(clientDto,id);
        LOGGER.debug("END RESOURCE add client by id : {}, name: {} is ok", clientDto.getId(), clientDto.getNom());
    }

    @GetMapping("/{id}")
    @JsonView(UserView.Basic.class)
    public  ClientDto  get(@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE find client by id : {}", id);
        ClientDto client = clientService.findById(id);
        LOGGER.debug("END RESOURCE find client by id : {}", id);
        return client;
    }


    @PutMapping
    @JsonView(UserView.Basic.class)
    public void update(@RequestBody ClientDto clientDto) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE add client by name : {}", clientDto.getNom());
        clientService.save(clientDto);
        LOGGER.debug("END RESOURCE add client by id : {}, name: {} is ok", clientDto.getId(), clientDto.getNom());
    }


    @GetMapping("/{id}/facture")
    @JsonView(UserView.Basic.class)
    public List<Facture> getAllFactures(@PathVariable Long id) throws SuiviClientsException {
        LOGGER.debug("START RESOURCE all find client");
        List<Facture> factures = clientService.findAllFactures(id);
        LOGGER.debug("END RESOURCE find all client, size: {}", factures.size());
        return factures;
    }

}
