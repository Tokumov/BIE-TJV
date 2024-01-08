package com.example.frontend.ui;

import com.example.frontend.modeldto.BookingDto;
import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.service.BookingService;
import com.example.frontend.service.ClientsService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @GetMapping("/booking")
    public String bookinglist(Model model) {
        model.addAttribute("Bookinglist", bookingService.readAll());
        return "booking";
    }
    @GetMapping("/booking/add")
    public String addBooking(Model model) {
        model.addAttribute("bookingDto", new BookingDto());

        return "addbooking";
    }

    @PostMapping("/booking/add")
    public String addBooking(@ModelAttribute BookingDto bookingDto, Model model) {
        try{
        bookingService.create(bookingDto);
        return "redirect:/booking";}
        catch (BadRequestException badRequestException){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
    }
    @GetMapping("booking/delete/{id}")
    public String deleteBooking(@PathVariable Long id){
        try{
        bookingService.setCurrentClient(id);
        bookingService.deleteOne();}
        catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity doesn't exist");
        }
        return "redirect:/booking";
    }
    @GetMapping("booking/edit/{id}")
    public String editBooking(@PathVariable long id, Model model) {
        try{
        bookingService.setCurrentClient(id);
        model.addAttribute("bookingDto", bookingService.readOne().orElseThrow(NoSuchElementException::new));
        return "editbooking";}
        catch (BadRequestException | NoSuchElementException noSuchElementException){
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be updated");
        }
    }

    @PostMapping("booking/edit")
    public String editBooking(@ModelAttribute BookingDto bookingDto, Model model) {
        try { bookingService.setCurrentClient(bookingDto.getId());
            bookingService.update(bookingDto);
            model.addAttribute("booking", bookingDto);
            return "redirect:/booking";
        } catch (NoSuchElementException | BadRequestException e) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be updated");
        }

    }
}
