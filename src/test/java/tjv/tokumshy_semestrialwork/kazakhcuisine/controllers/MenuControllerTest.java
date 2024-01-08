package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.web.context.WebApplicationContext;

import com.fasterxml.jackson.databind.ObjectMapper;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.MenuDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
class MenuControllerIntegrationTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private WebApplicationContext webApplicationContext;

    @Autowired
    private ObjectMapper objectMapper;

    @BeforeEach
    public void setUp() {
        mockMvc = webAppContextSetup(webApplicationContext).build();
    }

    @Test
    public void testCreateMenu() throws Exception {
        MenuDto menuDto = new MenuDto("Soup",100L);
        mockMvc.perform(post("/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetMenu() throws Exception {
        Long menuId = 1L;
        mockMvc.perform(get("/menu/" + menuId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllMenus() throws Exception {
        mockMvc.perform(get("/menu"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateMenu() throws Exception {
        Long menuId = 1L;
        MenuDto menuDto = new MenuDto("Burger",70L);
        mockMvc.perform(put("/menu/" + menuId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteMenu() throws Exception {
        Long menuId = 1L;
        mockMvc.perform(delete("/menu/" + menuId))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateMenu_InvalidData() throws Exception {
        MenuDto invalidMenuDto = new MenuDto();
        mockMvc.perform(post("/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(invalidMenuDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testGetMenu_NotFound() throws Exception {
        Long nonExistentMenuId = 999L;
        mockMvc.perform(get("/menu/" + nonExistentMenuId))
                .andExpect(status().isNotFound());
    }


    @Test
    public void testUpdateMenu_NotFound() throws Exception {
        Long nonExistentMenuId = 999L;
        MenuDto menuDto = new MenuDto();
        mockMvc.perform(put("/menu/" + nonExistentMenuId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testDeleteMenu_NotFound() throws Exception {
        Long nonExistentMenuId = 999L;
        mockMvc.perform(delete("/menu/" + nonExistentMenuId))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testCreateMenu_IncompleteData() throws Exception {
        MenuDto incompleteMenuDto = new MenuDto();
        mockMvc.perform(post("/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(incompleteMenuDto)))
                .andExpect(status().isBadRequest());
    }


}