package tjv.tokumshy_semestrialwork.kazakhcuisine.entities;

import jakarta.persistence.Entity;
import jakarta.persistence.Id;

@Entity
public class Clients {
    @Id
    private Long idClients;
    private String nameClient;
    private String surnameClient;

}
