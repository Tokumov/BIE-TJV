package com.example.frontend.ui;

import com.example.frontend.modeldto.MenuDto;
import com.example.frontend.modeldto.OrdersDto;
import com.example.frontend.service.OrdersService;
import jakarta.ws.rs.BadRequestException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.server.ResponseStatusException;

import java.util.Collection;
import java.util.NoSuchElementException;

@Controller
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    @GetMapping("/orders")
    public String orderslist(Model model) {
        model.addAttribute("orderslist", ordersService.readAll());
        return "orders";
    }

    @GetMapping("/orders/add")
    public String addOrders(Model model) {
        model.addAttribute("ordersDto", new OrdersDto());
        System.out.println("asdfasgasgaf");
        return "addorders";
    }

    @PostMapping("/orders/add")
    public String addOrders(@ModelAttribute OrdersDto ordersDto, Model model) {
        ordersService.create(ordersDto);
        model.addAttribute("ordersDto", ordersDto);
        return "redirect:/orders";
    }
    @GetMapping("orders/delete/{id}")
    public String deleteOrders(@PathVariable Long id){
        try{
        ordersService.setCurrentOrder(id);
        ordersService.deleteOne();}
        catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
        return "redirect:/orders";
    }
    @GetMapping("orders/edit/{id}")
    public String editOrders(@PathVariable long id, Model model) {
        try{
        ordersService.setCurrentOrder(id);}
        catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
        model.addAttribute("orders", ordersService.readOne().orElseThrow());
        return "editorders";
    }

    @PostMapping("orders/edit")
    public String editOrders(@ModelAttribute OrdersDto ordersDto, Model model) {
        try { ordersService.setCurrentOrder(ordersDto.getId());
            ordersService.update(ordersDto);
        } catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
        model.addAttribute("client", ordersDto);
        return "redirect:/orders";
    }
    @GetMapping("orders/findorders")
    public String findOrdersWithDishHigherthanKandunderNtotalcost(Model model){
        Collection<OrdersDto> ordersDto=ordersService.findOrdersWithDishHigherthanKandunderNtotalcost();
        model.addAttribute("orderslist", ordersDto);
        return "findall";
    }
}
