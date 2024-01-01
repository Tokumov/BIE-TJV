package com.example.frontend.clientAPI;

import com.example.frontend.modeldto.OrdersDto;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Value;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;

public class OrdersClient {
    private WebTarget allOrderEndpoint;
    private WebTarget singleEndpointTemplate;
    private WebTarget singleOrderEndpoint;


    public OrdersClient(@Value("${server.url}") String apiUrl) {
        var c = ClientBuilder.newClient();
        allOrderEndpoint = c.target(apiUrl + "/orders");
        singleEndpointTemplate = allOrderEndpoint.path("/{id}");
    }

    public OrdersDto create(OrdersDto e) {
        return allOrderEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), OrdersDto.class);
    }

    public Collection<OrdersDto> readAll() {
        var res = allOrderEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .get(OrdersDto[].class);
        return Arrays.asList(res);
    }

    public Optional<OrdersDto> readOne() {
        var response = singleOrderEndpoint.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(OrdersDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }

    public void setCurrentOrder(long id) {
        singleOrderEndpoint = singleEndpointTemplate.resolveTemplate("id", id);
    }

    public void updateOne(OrdersDto e) {
        singleOrderEndpoint.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        singleOrderEndpoint.request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

}
