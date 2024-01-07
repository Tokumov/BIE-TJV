package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.OrdersRepository;

import java.util.Collection;

@Service
public class OrdersService extends CrudService<Orders,Long, OrdersRepository> {
    @Autowired
    OrdersRepository ordersRepository;

    public OrdersService(OrdersRepository repository){
        super(repository);
    }

    public Collection<Orders>findOrdersWithDishHigherthanKandunderNtotalcost(){
     return  ordersRepository.findOrdersWithDishHigherthanKandunderNtotalcost(3L,15L);
    }
}
