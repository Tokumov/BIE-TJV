package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;

@Component
class CrudServiceSubclass extends CrudService<Clients, Long, ClientRepository> {
    public CrudServiceSubclass(ClientRepository repository) {
        super(repository);
    }
}
