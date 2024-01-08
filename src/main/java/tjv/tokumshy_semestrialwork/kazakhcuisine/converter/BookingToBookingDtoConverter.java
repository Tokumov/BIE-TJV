package tjv.tokumshy_semestrialwork.kazakhcuisine.converter;

import org.springframework.stereotype.Component;
import tjv.tokumshy_semestrialwork.kazakhcuisine.DTO.BookingDto;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Exception.EntityCannotBeCreatedException;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;
@Component
public class BookingToBookingDtoConverter {
    public BookingDto convert(Booking booking) throws EntityCannotBeCreatedException {
        BookingDto bookingDto = new BookingDto();

        bookingDto.setId(booking.getId());

        bookingDto.setDate_of_booking(booking.getDate_of_booking());
        bookingDto.setTime_of_booking(booking.getTime_of_booking());

        if (booking.getBooking_client() != null) {
            bookingDto.setBooking_client(booking.getBooking_client().getId());
        }
        else{
            throw new EntityCannotBeCreatedException();
        }

        return bookingDto;
    }
}
