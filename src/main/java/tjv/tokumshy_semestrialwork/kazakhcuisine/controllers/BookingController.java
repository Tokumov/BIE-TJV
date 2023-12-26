package tjv.tokumshy_semestrialwork.kazakhcuisine.controllers;


import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import tjv.tokumshy_semestrialwork.kazakhcuisine.Service.BookingService;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.BookingRepository;
@RestController
@RequestMapping("/booking")

public class BookingController extends CrudController<Booking,Long, BookingService, BookingRepository> {

    public BookingController(BookingService service){
        super(service);
    }
}
