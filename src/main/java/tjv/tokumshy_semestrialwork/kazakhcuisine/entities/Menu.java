package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.*;

import java.util.Collection;

@Entity
public class Menu implements EntityWithId<Long> {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long idMenu;
    private String nameMenu;
    private Long price;

    @Override
    public Long getId() {
        return idMenu;
    }

    @Override
    public void setId(Long aLong) {
        idMenu=aLong;
    }
}
