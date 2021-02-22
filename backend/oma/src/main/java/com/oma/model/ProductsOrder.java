package com.oma.model;

import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDate;
import java.util.Objects;
import java.util.List;

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

    @ManyToMany
    @JoinTable(name = "Orders_Products",
            joinColumns = {@JoinColumn(name = "productsOrder_id")},
            inverseJoinColumns = {@JoinColumn(name = "productList_id")})
    private List<OrderItem> basket;

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

    public ProductsOrder(LocalDate creationDate, OrderStatus orderStatus, String orderInfo) {
        this.createdAt = creationDate;
        this.status = orderStatus;
        this.info = orderInfo;
    }

    @Override
    public String toString() {
        return "ProductsOrder{" +
                "id=" + id +
                ", company=" + company +
                ", deliveryPoint=" + deliveryPoint +
                ", products=" + basket +
                ", createdBy=" + createdBy +
                ", approvedBy=" + approvedBy +
                ", status=" + status +
                ", createdAt=" + createdAt +
                ", summaryValue=" + summaryValue +
                ", info='" + info + '\'' +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof ProductsOrder)) return false;
        ProductsOrder order = (ProductsOrder) o;
        return Double.compare(order.summaryValue, summaryValue) == 0 && Objects.equals(deliveryPoint, order.deliveryPoint) && Objects.equals(createdBy, order.createdBy) && Objects.equals(approvedBy, order.approvedBy) && status == order.status && Objects.equals(createdAt, order.createdAt) && Objects.equals(info, order.info);
    }

    @Override
    public int hashCode() {
        return Objects.hash(deliveryPoint, createdBy, approvedBy, status, createdAt, summaryValue, info);
    }

    public void setBasket(List<OrderItem> basket) {
        this.basket = basket;
        summaryValue = basket.stream().mapToDouble(OrderItem::getSummaryValue).sum();
    }

    public void addProductToProducts(OrderItem orderItem){
        basket.add(orderItem);
        summaryValue += orderItem.getSummaryValue();
    }
}
