package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.MenuService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.MenuRepository;
@RestController
@RequestMapping("/menu")

public class MenuController extends CrudController<Menu,Long, MenuService, MenuRepository> {

    public MenuController(MenuService service){
        super(service);
    }
}
/*@Controller
public class MenuController {
    @GetMapping("/Menu")
    public String Menu(Model model) {
        return "Menu";
    }
}
*/