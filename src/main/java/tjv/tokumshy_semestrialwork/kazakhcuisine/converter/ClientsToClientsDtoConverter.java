package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.ClientsDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.stream.Collectors;
@Component
public class ClientsToClientsDtoConverter {
    public ClientsDto convert(Clients clients) {
        ClientsDto clientsDto = new ClientsDto();
        clientsDto.setId(clients.getId());
        clientsDto.setName(clients.getName());
        clientsDto.setSurname(clients.getSurname());


        if (clients.getClient_order() != null) {
            clientsDto.setClient_order(clients.getClient_order().stream()
                    .map(Orders::getId)
                    .collect(Collectors.toList()));
        }

        if (clients.getClient_booking() != null) {
            clientsDto.setClient_booking(clients.getClient_booking().stream()
                    .map(Booking::getId)
                    .collect(Collectors.toList()));
        }

        return clientsDto;
    }
}
