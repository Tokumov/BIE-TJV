package tjv.tokumshy_semestrialwork.kazakhcuisine.Service;

import org.springframework.stereotype.Service;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.BookingRepository;
@Service
public class BookingService extends CrudService<Booking,Long, BookingRepository>{
    public BookingService(BookingRepository repository){
        super(repository);
    }
}
