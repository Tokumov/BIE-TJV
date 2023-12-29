package com.example.frontend.clientAPI;

import com.example.frontend.modeldto.ClientsDto;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
@Component
public class ClientsClient {
    private WebTarget allClientEndpoint;
    private WebTarget singleEndpointTemplate;
    private WebTarget singleClientEndpoint;

    public ClientsClient(@Value("http://localhost:8081") String apiUrl) {
        var c = ClientBuilder.newClient();
        allClientEndpoint = c.target(apiUrl + "/clients");
        singleEndpointTemplate = allClientEndpoint.path("/{id}");
    }

    public ClientsDto create(ClientsDto e) {
        if(e.getNameClient()!=null){
        System.out.println("it is here");
       /* var response = allClientEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));

        if (response.getStatus() != Response.Status.OK.getStatusCode()) {
            System.out.println("it is here2");
            throw new RuntimeException("Failed to create client: " + response.getStatus());
        }
        System.out.println("it is here3");
        return response.readEntity(ClientsDto.class);*/
        return allClientEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), ClientsDto.class);}
        return new ClientsDto();
    }

    public Collection<ClientsDto> readAll() {
        var res = allClientEndpoint.request(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientsDto[].class);
        return Arrays.asList(res);
    }

    public Optional<ClientsDto> readOne() {
        var response = singleClientEndpoint.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(ClientsDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }

    public void setCurrentClient(long id) {
        singleClientEndpoint = singleEndpointTemplate.resolveTemplate("id", id);
    }

    public void updateOne(ClientsDto e) {
        singleClientEndpoint.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        singleClientEndpoint.request(MediaType.APPLICATION_JSON_TYPE).delete();}

}
