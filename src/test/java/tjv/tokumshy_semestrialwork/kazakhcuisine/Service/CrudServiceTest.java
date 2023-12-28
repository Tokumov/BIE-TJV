package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;
import org.mockito.Mockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;

@SpringBootTest
class CrudServiceUnitTest {
    @MockBean
    private ClientRepository clientRepository;
    @Autowired
    private CrudServiceSubclass service;
    @Test
    void createIdDoesNotExist() {
        Clients newClient = new Clients(1L,"2","3");
        Mockito.when(clientRepository.existsById(newClient.getId())).thenReturn(false);
        Mockito.when(clientRepository.save(newClient)).thenReturn(newClient);

        var ret = service.create(newClient);

        Assertions.assertEquals(newClient, ret);
        Mockito.verify(clientRepository, Mockito.atLeastOnce()).save(newClient);
    }
}
