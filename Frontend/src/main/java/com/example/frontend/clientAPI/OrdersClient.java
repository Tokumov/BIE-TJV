package com.example.frontend.clientAPI;

import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.modeldto.MenuDto;
import com.example.frontend.modeldto.OrdersDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
@Component
public class OrdersClient {
    private WebTarget baseUrl;
    private WebTarget target;
    private WebTarget current;

    public OrdersClient(@Value("http://localhost:8081") String baseurl) {
        var c = ClientBuilder.newClient();
        baseUrl = c.target(baseurl + "/orders");
        target = baseUrl.path("/{id}");
    }

    public OrdersDto create(OrdersDto e) {
            System.out.println("it is here");
            try{
            var res=baseUrl.request(MediaType.APPLICATION_JSON_TYPE).post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), OrdersDto.class);
            if(res==null){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
            }
            return res;}
            catch(BadRequestException badRequestException){
                throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
            }
    }

    public Collection<OrdersDto> readAll() {
        var res = baseUrl.request(MediaType.APPLICATION_JSON_TYPE)
                .get(OrdersDto[].class);
        return Arrays.asList(res);
    }

    public Optional<OrdersDto> readOne() {
        var response = current.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(OrdersDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }

    public void setCurrent(long id) {
        current = target.resolveTemplate("id", id);
    }

    public void updateOne(OrdersDto e) {
        current.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        current.request(MediaType.APPLICATION_JSON_TYPE).delete();}
    public Collection<OrdersDto> findOrdersWithDishHigherthanKandunderNtotalcost(Long numberofDishes, Long underprice){
        WebTarget findOrdersWithDishHigherthanKandunderNtotalcostURL = baseUrl.path("/findorders")
                .queryParam("numberofDishes", numberofDishes)
                .queryParam("underprice", underprice);
        var res=findOrdersWithDishHigherthanKandunderNtotalcostURL.request(MediaType.APPLICATION_JSON_TYPE).get(OrdersDto[].class);
        return Arrays.asList(res);
    }

}
