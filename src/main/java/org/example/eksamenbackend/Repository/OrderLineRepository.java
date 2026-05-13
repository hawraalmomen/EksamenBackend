package org.example.eksamenbackend.Repository;

import org.example.eksamenbackend.Model.OrderLine;
import org.springframework.data.jpa.repository.JpaRepository;

public interface OrderLineRepository extends JpaRepository<OrderLine,Integer> {
}
