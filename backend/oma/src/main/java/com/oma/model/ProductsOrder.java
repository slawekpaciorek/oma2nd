package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.EnableMBeanExport;

import javax.persistence.*;
import java.time.LocalDate;

@Data
@NoArgsConstructor
@Entity
public class ProductsOrder {

    @Id
    @GeneratedValue
    private long id;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "Company_id", referencedColumnName = "id")
    private Company company;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "DeliveryPoint_id", referencedColumnName = "id")
    private DeliveryPoint deliveryPoint;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "ProductList_id", referencedColumnName = "id")
    private ProductList products;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "Operator_id", referencedColumnName = "id")
    private User createdBy;

    @ManyToOne(cascade = {
            CascadeType.DETACH,
            CascadeType.MERGE,
            CascadeType.PERSIST,
            CascadeType.REFRESH
    })
    @JoinColumn(name = "Manager_id", referencedColumnName = "id")
    private User approvedBy;

    @Enumerated(EnumType.STRING)
    private OrderStatus status;

    @Column
    private LocalDate createdAt;

    @Column
    private double summaryValue;

    @Column
    private String info;

    @Override
    public String toString() {
        return "ProductsOrder{" +
                "id=" + id +
                ", company=" + company +
                ", deliveryPoint=" + deliveryPoint +
                ", products=" + products +
                ", createdBy=" + createdBy +
                ", approvedBy=" + approvedBy +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", summaryValue=" + summaryValue +
                ", info='" + info + '\'' +
                '}';
    }
}
