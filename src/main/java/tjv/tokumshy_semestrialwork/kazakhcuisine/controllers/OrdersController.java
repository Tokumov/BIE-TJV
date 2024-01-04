package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.OrdersDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.BookingService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.OrdersService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.OrdersDtoToOrdersConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.converter.OrdersToOrdersDtoConverter;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.OrdersRepository;

import java.util.Collection;
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
    public ResponseEntity<OrdersDto> createOrder(@RequestBody OrdersDto ordersDto) {
        Orders order = dtoToEntityConverter.convert(ordersDto);
        Orders savedOrder = ordersService.create(order);
        OrdersDto savedOrderDto = entityToDtoConverter.convert(savedOrder);
        return new ResponseEntity<>(savedOrderDto, HttpStatus.CREATED);
    }

    @GetMapping("/{id}")
    public ResponseEntity<OrdersDto> getOrder(@PathVariable Long id) {
        Optional<Orders> orderOptional = ordersService.readById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        OrdersDto ordersDto = entityToDtoConverter.convert(orderOptional.get());
        return new ResponseEntity<>(ordersDto, HttpStatus.OK);
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
    public void updateOrder(@PathVariable Long id, @RequestBody OrdersDto ordersDto) {
        Optional<Orders> existingOrderOptional = ordersService.readById(id);
        Orders orderToUpdate = dtoToEntityConverter.convert(ordersDto);
        ordersService.update(id,orderToUpdate);

    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOrder(@PathVariable Long id) {
        Optional<Orders> orderOptional = ordersService.readById(id);
        if (!orderOptional.isPresent()) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
        ordersService.deleteById(orderOptional.get().getId());
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }
}

