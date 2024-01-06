package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.BookingDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.ClientsService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;

@Component
public class BookingDtoToBookingConverter {
    private final ClientsService clientService; // If Booking has a reference to a Client entity

    public BookingDtoToBookingConverter(ClientsService clientService) {
        this.clientService = clientService;
    }

    public Booking convert(BookingDto bookingDto) {
        Booking booking = new Booking();
        booking.setTime_of_booking(bookingDto.getTime_of_booking());

        booking.setDate_of_booking(bookingDto.getDate_of_booking());

        if (bookingDto.getBooking_client() != null) {
            clientService.readById(bookingDto.getBooking_client())
                    .ifPresent(booking::setBooking_client);
        }


        return booking;
    }
}
