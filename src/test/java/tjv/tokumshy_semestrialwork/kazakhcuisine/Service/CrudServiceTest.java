package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import static org.mockito.Mockito.*;
import static org.junit.jupiter.api.Assertions.*;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.data.repository.CrudRepository;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.EntityWithId;

import java.util.Optional;

@ExtendWith(MockitoExtension.class)
public class CrudServiceTest {

    @Mock
    private CrudRepository<MyEntity, Long> repository;

    private CrudService<MyEntity, Long, CrudRepository<MyEntity, Long>> crudService;

    @BeforeEach
    public void setUp() {
        crudService = new CrudServiceSubclass(repository);
    }

    @Test
    public void testCreate() {
        MyEntity entity = new MyEntity();
        entity.setId(null);
        when(repository.save(entity)).thenReturn(entity);

        MyEntity created = crudService.create(entity);

        assertEquals(entity, created);
    }

    @Test
    public void testReadAll() {
        Iterable<MyEntity> entities = mock(Iterable.class);
        when(repository.findAll()).thenReturn(entities);
        Iterable<MyEntity> found = crudService.readAll();
        assertEquals(entities, found);
    }

    @Test
    public void testReadById() {
        Long id = 1L;
        MyEntity entity = new MyEntity();
        when(repository.findById(id)).thenReturn(Optional.of(entity));
        Optional<MyEntity> found = crudService.readById(id);
        assertTrue(found.isPresent());
        assertEquals(entity, found.get());
    }

    @Test
    public void testUpdate() {
        Long id = 1L;
        MyEntity entity = new MyEntity();
        entity.setId(id);
        when(repository.existsById(id)).thenReturn(true);
        when(repository.save(entity)).thenReturn(entity);
        assertDoesNotThrow(() -> crudService.update(id, entity));
    }

    @Test
    public void testDeleteById() {
        Long id = 1L;
        when(repository.existsById(id)).thenReturn(true);
        assertDoesNotThrow(() -> crudService.deleteById(id));
    }


    private static class CrudServiceSubclass extends CrudService<MyEntity, Long, CrudRepository<MyEntity, Long>> {
        CrudServiceSubclass(CrudRepository<MyEntity, Long> repository) {
            super(repository);
        }
    }

    private static class MyEntity implements EntityWithId<Long> {
        private Long id;

        public Long getId() {
            return id;
        }

        public void setId(Long id) {
            this.id = id;
        }
    }
}
