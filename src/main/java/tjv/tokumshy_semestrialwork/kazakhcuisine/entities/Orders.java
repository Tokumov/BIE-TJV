package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.Objects;
import java.util.Set;

@Entity
@Table(name = "orders")
public class Orders implements EntityWithId<Long> {
    @Id
    @Column(name="id_orders")
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @Column(name="total_cost")
    private Long totalcost;
    @ManyToOne(cascade = CascadeType.REFRESH, fetch = FetchType.LAZY)
    @JoinColumn(name="client_id_orders")
    private Clients orders_client;
    @ManyToMany(fetch = FetchType.LAZY, cascade = {
            CascadeType.MERGE
    })
    @JoinTable(
            name="menu_and_orders",
            joinColumns = @JoinColumn(name="orders_id_menu"),
            inverseJoinColumns = @JoinColumn(name="menu_id_orders")
    )
    private Set<Menu> orders_Menu;

    @Override
    public String toString() {
        return "Orders{" +
                "id=" + id +
                ", totalcost=" + totalcost +
                ", orders_client=" + orders_client +
                ", orders_Menu=" + orders_Menu +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Orders orders = (Orders) o;
        return Objects.equals(id, orders.id);
    }
    public Orders(Long id, Long totalcost, Clients orders_client, Set<Menu> orders_Menu){
        this.id=id;
        this.totalcost=totalcost;
        this.orders_client=orders_client;
        this.orders_Menu=orders_Menu;

    }
    public Orders(){

    }
    @Override
    public int hashCode() {
        return Objects.hash(id);
    }

    public Long getTotalcost() {
        return totalcost;
    }

    public void setTotalcost(Long totalcost) {
        this.totalcost = totalcost;
    }

    public Clients getOrders_client() {
        return orders_client;
    }

    public void setOrders_client(Clients orders_client) {
        this.orders_client = orders_client;
    }

    public Collection<Menu> getOrders_Menu() {
        return orders_Menu;
    }

    public void setOrders_Menu(Set<Menu> orders_Menu) {
        this.orders_Menu = orders_Menu;
    }

    @Override
    public Long getId() {
        return id;
    }

    @Override
    public void setId(Long aLong) {
        id=aLong;
    }
}
