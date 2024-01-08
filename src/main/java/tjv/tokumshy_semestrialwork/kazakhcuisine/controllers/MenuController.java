package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.MenuDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityDoesNotExistException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.MenuService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.MenuDtoToMenuConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.MenuToMenuDtoConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.MenuRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/menu")

public class MenuController {
    @Autowired
    private MenuService menuService;
    private final MenuDtoToMenuConverter dtoToEntityConverter;
    private final MenuToMenuDtoConverter entityToDtoConverter;

    @Autowired
    public MenuController(MenuService menuService,
                          MenuDtoToMenuConverter dtoToEntityConverter,
                          MenuToMenuDtoConverter entityToDtoConverter) {
        this.menuService = menuService;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @PostMapping
    public ResponseEntity<MenuDto> createMenu(@RequestBody MenuDto menuDto) throws EntityCannotBeCreatedException {
            try{ Menu menu = dtoToEntityConverter.convert(menuDto);
        Menu savedMenu = menuService.create(menu);
        MenuDto savedMenuDto = entityToDtoConverter.convert(savedMenu);
        return new ResponseEntity<>(savedMenuDto, HttpStatus.CREATED);
       } catch(EntityCannotBeCreatedException e){
        return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<MenuDto> getMenu(@PathVariable Long id) {
        Optional<Menu> menuOptional = menuService.readById(id);
        if (!menuOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        MenuDto menuDto = entityToDtoConverter.convert(menuOptional.get());
        return new ResponseEntity<>(menuDto, HttpStatus.OK);
    }

    @GetMapping
    public List<MenuDto> getAllMenus() {
        List<Menu> menuList = StreamSupport.stream(menuService.readAll().spliterator(), false)
                .collect(Collectors.toList());
        return menuList.stream()
                .map(entityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void updateMenu(@PathVariable Long id, @RequestBody MenuDto menuDto) throws EntityDoesNotExistException {
        try{menuService.readById(id).orElseThrow(EntityDoesNotExistException::new);
        Menu menuToUpdate = dtoToEntityConverter.convert(menuDto);
        menuService.update(id,menuToUpdate);}
        catch(EntityDoesNotExistException entityDoesNotExistException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }

    @DeleteMapping("/{id}")
    public void deleteMenu(@PathVariable Long id) throws EntityDoesNotExistException {
        try{
        menuService.deleteById(id);}
        catch (EntityDoesNotExistException entityDoesNotExistException){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }
    }
}