package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.util.Collection;
import java.util.HashSet;

@Entity
@Table(name = "menu")
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

    public Collection<Orders> getMenu_orders() {
        return menu_orders;
    }

    public void setMenu_orders(Collection<Orders> menu_orders) {
        this.menu_orders = menu_orders;
    }

    public Menu(){

    }
    public Menu(Long id, String name, Long price){
        this.id=id;
        this.name=name;
        this.price=price;
        this.menu_orders=new HashSet<>();
    }


    public Menu(Long id, String name, Long price, Collection<Orders> menu_orders){
        this.id=id;
        this.name=name;
        this.price=price;
        this.menu_orders=menu_orders;
    }
    public Menu(Long id) {

        this.id = id;
    }
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
