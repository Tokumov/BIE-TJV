package com.example.frontend.modeldto;

import java.util.Collection;

public class ClientsDto {

    private Long id;

    private String name;

    private String surname;

    private Collection<Long> client_order;

    private Collection<Long> client_booking;

    public void setId(Long id) {
        this.id = id;
    }
    public Long getId() {
        return id;
    }
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
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

    public ClientsDto(){

    }
    public ClientsDto(String name, String surname){
        this.surname=surname;
        this.name=name;

    }



}
