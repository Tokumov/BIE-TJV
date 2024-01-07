package tjv.tokumshy_semestrialwork.kazakhcuisine.repositories;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;

import static org.junit.jupiter.api.Assertions.*;
@DataJpaTest
class OrdersRepositoryTest {
    @Autowired
    private OrdersRepository ordersRepository;
    @Autowired
    private MenuRepository menuRepository;

    @Test
    void findOrdersWithThreeDishesUnder15Euros() {
    }

    @Test
    void findOrdersWithDishHigherthanKandunderNtotalcost() {
    }
}