package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.util.Collection;
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
}
