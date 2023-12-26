package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.BookingService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.OrdersService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.OrdersRepository;
@RestController
@RequestMapping("/orders")
public class OrdersController extends CrudController<Orders,Long, OrdersService, OrdersRepository> {
    public OrdersController(OrdersService service){
        super(service);
    }
}
