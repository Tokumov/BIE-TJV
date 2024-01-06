package com.example.frontend.service;

import com.example.frontend.clientAPI.BookingClient;
import com.example.frontend.clientAPI.ClientsClient;
import com.example.frontend.modeldto.BookingDto;
import com.example.frontend.modeldto.ClientsDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class BookingService {
    @Autowired
    private BookingClient bookingClient;
    BookingService(){

    }
    public BookingService(BookingClient bookingClient) {
        this.bookingClient = bookingClient;
    }

    public void create(BookingDto e) {
        bookingClient.create(e);
    }

    public Collection<BookingDto> readAll() {
        return bookingClient.readAll();
    }

    public Optional<BookingDto> readOne() {
        return bookingClient.readOne();
    }

    public void setCurrentClient(long id) {
        bookingClient.setCurrent(id);
    }


    public void update(BookingDto e) {
        bookingClient.updateOne(e);
    }

    public void deleteOne() {
        bookingClient.deleteOne();
    }
}
