package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.OrdersDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.ClientsService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.MenuService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.*;
import java.util.stream.Collectors;
@Component
public class OrdersDtoToOrdersConverter {
    private static MenuService menuService;
    private static ClientsService clientsService;


    public OrdersDtoToOrdersConverter(MenuService menuService, ClientsService clientsService) {
        this.menuService = menuService;
        this.clientsService = clientsService;
    }

    public static Orders convert(OrdersDto ordersDto) {
        Orders orders = new Orders();
        orders.setId(ordersDto.getId());
        orders.setTotalcost(ordersDto.getTotalcost());
        // Handling orders_client
        if(ordersDto.getOrders_Menu()!=null) {
            clientsService.readById(ordersDto.getOrders_client()).ifPresent(orders::setOrders_client);
        }
        else{
            throw new RuntimeException();
        }
        if (ordersDto.getOrders_Menu() != null) {
            Set<Menu> ordersmenuid = new HashSet<>();
            for (Long menuid : ordersDto.getOrders_Menu()) {
                ordersmenuid.add(menuService.readById(menuid).orElseThrow());
            }
            orders.setOrders_Menu(ordersmenuid);
        }
        else{
            throw new RuntimeException();
        }
        return orders;
    }
}
