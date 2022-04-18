package models;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;
import lombok.Getter;
import lombok.Setter;

import java.util.Date;
import java.util.List;
import java.util.ArrayList;
import javax.persistence.*;

@Entity
@Table(name = "orders", schema = "public", catalog = "test")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Orders {
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Id
    @Column(name = "orders_id")
    private int id;
    @Column(name = "date_order")
    private Date orderDate;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_service")
    private Service service;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "master_id")
    private Master master;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_customer")
    private Customer customer;

    @Column(name = "price")
    private Integer price;
    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "id_detail")
    private Details detail;
    public Orders(Service service, Master master, Customer customer, Date orderDate, Integer price, Details detail){
        this.service=service;
        this.master=master;
        this.customer=customer;
        this.orderDate=orderDate;
        this.price=price;
        this.detail=detail;
    }
    @Override
    public String toString() {
        return String.format("\nid: %d || дата: %s || услуга: %s || мастер: %s || заказчик: %s ||  цена: %s || деталь: %s",
                id,orderDate, service, master, customer,  price, detail);
    }
}
