package tjv.tokumshy_semestrialwork.kazakhcuisine.repositories;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Clients;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Menu;
import tjv.tokumshy_semestrialwork.kazakhcuisine.entities.Orders;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.ClientRepository;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.MenuRepository;
import tjv.tokumshy_semestrialwork.kazakhcuisine.repositories.OrdersRepository;

import java.util.Arrays;
import java.util.Collection;
import java.util.Collections;
import java.util.HashSet;

@ExtendWith(SpringExtension.class)
@DataJpaTest
public class OrdersRepositoryTest {

    @Autowired
    private OrdersRepository ordersRepository;

    @Autowired
    private ClientRepository clientRepository;

    @Autowired
    private MenuRepository menuRepository;

    @BeforeEach
    public void setUp() {
        Clients client = new Clients();
        client.setName("Test Client");
        client.setSurname("Test Surname");
        clientRepository.save(client);

        Menu expensiveMenu = new Menu();
        expensiveMenu.setName("Expensive Dish");
        expensiveMenu.setPrice(100L);
        menuRepository.save(expensiveMenu);

        Menu cheapMenu = new Menu();
        cheapMenu.setName("Cheap Dish");
        cheapMenu.setPrice(30L);
        menuRepository.save(cheapMenu);

        Orders orderWithExpensiveDish = new Orders();
        orderWithExpensiveDish.setTotalcost(400L);
        orderWithExpensiveDish.setOrders_client(client);
        orderWithExpensiveDish.setOrders_Menu(new HashSet<>(Collections.singletonList(expensiveMenu)));
        ordersRepository.save(orderWithExpensiveDish);

        Orders orderWithCheapDish = new Orders();
        orderWithCheapDish.setTotalcost(600L);
        orderWithCheapDish.setOrders_client(client);
        orderWithCheapDish.setOrders_Menu(new HashSet<>(Collections.singletonList(cheapMenu)));
        ordersRepository.save(orderWithCheapDish);
    }

    @Test
    public void testFindOrdersWithDishHigherthanKandunderNtotalcost() {
        Long testDishPrice = 50L;
        Long testTotalCost = 500L;

        Collection<Orders> foundOrders = ordersRepository.findOrdersWithDishHigherthanKandunderNtotalcost(testDishPrice, testTotalCost);


        assertThat(foundOrders).isNotEmpty();
        assertThat(foundOrders).allMatch(order -> order.getTotalcost() < testTotalCost);
        assertThat(foundOrders).anyMatch(order -> order.getOrders_Menu().stream().anyMatch(menu -> menu.getPrice() > testDishPrice));
    }
}