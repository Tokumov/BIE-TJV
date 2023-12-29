package com.example.frontend.modeldto;

import java.sql.Date;
import java.sql.Time;

public class BookingDto {

    public Long getId_booking() {
        return id_booking;
    }

    public void setId_booking(Long id_booking) {
        this.id_booking = id_booking;
    }

    public Time getTime_of_booking() {
        return time_of_booking;
    }

    public void setTime_of_booking(Time time_of_booking) {
        this.time_of_booking = time_of_booking;
    }

    public Date getDate_of_booking() {
        return date_of_booking;
    }

    public void setDate_of_booking(Date date_of_booking) {
        this.date_of_booking = date_of_booking;
    }

    public Long getBooking_client() {
        return booking_client;
    }

    public void setBooking_client(Long booking_client) {
        this.booking_client = booking_client;
    }

    private Long id_booking;
    private Time time_of_booking;
    private Date date_of_booking;

    private Long booking_client;

}
