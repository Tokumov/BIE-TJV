package tjv.tokumshy_semestrialwork.kazakhcuisine.repositories;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Booking;

@Repository
public interface BookingRepository extends CrudRepository<Booking,Long> {
}
