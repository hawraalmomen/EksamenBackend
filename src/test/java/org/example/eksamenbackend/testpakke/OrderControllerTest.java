package org.example.eksamenbackend.testpakke;

import org.example.eksamenbackend.Model.Order;
import org.example.eksamenbackend.Repository.OrderRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertNotNull;

@SpringBootTest
public class OrderControllerTest {

    @Autowired
    OrderRepository orderRepository;

    @Test
    void testFindAllOrders() {
        List<Order> orders = orderRepository.findAll();

        assertNotNull(orders);

        assertFalse(orders.isEmpty());
    }
}
