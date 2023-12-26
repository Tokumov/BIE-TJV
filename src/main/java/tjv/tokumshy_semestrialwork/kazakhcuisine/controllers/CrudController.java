package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.data.repository.CrudRepository;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.server.ResponseStatusException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.CrudService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.EntityWithId;

public abstract class CrudController<E extends EntityWithId<ID>,ID, S extends CrudService<E, ID, R>, R extends CrudRepository <E,ID>>{
    protected S service;
    public CrudController(S service){
        this.service=service;
    }
    @PostMapping
    @ResponseBody
    public E create(E data){
        return service.create(data);
    }
    public Iterable<E> readAll(){
        return service.readAll();
    }
    @GetMapping ("/{id}")
    @ResponseBody
    public E readById(@PathVariable ID id){
        var optE=service.readById(id);
        if(optE.isPresent())
            return optE.get();
        else
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
    }
}
