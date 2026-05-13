package org.example.eksamenbackend.Repository;

import org.example.eksamenbackend.Model.Order;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderRepository extends JpaRepository<Order, Integer> {
}
