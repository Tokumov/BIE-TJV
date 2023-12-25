package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;
import jakarta.persistence.ManyToMany;
import jakarta.persistence.ManyToOne;

import java.util.Collection;

@Entity
public class Orders implements EntityWithId<Long> {
    @Id
    private Long id;
    private int totalcost;
    @ManyToOne
    private Clients orders_client;
    @ManyToMany
    private Collection<Menu> orders_Menu;

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long aLong) {
        id=aLong;
    }
}
