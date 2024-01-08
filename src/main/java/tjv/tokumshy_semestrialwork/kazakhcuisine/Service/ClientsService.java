package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.stereotype.Service;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;
@Service
public class ClientsService  extends CrudService<Clients,Long, ClientRepository>{
    public ClientsService(ClientRepository repository){
        super(repository);
    }
}
