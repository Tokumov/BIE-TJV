package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.MenuRepository;

public class MenuService extends  CrudService<Menu,Long, MenuRepository> {
    public MenuService(MenuRepository repository){
        super(repository);
    }
}
