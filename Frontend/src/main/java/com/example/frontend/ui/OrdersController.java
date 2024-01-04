package com.example.frontend.ui;

import com.example.frontend.modeldto.MenuDto;
import com.example.frontend.modeldto.OrdersDto;
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
    public String orderslist(Model model) {
        model.addAttribute("orderslist", ordersService.readAll());
        return "orders";
    }
    private Long temp;

    public Long getTemp() {
        return temp;
    }

    public void setTemp(Long temp) {
        this.temp = temp;
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
        ordersService.setCurrentOrder(id);
        ordersService.deleteOne();
        return "redirect:/orders";
    }
    @GetMapping("orders/edit/{id}")
    public String editOrders(@PathVariable long id, Model model) {
        ordersService.setCurrentOrder(id);
        model.addAttribute("orders", ordersService.readOne().orElseThrow());
        return "editOrders";
    }

    @PostMapping("orders/edit")
    public String editOrders(@ModelAttribute OrdersDto ordersDto, Model model) {
        ordersService.setCurrentOrder(ordersDto.getId());
        try {
            ordersService.update(ordersDto);
        } catch (BadRequestException e) {
            System.out.println("There is nothing bro");
        }
        model.addAttribute("client", ordersDto);
        return "redirect:/orders";
    }
}
