package org.example.eksamenbackend.Controller;


import org.example.eksamenbackend.Model.Component;
import org.example.eksamenbackend.Model.Order;
import org.example.eksamenbackend.Model.OrderLine;
import org.example.eksamenbackend.Repository.ComponentRepository;
import org.example.eksamenbackend.Repository.OrderLineRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;


import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
public class InventoryController {

    private final OrderLineRepository orderLineRepository;

    private final ComponentRepository componentRepository;

    public InventoryController(OrderLineRepository orderLineRepository, ComponentRepository componentRepository) {
        this.orderLineRepository = orderLineRepository;
        this.componentRepository = componentRepository;

    }



    @GetMapping("/inventory")
    public Map<String, Integer> getInventory() {
        List<OrderLine> lines = orderLineRepository.findAll();

        Map<String, Integer> inventory = new HashMap<>();

        for (OrderLine line : lines) {
            String componentName = line.getComponent().getExternalNumber();
            int quantity = line.getQuantity();

            inventory.put(componentName, inventory.getOrDefault(componentName, 0) + quantity
            );
        }
        return inventory;
    }

    @PostMapping("/inventory")
    public ResponseEntity<?> addInventory(@RequestBody InventoryRequest request) {

        Component component = componentRepository.findById(request.componentId())
                .orElseThrow(() -> new RuntimeException("Component not found"));

        OrderLine line = new OrderLine();
        line.setComponent(component);
        line.setQuantity(request.quantity());

        orderLineRepository.save(line);

        return ResponseEntity.status(HttpStatus.CREATED).body("Inventory registered");
    }

    public record InventoryRequest(
            int componentId,
            int quantity
    ) {
    }
}
