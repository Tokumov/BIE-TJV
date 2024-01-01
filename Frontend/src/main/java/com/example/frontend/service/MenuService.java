package com.example.frontend.service;

import com.example.frontend.clientAPI.ClientsClient;
import com.example.frontend.clientAPI.MenuClient;
import com.example.frontend.modeldto.ClientsDto;
import com.example.frontend.modeldto.MenuDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Collection;
import java.util.Optional;
@Service
public class MenuService {
    @Autowired
    private MenuClient menuClient;
    MenuService(){

    }
    public MenuService(MenuClient menuClient) {
        this.menuClient = menuClient;
    }

    public void create(MenuDto e) {
        menuClient.create(e);
    }

    public Collection<MenuDto> readAll() {
        return menuClient.readAll();
    }

    public Optional<MenuDto> readOne() {
        return menuClient.readOne();
    }

    public void setCurrentMenu(long id) {
        menuClient.setCurrent(id);
    }


    public void update(MenuDto e) {
        menuClient.updateOne(e);
    }

    public void deleteOne() {
        menuClient.deleteOne();
    }
}
