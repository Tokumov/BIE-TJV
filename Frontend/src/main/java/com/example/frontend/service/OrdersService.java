package com.example.frontend.service;

import com.example.frontend.clientAPI.ClientsClient;
import com.example.frontend.clientAPI.OrdersClient;
import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.modeldto.OrdersDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class OrdersService {
    @Autowired
    private OrdersClient ordersClient;
    OrdersService(){

    }
    public OrdersService(OrdersClient ordersClient) {
        this.ordersClient = ordersClient;
    }

    public void create(OrdersDto e) {
        ordersClient.create(e);
    }

    public Collection<OrdersDto> readAll() {
        return ordersClient.readAll();
    }

    public Optional<OrdersDto> readOne() {
        return ordersClient.readOne();
    }

    public void setCurrentClient(long id) {
        ordersClient.setCurrent(id);
    }


    public void update(OrdersDto e) {
        ordersClient.updateOne(e);
    }

    public void deleteOne() {
        ordersClient.deleteOne();
    }
}
