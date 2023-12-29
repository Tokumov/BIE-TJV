package com.example.frontend.ui;

import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.service.ClientsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.Banner;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.io.Console;

@Controller

public class ClientController {
    @Autowired
    private ClientsService clientsService;
    @GetMapping("/client")
    public String home(Model model) {
        model.addAttribute("clientDto", new ClientsDto());
        System.out.println("pizda");
        return "/client";
    }

 /*   @PostMapping("client/add")
    public String clientPostAdd(@RequestParam String name, @RequestParam String surname, Model model){
        return "";
    }*/
 @PostMapping("/client")
 public String addClient(@ModelAttribute ClientsDto clientsDto, Model model) {
     System.out.println("HUIHUIHUI");
     System.out.println(clientsDto.getNameClient());
     clientsService.create(clientsDto);

     //model.addAttribute("message", "Client added successfully");
     return "redirect:/success"; // Redirect to a confirmation page or back to the form
 }
}
