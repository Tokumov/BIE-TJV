package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.ClientsDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.OrdersService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.BookingService;
import java.util.Optional;
import java.util.stream.Collectors;
@Component
public class ClientsDtoToClientsConverter {

    private static OrdersService ordersService;
    private static BookingService bookingService;

    public ClientsDtoToClientsConverter(OrdersService ordersService, BookingService bookingService) {
        this.ordersService = ordersService;
        this.bookingService = bookingService;
    }

    public static Clients convert(ClientsDto clientsDto) {
        Clients clients = new Clients();
        clients.setId(clientsDto.getId());
        clients.setName(clientsDto.getName());
        clients.setSurname(clientsDto.getSurname());
        if(clients.getName()==null || clients.getSurname()==null){
            throw new EntityCannotBeCreatedException();
        }

        if (clientsDto.getClient_order() != null) {
            clients.setClient_order(clientsDto.getClient_order().stream()
                    .map(ordersId -> ordersService.readById(ordersId))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet()));
        }

        if (clientsDto.getClient_booking() != null) {
            clients.setClient_booking(clientsDto.getClient_booking().stream()
                    .map(bookingId -> bookingService.readById(bookingId))
                    .filter(Optional::isPresent)
                    .map(Optional::get)
                    .collect(Collectors.toSet()));
        }

        return clients;
    }
}