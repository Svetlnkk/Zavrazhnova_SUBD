package models;
import javax.persistence.*;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
@Entity
@Table(name = "service", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Service {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "type_of_service")
    private String typeOfService;
    @Column(name = "price")
    private int price;
    @OneToMany(mappedBy = "service", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;
    public Service(String typeOfService, int price){
        this.typeOfService=typeOfService;
        this.price=price;
        orders=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || тип услуги: %s || цена: %s ",
                id, typeOfService, price);
    }
}
