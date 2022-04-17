package models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "master", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Master {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "master_name")
    private String masterName;
    @Column(name = "salary")
    private int salary;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "warehouse_id")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "master", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;

    public Master(String masterName, int salary, Warehouse warehouse) {
        this.masterName = masterName;
        this.salary=salary;
        this.warehouse=warehouse;
        orders=new ArrayList<>();

    }

    @Override
    public String toString() {
        return String.format("\nid: %d || Мастер: %s || Зарплата: %s || Склад: %s", id, masterName, salary, warehouse.getId());
    }
}
