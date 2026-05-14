package org.example.eksamenbackend.Model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import jakarta.persistence.*;

import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "orders")
public class Order {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String trackingCode;

    private LocalDate orderedDate;

    private LocalDate expectedDelivery;

    private LocalDate receivedDate;

    @JsonIgnore
    @OneToMany(mappedBy = "order", cascade = CascadeType.ALL)
    private List<OrderLine> orderLines = new ArrayList<>();


    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Order(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getTrackingCode() {
        return trackingCode;
    }

    public void setTrackingCode(String trackingCode) {
        this.trackingCode = trackingCode;
    }

    public LocalDate getOrderedDate() {
        return orderedDate;
    }

    public void setOrderedDate(LocalDate orderedDate) {
        this.orderedDate = orderedDate;
    }

    public LocalDate getExpectedDelivery() {
        return expectedDelivery;
    }

    public void setExpectedDelivery(LocalDate expectedDelivery) {
        this.expectedDelivery = expectedDelivery;
    }

    public LocalDate getReceivedDate() {
        return receivedDate;
    }

    public void setReceivedDate(LocalDate receivedDate) {
        this.receivedDate = receivedDate;
    }

    public Supplier getSupplier(){
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier=supplier;
    }

    public List<OrderLine> getOrderLines() {
        return orderLines;
    }

    // --- hjælpemetode ---

    public void addOrderLine(OrderLine line) {
        orderLines.add(line);
        line.setOrder(this);
    }


}
