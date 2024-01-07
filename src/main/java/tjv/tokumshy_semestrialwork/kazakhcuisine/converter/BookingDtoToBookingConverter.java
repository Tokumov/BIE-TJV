package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.BookingDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.ClientsService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;

@Component
public class BookingDtoToBookingConverter {
    @Autowired
    private ClientsService clientService;

    public BookingDtoToBookingConverter(ClientsService clientService) {
        this.clientService = clientService;
    }

    public Booking convert(BookingDto bookingDto) throws EntityCannotBeCreatedException {
        Booking booking = new Booking();
        booking.setTime_of_booking(bookingDto.getTime_of_booking());

        booking.setDate_of_booking(bookingDto.getDate_of_booking());
        if(booking.getDate_of_booking()==null || booking.getTime_of_booking()==null){
            throw new EntityCannotBeCreatedException();
        }
        if (bookingDto.getBooking_client() != null) {
            clientService.readById(bookingDto.getBooking_client()).orElseThrow(EntityCannotBeCreatedException::new);
            clientService.readById(bookingDto.getBooking_client())
                    .ifPresent(booking::setBooking_client);
        }
        else{
            throw new EntityCannotBeCreatedException();
        }


        return booking;
    }
}
