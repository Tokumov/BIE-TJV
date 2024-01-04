package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.MenuDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.stream.Collectors;
@Component
public class MenuToMenuDtoConverter {
    public MenuDto convert(Menu menu) {
        MenuDto menuDto = new MenuDto();
        menuDto.setId(menu.getId());
        menuDto.setName(menu.getName());
        menuDto.setPrice(menu.getPrice());
        menuDto.setMenu_orders(menu.getMenu_orders().stream().map(Orders::getId).collect(Collectors.toList()));
        return menuDto;
    }
}
