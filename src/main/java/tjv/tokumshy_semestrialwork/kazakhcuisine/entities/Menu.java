package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Menu implements EntityWithId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name="id_menu")
    private long id;
    @Column(name="name_menu")
    private String name;
    @Column(name="price")
    private Long price;

    @ManyToMany(mappedBy = "orders_Menu")
    private Collection<Orders> menu_orders;

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Long getPrice() {
        return price;
    }

    public void setPrice(Long price) {
        this.price = price;
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
