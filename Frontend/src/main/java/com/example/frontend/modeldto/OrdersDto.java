package com.example.frontend.modeldto;

import java.util.Collection;

public class OrdersDto {
    private Long id;

    private Long totalcost;

    private Long orders_client;

    private Collection<Long> orders_Menu;

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
