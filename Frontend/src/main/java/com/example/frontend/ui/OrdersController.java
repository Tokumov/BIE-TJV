package com.example.frontend.ui;

import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.modeldto.MenuDto;
import com.example.frontend.modeldto.OrdersDto;
import com.example.frontend.service.ClientsService;
import com.example.frontend.service.MenuService;
import com.example.frontend.service.OrdersService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @GetMapping("/orders")
    public String menulist(Model model) {
        model.addAttribute("menulist", ordersService.readAll());
        return "menu";
    }
    @GetMapping("/orders/add")
    public String addMenu(Model model) {
        model.addAttribute("ordersDto", new OrdersDto());
        System.out.println("pizda");
        return "addOrders";
    }

    @PostMapping("/orders/add")
    public String addMenu(@ModelAttribute OrdersDto ordersDto, Model model) {
        System.out.println("HUIHUIHUI");
        ordersService.create(ordersDto);
        return "redirect:/menu"; // Redirect to a confirmation page or back to the form
    }
    @GetMapping("orders/delete/{id}")
    public String deleteOrders(@PathVariable Long id){
        ordersService.setCurrentOrder(id);
        ordersService.deleteOne();
        return "redirect:/orders";
    }
    @GetMapping("orders/edit/{id}")
    public String editMenu(@PathVariable long id, Model model) {
        ordersService.setCurrentOrder(id);
        model.addAttribute("orders", ordersService.readOne().orElseThrow());
        return "editOrders";
    }

    @PostMapping("menu/edit")
    public String editMenu(@ModelAttribute OrdersDto ordersDto, Model model) {
        ordersService.setCurrentOrder(ordersDto.getId());
        try {
            ordersService.update(ordersDto);
        } catch (BadRequestException e) {
            System.out.println("There is nothing bro");
        }
        model.addAttribute("client", ordersDto);
        return "redirect:/menu";
    }
}
