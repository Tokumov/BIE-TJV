package com.example.frontend.ui;

import com.example.frontend.modeldto.BookingDto;
import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.service.BookingService;
import com.example.frontend.service.ClientsService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class BookingController {
    @Autowired
    private BookingService bookingService;
    @GetMapping("/booking")
    public String clientlist(Model model) {
        model.addAttribute("Bookinglist", bookingService.readAll());
        return "booking";
    }
    @GetMapping("/booking/add")
    public String addClient(Model model) {
        model.addAttribute("bookingDto", new BookingDto());
        System.out.println("pizda");
        return "addbooking";
    }

    @PostMapping("/booking/add")
    public String addClient(@ModelAttribute BookingDto bookingDto, Model model) {
        bookingService.create(bookingDto);
        return "redirect:/booking"; // Redirect to a confirmation page or back to the form
    }
    @GetMapping("booking/delete/{id}")
    public String deleteClient(@PathVariable Long id){
        bookingService.setCurrentClient(id);
        bookingService.deleteOne();
        return "redirect:/client";
    }
    @GetMapping("booking/edit/{id}")
    public String editClient(@PathVariable long id, Model model) {
        bookingService.setCurrentClient(id);
        model.addAttribute("booking", bookingService.readOne().orElseThrow());
        return "editbooking";
    }

    @PostMapping("booking/edit")
    public String editClient(@ModelAttribute BookingDto bookingDto, Model model) {
        bookingService.setCurrentClient(bookingDto.getId());
        try {
            bookingService.update(bookingDto);
        } catch (BadRequestException e) {
            System.out.println("There is nothing bro");
        }
        model.addAttribute("booking", bookingDto);
        return "redirect:/booking";
    }
}
