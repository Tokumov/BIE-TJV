package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.OneToMany;

import java.util.Collection;

@Entity
public class Clients implements EntityWithId<Long> {
    @Id
    private Long idClients;
    private String nameClient;
    private String surnameClient;
    @OneToMany(mappedBy = "orders_client")
    private Collection<Orders> client_order;
    @OneToMany(mappedBy = "booking_client")
    private Collection<Booking> client_booking;

    @Override
    public Long getId() {
        return idClients;
    }
}
