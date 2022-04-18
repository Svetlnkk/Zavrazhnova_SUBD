package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "warehouse", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "wh_id")
    private int id;
    @Column(name = "numb_of_repairedequipment")
    private int numbOfEquipment;
    @Column(name = "numb_details")
    private int numbOfDetails;

    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Master> master;
    @OneToMany(mappedBy = "warehouse", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Details> details;
    public Warehouse(int numbOfDetails, int numbOfEquipment){
        this.numbOfDetails=numbOfDetails;
        this.numbOfEquipment=numbOfEquipment;
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || Количество техники: %s || Количество деталей: %s" ,  id, numbOfEquipment, numbOfDetails);
    }
}
