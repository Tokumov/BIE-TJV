package tjv.tokumshy_semestrialwork.kazakhcuisine.DTO;

import org.springframework.beans.factory.annotation.Autowired;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.MenuService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.stream.Collectors;

public class OrdersDto {
    private Long id;

    private Long totalcost;

    private Long orders_client;

    private Collection<Long> orders_Menu;

    public OrdersDto(){

    }

    public OrdersDto(Long totalcost, Long orders_client){
        this.totalcost=totalcost;
        this.orders_client=orders_client;
    }
    public OrdersDto(Long id, Long totalcost, Long orders_client, Collection<Long> orders_Menu){
        this.id=id;
        this.totalcost=totalcost;
        this.orders_client=orders_client;
        this.orders_Menu=orders_Menu;
    }
    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Long totalcost) {
        this.totalcost = totalcost;
    }

    public Long getOrders_client() {
        return orders_client;
    }

    public void setOrders_client(Long orders_client) {
        this.orders_client = orders_client;
    }

    public Collection<Long> getOrders_Menu() {
        return orders_Menu;
    }

    public void setOrders_Menu(Collection<Long> orders_Menu) {
        this.orders_Menu = orders_Menu;
    }


}
