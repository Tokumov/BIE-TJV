package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
@Controller
public class MenuController {
    @GetMapping("/Menu")
    public String Menu(Model model) {
        return "Menu";
    }
}
