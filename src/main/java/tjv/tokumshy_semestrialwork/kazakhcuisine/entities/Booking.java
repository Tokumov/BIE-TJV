package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToOne;

import java.sql.Time;
import java.text.DateFormat;
@Entity
public class Booking {
    @Id
    private Long idBooking;
    private Time time;
    private DateFormat date;
    @ManyToOne
    private Clients booking_client;

}
