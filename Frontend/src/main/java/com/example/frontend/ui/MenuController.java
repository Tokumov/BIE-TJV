package com.example.frontend.ui;

import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.modeldto.MenuDto;
import com.example.frontend.service.ClientsService;
import com.example.frontend.service.MenuService;
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

import java.util.NoSuchElementException;

@Controller
public class MenuController {
    @Autowired
    private MenuService menuService;
    @GetMapping("/menu")
    public String menulist(Model model) {
        model.addAttribute("menulist", menuService.readAll());
        return "menu";
    }
    @GetMapping("/menu/add")
    public String addMenu(Model model) {
        model.addAttribute("menuDto", new MenuDto());

        return "addMenu";
    }

    @PostMapping("/menu/add")
    public String addMenu(@ModelAttribute MenuDto menuDto, Model model) {
        menuService.create(menuDto);
        return "redirect:/menu";
    }
    @GetMapping("menu/delete/{id}")
    public String deleteMenu(@PathVariable Long id){
        try{
        menuService.setCurrentMenu(id);
        menuService.deleteOne();}
        catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
        return "redirect:/menu";
    }
    @GetMapping("menu/edit/{id}")
    public String editMenu(@PathVariable long id, Model model) {
        try{
        menuService.setCurrentMenu(id);
        model.addAttribute("menu", menuService.readOne().orElseThrow(NoSuchElementException::new));
            return "editmenu";}
        catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be updated");
        }

    }

    @PostMapping("menu/edit")
    public String editMenu(@ModelAttribute MenuDto menu, Model model) {
        try {
            menuService.setCurrentMenu(menu.getId());
            menuService.update(menu);
            model.addAttribute("client", menu);
            return "redirect:/menu";
        } catch (BadRequestException | NoSuchElementException noSuchElementException) {
            throw new ResponseStatusException(HttpStatus.CONFLICT, "entity can't be created");
        }
    }
}
