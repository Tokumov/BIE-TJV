package com.example.frontend.clientAPI;

import com.example.frontend.modeldto.BookingDto;
import com.example.frontend.modeldto.ClientsDto;
import jakarta.ws.rs.BadRequestException;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.client.HttpClientErrorException;
import org.springframework.web.server.ResponseStatusException;

import java.util.Arrays;
import java.util.Collection;
import java.util.Optional;
@Component
public class BookingClient {
    private WebTarget baseUrl;
    private WebTarget target;
    private WebTarget current;

    public BookingClient(@Value("http://localhost:8081") String baseurl) {
        var c = ClientBuilder.newClient();
        baseUrl = c.target(baseurl + "/booking");
        target = baseUrl.path("/{id}");
    }

    public BookingDto create(BookingDto e) {
        try {
            return baseUrl.request(MediaType.APPLICATION_JSON_TYPE)
                .post(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE), BookingDto.class);}
        catch(BadRequestException badRequestException){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
    }

    public Collection<BookingDto> readAll() {
        var res = baseUrl.request(MediaType.APPLICATION_JSON_TYPE)
                .get(BookingDto[].class);
        return Arrays.asList(res);
    }

    public Optional<BookingDto> readOne() {
        var response = current.request(MediaType.APPLICATION_JSON_TYPE).get();
        if (response.getStatus() == 200)
            return Optional.of(response.readEntity(BookingDto.class));
        else if (response.getStatus() == 404) {
            return Optional.empty();
        } else
            throw new RuntimeException(response.getStatusInfo().getReasonPhrase());
    }

    public void setCurrent(long id) {
        current = target.resolveTemplate("id", id);
    }

    public void updateOne(BookingDto e) {
        current.request(MediaType.APPLICATION_JSON_TYPE).put(Entity.entity(e, MediaType.APPLICATION_JSON_TYPE));
    }

    public void deleteOne() {
        current.request(MediaType.APPLICATION_JSON_TYPE).delete();
    }

}
