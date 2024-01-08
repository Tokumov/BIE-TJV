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
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.ClientsDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.MenuDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.OrdersDto;

import java.util.Arrays;
import java.util.HashSet;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class OrdersControllerTest {

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
    public void testCreateOrderWithDependencies() throws Exception {

        ClientsDto client = new ClientsDto();
        client.setName("John");
        client.setSurname("Doe");

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());


        MenuDto menuItem = new MenuDto();
        menuItem.setName("Pizza");
        menuItem.setPrice(20L);

        mockMvc.perform(post("/menu")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(menuItem)))
                .andExpect(status().isCreated());


        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setTotalcost(500L);
        ordersDto.setOrders_client(1L);
        ordersDto.setOrders_Menu(new HashSet<>(Arrays.asList(1L)));
        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordersDto)))
                .andExpect(status().isCreated());
    }

    @Test
    public void testGetOrder() throws Exception {
        Long orderId = 1L;
        mockMvc.perform(get("/orders/" + orderId))
                .andExpect(status().isOk());
    }

    @Test
    public void testGetAllOrders() throws Exception {
        mockMvc.perform(get("/orders"))
                .andExpect(status().isOk());
    }

    @Test
    public void testUpdateOrder() throws Exception {
        ClientsDto client = new ClientsDto();
        client.setName("Test");
        client.setSurname("Test2");
        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());
        Long orderId = 1L;
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setTotalcost(100L);
        ordersDto.setOrders_client(2L);
        ordersDto.setOrders_Menu(new HashSet<>(Arrays.asList(1L)));
        mockMvc.perform(put("/orders/" + orderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordersDto)))
                .andExpect(status().isOk());
    }

    @Test
    public void testDeleteOrder() throws Exception {
        Long orderId = 1L;
        mockMvc.perform(delete("/orders/" + orderId))
                .andExpect(status().isOk());
    }

    @Test
    public void testFindOrdersWithDishHigherthanKandunderNtotalcost() throws Exception {
        mockMvc.perform(get("/orders/findorders"))
                .andExpect(status().isOk());
    }
    @Test
    public void testCreateOrder_NonExistingClient() throws Exception {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setTotalcost(300L);
        ordersDto.setOrders_client(999L);
        ordersDto.setOrders_Menu(new HashSet<>(Arrays.asList(1L)));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordersDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testCreateOrder_NonExistingMenuItem() throws Exception {
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setTotalcost(300L);
        ordersDto.setOrders_client(1L);
        ordersDto.setOrders_Menu(new HashSet<>(Arrays.asList(999L)));

        mockMvc.perform(post("/orders")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordersDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testUpdateNonExistingOrder() throws Exception {
        Long nonExistingOrderId = 999L;
        OrdersDto ordersDto = new OrdersDto();
        ordersDto.setTotalcost(600L);
        ordersDto.setOrders_client(1L);
        ordersDto.setOrders_Menu(new HashSet<>(Arrays.asList(1L)));

        mockMvc.perform(put("/orders/" + nonExistingOrderId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(ordersDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testDeleteNonExistingOrder() throws Exception {
        Long nonExistingOrderId = 999L;

        mockMvc.perform(delete("/orders/" + nonExistingOrderId))
                .andExpect(status().isBadRequest());
    }


}