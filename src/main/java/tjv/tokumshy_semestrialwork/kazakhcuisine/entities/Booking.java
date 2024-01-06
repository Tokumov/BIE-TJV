package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.sql.Date;
import java.sql.Time;
import java.util.Objects;

@Entity
@Table(name="booking")
public class Booking implements EntityWithId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String time_of_booking;
    private String date_of_booking;
    @ManyToOne
    @JoinColumn(name="client_id_booking")
    private Clients booking_client;



    @Override
    public String toString() {
        return "Booking{" +
                "id_booking=" + id +
                ", time_of_booking=" + time_of_booking +
                ", date_of_booking=" + date_of_booking +
                ", booking_client=" + booking_client +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Booking booking = (Booking) o;
        return Objects.equals(id, booking.id);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id);
    }



    public String getTime_of_booking() {
        return time_of_booking;
    }

    public void setTime_of_booking(String time_of_booking) {
        this.time_of_booking = time_of_booking;
    }

    public String getDate_of_booking() {
        return date_of_booking;
    }

    public void setDate_of_booking(String date_of_booking) {
        this.date_of_booking = date_of_booking;
    }

    public Clients getBooking_client() {
        return booking_client;
    }

    public void setBooking_client(Clients booking_client) {
        this.booking_client = booking_client;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long aLong) {
        id =aLong;
    }
}
