package models;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "warehouse", schema = "public", catalog = "lab4")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Warehouse {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id")
    private int id;
    @Column(name = "numb_of_details")
    private int numbOfDetails;
    @Column(name = "numb_of_equipment")
    private int numbOfEquipment;
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
        return String.format("\nid: %d || iмя: %s || Фамилия: %s" ,  id, numbOfDetails, numbOfEquipment);
    }
}
