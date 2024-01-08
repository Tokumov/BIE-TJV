package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.MenuDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.OrdersService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;

import java.util.HashSet;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class MenuDtoToMenuConverter {
    private final OrdersService ordersService;


    public MenuDtoToMenuConverter(OrdersService ordersService) {
        this.ordersService = ordersService;
    }

    public Menu convert(MenuDto menuDto) {
        Menu menu = new Menu();
        menu.setId(menuDto.getId());
        menu.setName(menuDto.getName());
        menu.setPrice(menuDto.getPrice());
        if(menu.getName()==null || menu.getPrice()==null){
            throw new EntityCannotBeCreatedException();
        }
        if (menuDto.getMenu_orders() != null) {
            menu.setMenu_orders(menuDto.getMenu_orders().stream()
                    .map(ordersService::readById)
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet()));
        }
        else{
            menu.setMenu_orders(new HashSet<>());
        }
        return menu;
    }
}
