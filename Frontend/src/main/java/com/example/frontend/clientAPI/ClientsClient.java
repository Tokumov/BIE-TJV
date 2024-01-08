package com.example.frontend.clientAPI;

import com.example.frontend.modeldto.ClientsDto;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
@Component
public class ClientsClient {
    private WebTarget baseUrl;
    private WebTarget target;
    private WebTarget current;

    public ClientsClient(@Value("http://localhost:8081") String baseurl) {
        var c = ClientBuilder.newClient();
        baseUrl = c.target(baseurl + "/clients");
        target = baseUrl.path("/{id}");
    }

    public ClientsDto create(ClientsDto e) {

        return baseUrl.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), ClientsDto.class);
    }

    public Collection<ClientsDto> readAll() {
        var res = baseUrl.request(MediaType.APPLICATION_JSON_TYPE)
                .get(ClientsDto[].class);

        return Arrays.asList(res);
    }

    public Optional<ClientsDto> readOne() {
        var response = current.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(ClientsDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }

    public void setCurrent(long id) {
        current = target.resolveTemplate("id", id);
    }

    public void updateOne(ClientsDto e) {
        current.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        current.request(MediaType.APPLICATION_JSON_TYPE).delete();}

}
