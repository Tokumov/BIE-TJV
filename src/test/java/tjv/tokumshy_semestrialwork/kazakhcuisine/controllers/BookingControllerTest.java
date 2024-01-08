package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import static org.springframework.test.web.servlet.setup.MockMvcBuilders.*;

import com.fasterxml.jackson.databind.ObjectMapper;
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
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.BookingDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.ClientsDto;

@ExtendWith(SpringExtension.class)
@SpringBootTest
@AutoConfigureMockMvc
public class BookingControllerTest {

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
    public void testCreateBookingWithClient() throws Exception {

        ClientsDto client = new ClientsDto();
        client.setName("Jane");
        client.setSurname("Doe");

        mockMvc.perform(post("/clients")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(client)))
                .andExpect(status().isCreated());
        BookingDto bookingDto = new BookingDto();
        bookingDto.setDate_of_booking("01-01-2023");
        bookingDto.setTime_of_booking("12:00");
        bookingDto.setBooking_client(1L);

        mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isCreated());
    }


    @Test
    public void testCreateBooking_NonExistingClient() throws Exception {
        BookingDto bookingDto = new BookingDto();
        bookingDto.setDate_of_booking("2023-01-01");
        bookingDto.setTime_of_booking("13:00");
        bookingDto.setBooking_client(999L);

        mockMvc.perform(post("/booking")
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isBadRequest());
    }


    @Test
    public void testGetBooking() throws Exception {
        Long bookingId = 1L;
        mockMvc.perform(get("/booking/" + bookingId))
                .andExpect(status().isOk());
    }


    @Test
    public void testUpdateBooking() throws Exception {
        Long bookingId = 1L;
        BookingDto bookingDto = new BookingDto();
        bookingDto.setDate_of_booking("2023-01-02");
        bookingDto.setTime_of_booking("14:00");
        bookingDto.setBooking_client(1L);

        mockMvc.perform(put("/booking/" + bookingId)
                        .contentType(MediaType.APPLICATION_JSON)
                        .content(objectMapper.writeValueAsString(bookingDto)))
                .andExpect(status().isOk());
    }


    @Test
    public void testDeleteBooking() throws Exception {
        Long bookingId = 1L;
        mockMvc.perform(delete("/booking/" + bookingId))
                .andExpect(status().isOk());
    }


    @Test
    public void testGetAllBookings() throws Exception {
        mockMvc.perform(get("/booking"))
                .andExpect(status().isOk());
    }
}