package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.OrdersDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityDoesNotExistException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.BookingService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.OrdersService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.OrdersDtoToOrdersConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.OrdersToOrdersDtoConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.OrdersRepository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;
import java.util.function.Function;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

@RestController
@RequestMapping("/orders")
public class OrdersController {
    @Autowired
    private OrdersService ordersService;
    private final OrdersDtoToOrdersConverter dtoToEntityConverter;
    private final OrdersToOrdersDtoConverter entityToDtoConverter;

    @Autowired
    public OrdersController(OrdersService ordersService,
                            OrdersDtoToOrdersConverter dtoToEntityConverter,
                            OrdersToOrdersDtoConverter entityToDtoConverter) {
        this.ordersService = ordersService;
        this.dtoToEntityConverter = dtoToEntityConverter;
        this.entityToDtoConverter = entityToDtoConverter;
    }

    @PostMapping
    public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto ordersDto) throws EntityCannotBeCreatedException {
        try{
        Orders order = dtoToEntityConverter.convert(ordersDto);
        Orders savedOrder = ordersService.create(order);
        OrdersDto savedOrderDto = entityToDtoConverter.convert(savedOrder);
        return new ResponseEntity<>(savedOrderDto, HttpStatus.CREATED);
        }
        catch(EntityCannotBeCreatedException e){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> getOrder(@PathVariable Long id) {
        try{
        Optional<Orders> orderOptional = ordersService.readById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        OrdersDto ordersDto = entityToDtoConverter.convert(orderOptional.get());
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);}
        catch (EntityDoesNotExistException | EntityCannotBeCreatedException exception){
            return new ResponseEntity<>(null, HttpStatus.BAD_REQUEST);
        }
    }

    @GetMapping
    public List<OrdersDto> getAllOrders() {
        List<Orders> ordersList = StreamSupport.stream(ordersService.readAll().spliterator(), false)
                .collect(Collectors.toList());
        return ordersList.stream()
                .map(entityToDtoConverter::convert)
                .collect(Collectors.toList());
    }

    @PutMapping("/{id}")
    public void updateOrder(@PathVariable Long id, @RequestBody OrdersDto ordersDto){
     try{
         ordersService.readById(id).orElseThrow(EntityDoesNotExistException::new);
        Orders orderToUpdate = dtoToEntityConverter.convert(ordersDto);
        ordersService.update(id,orderToUpdate);
        }
        catch(EntityDoesNotExistException exception){
        throw new ResponseStatusException(HttpStatus.BAD_REQUEST);
     }
    }

    @DeleteMapping("/{id}")
    public void deleteOrder(@PathVariable Long id) throws EntityDoesNotExistException {
        ordersService.deleteById(id);
    }
    @GetMapping("/findorders")
    public Collection<OrdersDto>findOrdersWithDishHigherthanKandunderNtotalcost(){
        Collection<OrdersDto> ordersDto=new HashSet<>();
        for(Orders orders:ordersService.findOrdersWithDishHigherthanKandunderNtotalcost()){
            ordersDto.add(entityToDtoConverter.convert(orders));
        }
        return ordersDto;
    }
}

