package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.EntityWithId;

import java.util.Optional;


public abstract class CrudService <E extends EntityWithId<ID>, ID, R extends CrudRepository<E, ID>>{
    protected  R repository;
    public CrudService(R repository){
        this.repository=repository;
    }
    public E create(E data){
        if(repository.existsById(data.getId()))
           throw new EntityCannotBeCreatedException();
        return repository.save(data);
    }
    public Iterable<E> readAll(){
        return repository.findAll();

    }
    public Optional<E> readById(ID id){
        return repository.findById(id);

    }
    public void update(ID id, E data){
        repository.save(data);
    }
    public void deleteById(ID id){
        if(!repository.existsById(id))
            throw new EntityDoesNotExistException();
    repository.deleteById(id);
    }
}
