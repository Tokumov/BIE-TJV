package com.example.frontend.modeldto;

import java.util.Collection;

public class ClientsDto {

    private Long id;

    private String name;

    private String surname;

    private Collection<Long> client_order;

    private Collection<Long> client_booking;
    public ClientsDto(){

    }
    public ClientsDto(String name, String surname){
        this.surname=surname;
        this.name=name;

    }
    public Long getIdClients() {
        return id;
    }

    public void setIdClients(Long idClients) {
        this.id = idClients;
    }

    public String getNameClient() {
        return name;
    }

    public void setNameClient(String nameClient) {
        this.name = nameClient;
    }

    public String getSurnameClient() {
        return surname;
    }

    public void setSurnameClient(String surnameClient) {
        this.surname = surnameClient;
    }

    public Collection<Long> getClient_order() {
        return client_order;
    }

    public void setClient_order(Collection<Long> client_order) {
        this.client_order = client_order;
    }

    public Collection<Long> getClient_booking() {
        return client_booking;
    }

    public void setClient_booking(Collection<Long> client_booking) {
        this.client_booking = client_booking;
    }

}
