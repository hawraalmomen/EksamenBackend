package org.example.eksamenbackend.Controller;

import org.example.eksamenbackend.Model.Component;
import org.example.eksamenbackend.Model.Order;
import org.example.eksamenbackend.Model.OrderLine;
import org.example.eksamenbackend.Repository.ComponentRepository;
import org.example.eksamenbackend.Repository.OrderLineRepository;
import org.example.eksamenbackend.Repository.OrderRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;



@RestController
@RequestMapping("/orderlines")
public class OrderLineController {

    private final OrderLineRepository orderLineRepository;
    private final OrderRepository orderRepository;
    private final ComponentRepository componentRepository;

    public OrderLineController(OrderLineRepository orderLineRepository,
                               OrderRepository orderRepository,
                               ComponentRepository componentRepository) {
        this.orderLineRepository = orderLineRepository;
        this.orderRepository = orderRepository;
        this.componentRepository = componentRepository;
    }

    @PostMapping("/{orderId}/lines")
    public ResponseEntity<?> addOrderLine(@PathVariable int orderId,
                                          @RequestBody OrderLineRequest request) {

        Order order = orderRepository.findById(orderId)
                .orElse(null);

        Component component = componentRepository.findById(request.componentId())
                .orElse(null);

        if (order == null || component == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order eller Component findes ikke");
        }

        if (order.getReceivedDate() != null) {
            return ResponseEntity.status(HttpStatus.FORBIDDEN)
                    .body("Order er allerede afsluttet");
        }

        OrderLine line = new OrderLine();
        line.setOrder(order);
        line.setComponent(component);
        line.setQuantity(request.quantity());

        return ResponseEntity.status(HttpStatus.CREATED)
                .body(orderLineRepository.save(line));
    }

    @GetMapping("/{orderId}/lines")
    public ResponseEntity<?> getOrderLines(@PathVariable int orderId) {

        Order order = orderRepository.findById(orderId)
                .orElse(null);

        if (order == null) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND)
                    .body("Order findes ikke");
        }

        return ResponseEntity.ok(order.getOrderLines());
    }

    public record OrderLineRequest(
            int componentId,
            int quantity
    ) {
    }
}