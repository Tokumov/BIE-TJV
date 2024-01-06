package com.example.frontend.ui;

import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.service.ClientsService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

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
     System.out.println("HUIHUIHUI");
     System.out.println(clientsDto.getName());
     clientsService.create(clientsDto);
     return "redirect:/client"; // Redirect to a confirmation page or back to the form
 }
    @GetMapping("client/delete/{id}")
    public String deleteClient(@PathVariable Long id){
        clientsService.setCurrentClient(id);
        clientsService.deleteOne();
        return "redirect:/client";
    }
    @GetMapping("client/edit/{id}")
    public String editClient(@PathVariable long id, Model model) {
        clientsService.setCurrentClient(id);
        model.addAttribute("client", clientsService.readOne().orElseThrow());
        return "editclient";
    }

    @PostMapping("client/edit")
    public String editClient(@ModelAttribute ClientsDto client, Model model) {
        clientsService.setCurrentClient(client.getId());
        try {
            clientsService.update(client);
        } catch (BadRequestException e) {
        System.out.println("There is nothing bro");
        }
        model.addAttribute("client", client);
        return "redirect:/client";
    }
}
