package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Service;

import java.util.Optional;


public abstract class CrudService <E, ID, R extends CrudRepository<E, ID>>{
    protected  R repository;
    public CrudService(R repository){
        this.repository=repository;
    }
    public E create(E data){

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
    repository.deleteById(id);
    }
}
