package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Collection;

@Entity
public class Menu {
    @Id
    private long idMenu;
    private String nameMenu;
    private int price;

    @ManyToOne
    private Clients clientsInfo;
    @ManyToMany
    private Collection<Orders> menuInfo;
}
