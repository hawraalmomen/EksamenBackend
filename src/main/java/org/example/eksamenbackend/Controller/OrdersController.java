package org.example.eksamenbackend.Controller;

import org.example.eksamenbackend.Model.Order;
import org.example.eksamenbackend.Model.Supplier;
import org.example.eksamenbackend.Repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.example.eksamenbackend.Repository.SupplierRepository;

import java.util.List;
import java.util.Optional;

@RestController
public class OrdersController {

    private final SupplierRepository supplierRepository;
    private final OrderRepository orderRepository;

    public OrdersController(OrderRepository orderRepository,
                            SupplierRepository supplierRepository) {

        this.orderRepository = orderRepository;
        this.supplierRepository = supplierRepository;
    }



    @GetMapping("/orders")
    public List<Order> orders() {
        return orderRepository.findAll();
    }


    @PostMapping("/orders")
    @ResponseStatus(HttpStatus.CREATED)
    public Order postOrder(@RequestBody Order order) {
        Supplier supplier = supplierRepository.findById(order.getSupplier().getId())
                .orElseThrow(() -> new RuntimeException("Supplier not found"));

        order.setSupplier(supplier);

        return orderRepository.save(order);
    }

    @PutMapping("/orders/{id}")
    public ResponseEntity<Order> putOrder(@PathVariable int id,
                                          @RequestBody Order order) {

        Optional<Order> orgOrder = orderRepository.findById(id);

        if (orgOrder.isPresent()) {

            Order updatedOrder = orgOrder.get();

            // Ordren er allerede modtaget → må ikke ændres
            if (updatedOrder.getReceivedDate() != null) {
                return ResponseEntity.status(HttpStatus.FORBIDDEN).build();
            }

            Supplier supplier = supplierRepository.findById(order.getSupplier().getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));


            updatedOrder.setTrackingCode(order.getTrackingCode());
            updatedOrder.setOrderedDate(order.getOrderedDate());
            updatedOrder.setExpectedDelivery(order.getExpectedDelivery());
            updatedOrder.setReceivedDate(order.getReceivedDate());
            updatedOrder.setSupplier(supplier);


            return ResponseEntity.ok(orderRepository.save(updatedOrder));
        }

        return ResponseEntity.notFound().build();
    }

        @DeleteMapping("/order/{id}")
        public ResponseEntity<String> deleteOrder(@PathVariable int id) {

        if (!orderRepository.existsById(id)) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("#Order not found");
        }

        orderRepository.deleteById(id);
        return ResponseEntity.ok("Order deleted");
    }


}
