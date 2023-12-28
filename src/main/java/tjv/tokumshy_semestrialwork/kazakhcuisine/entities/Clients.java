package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
public class Clients implements EntityWithId<Long> {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long idClients;
    @Column(name="name")
    private String nameClient;
    @Column(name="surname")
    private String surnameClient;
    @OneToMany(mappedBy = "orders_client")
    private Collection<Orders> client_order;
    @OneToMany(mappedBy = "booking_client")
    private Collection<Booking> client_booking;

    public Clients() {

    }

    public Long getIdClients() {
        return idClients;
    }

    public void setIdClients(Long idClients) {
        this.idClients = idClients;
    }

    public String getNameClient() {
        return nameClient;
    }

    public void setNameClient(String nameClient) {
        this.nameClient = nameClient;
    }

    public String getSurnameClient() {
        return surnameClient;
    }

    public void setSurnameClient(String surnameClient) {
        this.surnameClient = surnameClient;
    }

    public Collection<Orders> getClient_order() {
        return client_order;
    }

    public void setClient_order(Collection<Orders> client_order) {
        this.client_order = client_order;
    }

    public Collection<Booking> getClient_booking() {
        return client_booking;
    }

    public void setClient_booking(Collection<Booking> client_booking) {
        this.client_booking = client_booking;
    }

    @Override
    public Long getId() {
        return idClients;
    }

    @Override
    public void setId(Long aLong) {
        idClients=aLong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return Objects.equals(idClients, clients.idClients);
    }

    @Override
    public int hashCode() {
        return Objects.hash(idClients);
    }

    public Clients(Long id, String name, String surname) {
        this.idClients = id;
        this.nameClient = name;
        this.surnameClient = surname;
        client_order=new HashSet<>();
        client_booking=new HashSet<>();
    }

    @Override
    public String toString() {
        return "Clients{" +
                "idClients=" + idClients +
                ", nameClient='" + nameClient + '\'' +
                ", surnameClient='" + surnameClient + '\'' +
                ", client_order=" + client_order +
                ", client_booking=" + client_booking +
                '}';
    }
}
