package tjv.tokumshy_semestrialwork.kazakhcuisine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;

import java.util.Collection;

@Repository
public  interface ClientRepository extends CrudRepository<Clients,Long> {
//Collection<Clients>FindbyID(Long id);

}
