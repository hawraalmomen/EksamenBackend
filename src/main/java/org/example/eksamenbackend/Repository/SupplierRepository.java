package org.example.eksamenbackend.Repository;

import org.example.eksamenbackend.Model.Supplier;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface SupplierRepository  extends JpaRepository<Supplier, Integer> {
    List<Supplier> findSupplierByName(String name);
}
