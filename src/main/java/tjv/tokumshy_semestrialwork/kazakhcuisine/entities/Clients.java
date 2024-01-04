package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;
import java.util.Objects;

@Entity
@Table(name = "clients")
public class Clients implements EntityWithId<Long> {

    @Id
    @Column(name="id")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="name")
    private String name;
    @Column(name="surname")
    private String surname;
    @OneToMany(mappedBy = "orders_client",cascade = CascadeType.REMOVE)
    private Collection<Orders> client_order;
    @OneToMany(mappedBy = "booking_client")
    private Collection<Booking> client_booking;

    public Clients() {

    }


    public void setIdClients(Long idClients) {
        this.id = idClients;
    }

    public String getName() {
        return name;
    }

    public void setName(String nameClient) {
        this.name = nameClient;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surnameClient) {
        this.surname = surnameClient;
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
        return id;
    }

    @Override
    public void setId(Long aLong) {
        id=aLong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Clients clients = (Clients) o;
        return Objects.equals(id, clients.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Clients(Long id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        client_order=new HashSet<>();
        client_booking=new HashSet<>();
    }
    public Clients(Long id) {
        this.id = id;

    }

    @Override
    public String toString() {
        return "Clients{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", surname='" + surname + '\'' +
                ", client_order=" + client_order +
                ", client_booking=" + client_booking +
                '}';
    }
}
