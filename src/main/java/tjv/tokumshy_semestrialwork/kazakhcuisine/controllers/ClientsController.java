package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.ClientsService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;
@RestController
@RequestMapping("/clients")
public class ClientsController extends CrudController<Clients,Long, ClientsService, ClientRepository> {
   /* @Autowired
    private ClientsService clientsService;

    @PostMapping
    public ResponseEntity<?> createClient(@RequestBody Clients client) {
        try {
            clientsService.create(client); // Assumes a method in your service that handles the creation logic
            return ResponseEntity.ok("Client added successfully");
        } catch (Exception e) {
            return ResponseEntity.badRequest().body("Error adding client: " + e.getMessage());
        }
    }*/

    public ClientsController(ClientsService service){

        super(service);
    }
}
