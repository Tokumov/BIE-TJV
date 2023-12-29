package com.example.frontend.service;

import com.example.frontend.clientAPI.ClientsClient;
import com.example.frontend.modeldto.ClientsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class ClientsService {
    @Autowired
    private ClientsClient clientClient;
    ClientsService(){

    }
    public ClientsService(ClientsClient clientClient) {
        this.clientClient = clientClient;
    }

    public void create(ClientsDto e) {
        clientClient.create(e);
    }

    public Collection<ClientsDto> readAll() {
        return clientClient.readAll();
    }

    public Optional<ClientsDto> readOne() {
        return clientClient.readOne();
    }

    public void setCurrentClient(long id) {
        clientClient.setCurrentClient(id);
    }


    public void update(ClientsDto e) {
        clientClient.updateOne(e);
    }

    public void update(String name, String surname, Collection<Long> ordersId) {
        var cur = clientClient.readOne();
        if (cur.isPresent()) {
            var client = cur.get();
            client.setNameClient(name);
            client.setSurnameClient(surname);
            client.setClient_order(ordersId);
            update(client);
        }
    }
    public void deleteOne() {
        clientClient.deleteOne();
    }
}
