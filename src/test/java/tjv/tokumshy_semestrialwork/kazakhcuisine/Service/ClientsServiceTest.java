package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

@SpringBootTest
class ClientsServiceTest {
    @Autowired
    private ClientsService clientService;

    @MockBean
    private ClientRepository clientRepository;

    @Test
    public void testCreate() throws Exception {
        ClientRepository mockClientRepository = mock(ClientRepository.class);

        Clients expectedClient = new Clients(1L,"asd","asd");
        when(mockClientRepository.save(expectedClient)).thenReturn(expectedClient);

        ClientsService testClass = new ClientsService(mockClientRepository);

        Clients actualClient = testClass.create(expectedClient);

        assertEquals(expectedClient, actualClient);

        verify(mockClientRepository).save(expectedClient);
    }
}