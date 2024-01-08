package com.example.frontend.modeldto;

import java.sql.Date;
import java.sql.Time;

public class BookingDto {
    private Long id;
    private String time_of_booking;
    private String date_of_booking;

    private Long booking_client;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
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

    public Long getBooking_client() {
        return booking_client;
    }

    public void setBooking_client(Long booking_client) {
        this.booking_client = booking_client;
    }



}
