package models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "details", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Details {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "id_detail")
    private int id;
    @Column(name = "detail_type")
    private String detailType;
    @Column(name = "numb_of_detail")
    private int numbOfDetail;
    @Column(name = "price_detail")
    private int detailPrice;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "wh_numb")
    private Warehouse warehouse;
    @OneToMany(mappedBy = "detail", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<Orders> orders;
    public Details(String detailType, int numbOfDetail, int detailPrice, Warehouse warehouse){
        this.detailType=detailType;
        this.numbOfDetail=numbOfDetail;
        this.detailPrice=detailPrice;
        this.warehouse=warehouse;
        orders=new ArrayList<>();
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || тип детали: %s || количество: %s || цена: %s || склад: %s ",
                id, detailType, numbOfDetail, detailPrice, warehouse.getId());
    }
}
