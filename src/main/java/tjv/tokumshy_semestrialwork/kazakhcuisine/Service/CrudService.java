package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.data.repository.CrudRepository;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityDoesNotExistException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.EntityWithId;

import java.util.Optional;


public abstract class CrudService <E extends EntityWithId<ID>, ID, R extends CrudRepository<E, ID>>{
    protected  R repository;
    public CrudService(R repository){
        this.repository=repository;
    }
    public E create(E data){
        System.out.println("yeap you get it123123");
        ID id =data.getId();
        if(id!=null && repository.existsById(data.getId())){
            System.out.println("Entity cannot be created bruh");
           throw new EntityCannotBeCreatedException();}
        return repository.save(data);
    }
    public Iterable<E> readAll(){
        return repository.findAll();

    }
    public Optional<E> readById(ID id){
        return repository.findById(id);

    }
    public void update(ID id, E data){
        if(!repository.existsById(id))
            throw new EntityDoesNotExistException();
        data.setId(id);
        repository.save(data);
    }
    public void deleteById(ID id){
        if(!repository.existsById(id))
            throw new EntityDoesNotExistException();
    repository.deleteById(id);
    }
}
