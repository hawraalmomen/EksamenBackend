package org.example.eksamenbackend.Model;

import jakarta.persistence.*;


@Entity
public class Component {
    @Id
    private int internalNumber;
    private String externalNumber;
    private boolean discontinued;
    private boolean ordereable;

    @ManyToOne
    @JoinColumn(name = "supplier_id")
    private Supplier supplier;

    public Component() {}

    public int getInternalNumber() {
        return internalNumber;
    }

    public void setInternalNumber(int internalNumber) {
        this.internalNumber = internalNumber;
    }

    public String getExternalNumber() {
        return externalNumber;
    }

    public void setExternalNumber(String externalNumber) {
        this.externalNumber = externalNumber;
    }

    public boolean isDiscontinued() {
        return discontinued;
    }

    public void setDiscontinued(boolean discontinued) {
        this.discontinued = discontinued;
    }

    public boolean isOrdereable() {
        return ordereable;
    }

    public void setOrdereable(boolean ordereable) {
        this.ordereable = ordereable;
    }

    public Supplier getSupplier(){
        return supplier;
    }

    public void setSupplier(Supplier supplier) {
        this.supplier=supplier;
    }

}
