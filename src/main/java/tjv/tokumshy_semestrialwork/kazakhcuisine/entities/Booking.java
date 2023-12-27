package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.text.DateFormat;
@Entity
@Table(name="booking")
public class Booking implements EntityWithId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id_booking;
    private Time time_of_booking;
    private Date date_of_booking;
    @ManyToOne
    @JoinColumn(name="client_id_booking")
    private Clients booking_client;

    @Override
    public Long getId() {
        return id_booking;
    }

    @Override
    public void setId(Long aLong) {
        id_booking=aLong;
    }
}
