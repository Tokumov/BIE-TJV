package com.example.frontend.clientAPI;

import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.modeldto.MenuDto;
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
public class MenuClient {
    private WebTarget baseUrl;
    private WebTarget target;
    private WebTarget current;

    public MenuClient(@Value("http://localhost:8081") String baseurl) {
        var c = ClientBuilder.newClient();
        baseUrl = c.target(baseurl + "/menu");
        target = baseUrl.path("/{id}");
    }

    public MenuDto create(MenuDto e) {
        if(e.getName()!=null){

            return baseUrl.request(MediaType.APPLICATION_JSON_TYPE)
                    .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), MenuDto.class);}
        return new MenuDto();
    }

    public Collection<MenuDto> readAll() {
        var res = baseUrl.request(MediaType.APPLICATION_JSON_TYPE)
                .get(MenuDto[].class);
        return Arrays.asList(res);
    }

    public Optional<MenuDto> readOne() {
        var response = current.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(MenuDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }

    public void setCurrent(long id) {
        current = target.resolveTemplate("id", id);
    }

    public void updateOne(MenuDto e) {
        current.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        current.request(MediaType.APPLICATION_JSON_TYPE).delete();}
}
