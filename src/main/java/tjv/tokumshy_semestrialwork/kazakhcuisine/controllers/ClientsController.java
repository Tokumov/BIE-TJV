package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.ClientsDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityDoesNotExistException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.ClientsService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.ClientsDtoToClientsConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.ClientsToClientsDtoConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/clients")
public class ClientsController {
    @Autowired
    private ClientsService clientsService;
    private final ClientsDtoToClientsConverter dtoToEntityConverter;
    private final ClientsToClientsDtoConverter entityToDtoConverter;

    @Autowired
    public ClientsController(ClientsService clientsService,
                             ClientsDtoToClientsConverter dtoToEntityConverter,
                             ClientsToClientsDtoConverter entityToDtoConverter) {
        this.clientsService = clientsService;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @PostMapping
    public ResponseEntity<ClientsDto> createClient(@RequestBody ClientsDto clientsDto) throws EntityCannotBeCreatedException {
        Clients client = dtoToEntityConverter.convert(clientsDto);
        Clients savedClient = clientsService.create(client);
        ClientsDto savedClientDto = entityToDtoConverter.convert(savedClient);
        return new ResponseEntity<>(savedClientDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<ClientsDto> getClient(@PathVariable Long id) {
        Optional<Clients> clientOptional = clientsService.readById(id);
        if (!clientOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ClientsDto clientsDto = entityToDtoConverter.convert(clientOptional.get());
        return new ResponseEntity<>(clientsDto, HttpStatus.OK);
    }

    @PutMapping("/{id}")
    public void updateClient(@PathVariable Long id, @RequestBody ClientsDto clientsDto) throws EntityDoesNotExistException {
        try{clientsService.readById(id).orElseThrow(EntityDoesNotExistException::new);
        Clients clientToUpdate = dtoToEntityConverter.convert(clientsDto);
        clientsService.update(id,clientToUpdate);}
        catch (EntityDoesNotExistException e){

        }

    }

    @GetMapping
    public List<ClientsDto> getAllClients() {
        List<Clients> clientsList = StreamSupport.stream(clientsService.readAll().spliterator(), false)
                .collect(Collectors.toList());
        return clientsList.stream()
                .map(entityToDtoConverter::convert)
                .collect(Collectors.toList());
    }



    @DeleteMapping("/{id}")
    public void deleteClient(@PathVariable Long id) throws EntityDoesNotExistException {
        try{
        clientsService.deleteById(id);}
        catch (EntityDoesNotExistException e){
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
        }

    }
}
