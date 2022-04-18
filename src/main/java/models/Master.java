package models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "master", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Master {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_master")
    private int id;
    @Column(name = "master_name")
    private String masterName;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_numb")
    private Warehouse warehouse;
    @Column(name = "master_salary")
    private int salary;
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
        return String.format("\nid: %d || Мастер: %s || Склад: %s || Зарплата: %s ", id, masterName, warehouse.getId(), salary);
    }
}
