package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.http.HttpStatus.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.http.ResponseEntity;

import org.springframework.web.server.ResponseStatusException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.controllers.ClientsController;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.ClientsDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.ClientsService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.ClientsDtoToClientsConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.ClientsToClientsDtoConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityDoesNotExistException;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

public class ClientsControllerTest {

    @Mock
    private ClientsService clientsService;

    @Mock
    private ClientsDtoToClientsConverter dtoToEntityConverter;

    @Mock
    private ClientsToClientsDtoConverter entityToDtoConverter;

    @InjectMocks
    private ClientsController clientsController;

    @BeforeEach
    public void setUp() {
        MockitoAnnotations.openMocks(this);
    }

    @Test
    public void testCreateClient() throws EntityCannotBeCreatedException {
        ClientsDto clientsDto = new ClientsDto(1L,"Andrew","Johnson",new HashSet<>(),new HashSet<>());
        Clients client = new Clients(1L,"Andrew","Johnson");
        assertEquals(dtoToEntityConverter.convert(clientsDto),client);
        when(clientsService.create(client)).thenReturn(client);
        when(entityToDtoConverter.convert(client)).thenReturn(clientsDto);

        ResponseEntity<ClientsDto> response = clientsController.createClient(clientsDto);
        assertEquals(OK, response.getStatusCode());
        assertEquals(clientsDto, response.getBody());
    }
    @Test
    public void testGetClient_Success() {

        Long clientId = 1L;
        ClientsDto clientsDto = new ClientsDto(clientId,"Andrew","Johnson",new HashSet<>(),new HashSet<>());
        Clients client = new Clients(clientId,"Andrew","Johnson");
        when(clientsService.readById(clientId)).thenReturn(Optional.of(client));
        when(entityToDtoConverter.convert(client)).thenReturn(clientsDto);


        ResponseEntity<ClientsDto> response = clientsController.getClient(clientId);


        assertEquals(OK, response.getStatusCode());
        assertEquals(clientsDto, response.getBody());
    }
    @Test
    public void testGetClient_NotFound() {

        Long clientId = 1L;
        when(clientsService.readById(clientId)).thenReturn(Optional.empty());


        ResponseEntity<ClientsDto> response = clientsController.getClient(clientId);


        assertEquals(NOT_FOUND, response.getStatusCode());
    }



    @Test
    public void testUpdateClient_NotFound() {

        Long clientId = 1L;
        ClientsDto clientsDto = new ClientsDto(1L,"Andrew","Johnson",new HashSet<>(),new HashSet<>());
        when(clientsService.readById(clientId)).thenReturn(Optional.empty());


        assertThrows(ResponseStatusException.class, () -> clientsController.updateClient(clientId, clientsDto));
    }

    @Test
    public void testGetAllClients() {
        List<Clients> clientsList = new ArrayList<>();
        List<ClientsDto> clientsDtoList = new ArrayList<>();
        when(clientsService.readAll()).thenReturn(clientsList);
        when(entityToDtoConverter.convert(any(Clients.class))).thenAnswer(invocation -> clientsDtoList.get(0));
        List<ClientsDto> response = clientsController.getAllClients();
        assertEquals(clientsDtoList, response);
    }

    @Test
    public void testDeleteClient_Success() throws EntityDoesNotExistException {

        Long clientId = 1L;
        doNothing().when(clientsService).deleteById(clientId);


        assertDoesNotThrow(() -> clientsController.deleteClient(clientId));
    }

    @Test
    public void testDeleteClient_NotFound() {

        Long clientId = 1L;
        doThrow(EntityDoesNotExistException.class).when(clientsService).deleteById(clientId);


        assertThrows(ResponseStatusException.class, () -> clientsController.deleteClient(clientId));
    }


}