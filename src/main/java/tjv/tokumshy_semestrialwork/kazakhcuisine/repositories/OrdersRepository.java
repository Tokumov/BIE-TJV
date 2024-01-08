package tjv.tokumshy_semestrialwork.kazakhcuisine.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.Collection;
@Repository
public interface OrdersRepository extends CrudRepository<Orders,Long> {


 @Query("SELECT o FROM Orders o JOIN o.orders_Menu m JOIN o.orders_client c " +
         "WHERE m.price > :dishPrice AND o.totalcost < :totalCost")
    Collection<Orders> findOrdersWithDishHigherthanKandunderNtotalcost(
              @Param("dishPrice") Long dishPrice,
              @Param("totalCost") Long totalCost);
}
