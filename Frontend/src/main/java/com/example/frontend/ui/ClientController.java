package com.example.frontend.ui;

import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.service.ClientsService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.util.NoSuchElementException;

@Controller

public class ClientController {
    @Autowired
    private ClientsService clientsService;
    @GetMapping("/client")
    public String clientlist(Model model) {
        model.addAttribute("Clientslist", clientsService.readAll());
        return "client";
    }
    @GetMapping("/client/add")
    public String addClient(Model model) {
        model.addAttribute("clientDto", new ClientsDto());
        System.out.println("pizda");
        return "addclient";
    }

 @PostMapping("/client/add")
 public String addClient(@ModelAttribute ClientsDto clientsDto, Model model) {
     clientsService.create(clientsDto);
     return "redirect:/client"; // Redirect to a confirmation page or back to the form
 }
    @GetMapping("client/delete/{id}")
    public String deleteClient(@PathVariable Long id){
        try{
        clientsService.setCurrentClient(id);
        clientsService.deleteOne();}
        catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
        return "redirect:/client";
    }
    @GetMapping("client/edit/{id}")
    public String editClient(@PathVariable long id, Model model) {
       try{ clientsService.setCurrentClient(id);
        model.addAttribute("client", clientsService.readOne().orElseThrow());}
       catch (BadRequestException | NoSuchElementException noSuchElementException) {
           throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
       }
        return "editclient";
    }

    @PostMapping("client/edit")
    public String editClient(@ModelAttribute ClientsDto client, Model model) {
        try { clientsService.setCurrentClient(client.getId());
            clientsService.update(client);
        } catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
        model.addAttribute("client", client);
        return "redirect:/client";
    }
}
