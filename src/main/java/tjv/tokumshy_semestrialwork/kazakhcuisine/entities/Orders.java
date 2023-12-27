package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Orders implements EntityWithId<Long> {
    @Id
    @Column(name="id_orders")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="total_cost")
    private Long totalcost;
    @ManyToOne
    @JoinColumn(name="client_id_orders")
    private Clients orders_client;
    @ManyToMany
    @JoinTable(
            name="menu_and_orders",
            joinColumns = @JoinColumn(name="orders_id_menu"),
            inverseJoinColumns = @JoinColumn(name="menu_id_orders")
    )
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
