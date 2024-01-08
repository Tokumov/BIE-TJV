package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.OrdersDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.stream.Collectors;
@Component
public class OrdersToOrdersDtoConverter {
    public OrdersDto convert(Orders orders) {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setId(orders.getId());
        ordersDto.setTotalcost(orders.getTotalcost());
        ordersDto.setOrders_client(orders.getOrders_client().getId());
        ordersDto.setOrders_Menu(orders.getOrders_Menu().stream().map(Menu::getId).collect(Collectors.toList()));
        return ordersDto;
    }
}
