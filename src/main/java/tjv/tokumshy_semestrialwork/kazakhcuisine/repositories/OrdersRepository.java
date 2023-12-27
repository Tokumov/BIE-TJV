package tjv.tokumshy_semestrialwork.kazakhcuisine.repositories;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.data.repository.query.Param;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;

import java.util.Collection;

public interface OrdersRepository extends CrudRepository<Orders,Long> {
    @Query("SELECT o FROM Orders o JOIN o.orders_Menu m WHERE " +
            "o.orders_Menu.size = 3 AND m.price < 15 GROUP BY o HAVING COUNT(m) = 3")
    Collection<Orders> findOrdersWithThreeDishesUnder15Euros();
    @Query("SELECT o FROM Orders o JOIN o.orders_Menu m WHERE " +
            "m.price < :maxPrice GROUP BY o HAVING COUNT(m) = :numberOfDishes")
    Collection<Orders> findOrdersWithNDishesUnderKPrice(@Param("numberOfDishes") long numberOfDishes,
                                                        @Param("maxPrice") int maxPrice);
    /*@Query("SELECT o FROM Orders o JOIN o.orders_Menu m WHERE " +
            "m.price < 15 GROUP BY o HAVING COUNT(m) = 3")*/
}
