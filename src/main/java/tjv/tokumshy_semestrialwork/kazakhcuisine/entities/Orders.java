package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;

import java.util.Collection;

@Entity
public class Orders {
    @Id
    private Long id;
    private int totalcost;
    @ManyToMany
    private Collection<Orders> toMenu;
}
