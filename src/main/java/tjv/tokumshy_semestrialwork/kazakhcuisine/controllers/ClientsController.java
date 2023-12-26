package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.ClientsService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;
@RestController
@RequestMapping("/clients")
public class ClientsController extends CrudController<Clients,Long, ClientsService, ClientRepository> {
    public ClientsController(ClientsService service){
        super(service);
    }
}
