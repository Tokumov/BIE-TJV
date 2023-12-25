package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Collection;

@Entity
public class Orders {
    @Id
    private Long id;
    private int totalcost;
    @ManyToOne
    private Clients orders_client;
    @ManyToMany
    private Collection<Orders> orders_Menu;
}
