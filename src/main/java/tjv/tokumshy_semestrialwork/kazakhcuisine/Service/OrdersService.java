package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.stereotype.Service;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.OrdersRepository;
@Service
public class OrdersService extends CrudService<Orders,Long, OrdersRepository> {
    public OrdersService(OrdersRepository repository){
        super(repository);
    }
}
