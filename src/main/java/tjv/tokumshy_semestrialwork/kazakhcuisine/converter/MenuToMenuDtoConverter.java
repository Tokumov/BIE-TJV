package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.MenuDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.stream.Collectors;
@Component
public class MenuToMenuDtoConverter {
    private MenuDto menuDto;
    public MenuToMenuDtoConverter(){
    menuDto=new MenuDto();
    }
    public MenuDto convert(Menu menu) {
        if(menu==null){
            throw new EntityCannotBeCreatedException();
        }
        menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setPrice(menu.getPrice());
        if (menu.getMenu_orders() != null) {
            menuDto.setMenu_orders(menu.getMenu_orders().stream().map(Orders::getId).collect(Collectors.toList()));
        } else {
            menuDto.setMenu_orders(new HashSet<>());
        }
        return menuDto;
    }
}
