package org.example.eksamenbackend.Model;

import jakarta.persistence.*;

@Entity
public class BomLine {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int quantity;

    @ManyToOne
    @JoinColumn(name="bom_id")
    private BillOfMaterials bom;

    @ManyToOne
    @JoinColumn(name = "component_id")
    private Component component;

    public BomLine() {}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public BillOfMaterials getBom() {
        return bom;
    }

    public void setBom(BillOfMaterials bom) {
        this.bom = bom;
    }

    public Component getComponent() {
        return component;
    }

    public void setComponent(Component component) {
        this.component = component;
    }
}
