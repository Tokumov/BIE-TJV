package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.aspectj.lang.annotation.Before;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

class ClientsControllerTest {
    private MockMvc mockMvc;
    private ClientsController clientsController;
    @BeforeEach
    public void setUp() {
        mockMvc = MockMvcBuilders.standaloneSetup(clientsController).build();
    }
    @Test
    public void testCreateClient() throws Exception {
        Clients k=new Clients(1L,"1","1");
       clientsController.create(k);
    }
}