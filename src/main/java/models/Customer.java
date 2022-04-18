package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;

@Entity
@Table(name = "customer", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Customer {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "customer_id")
    private int id;
    @Column(name = "customer_name")
    private String customerName;
    @Column(name = "customer_phone")
    private int phoneNumb;
    @OneToMany(mappedBy = "customer", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;
    public Customer(String customerName, int phoneNumb){
        this.customerName=customerName;
        this.phoneNumb=phoneNumb;
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || имя: %s || номер телефона: %s ",
                id, customerName, phoneNumb);
    }
}
