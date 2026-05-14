package org.example.eksamenbackend.Controller;

import org.example.eksamenbackend.Model.Component;
import org.example.eksamenbackend.Model.Supplier;
import org.example.eksamenbackend.Repository.ComponentRepository;
import org.example.eksamenbackend.Repository.SupplierRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
public class ComponentController {

    private final ComponentRepository componentRepository;
    private final SupplierRepository supplierRepository;

    public ComponentController(ComponentRepository componentRepository,
                               SupplierRepository supplierRepository) {
        this.componentRepository = componentRepository;
        this.supplierRepository = supplierRepository;
    }

    @GetMapping("/components")
    public List<Component> components() {

        List<Component> components = componentRepository.findAll();
        return components;
    }

    @PostMapping("/components")
    @ResponseStatus(HttpStatus.CREATED)
    public Component postComponent(@RequestBody Component component) {
        System.out.println(component);
        return componentRepository.save(component);
    }

    @PutMapping("/components/{id}")
    public ResponseEntity<Component> putComponent(@PathVariable int id, @RequestBody Component component) {

        Optional<Component> orgComponent = componentRepository.findById(id);

        if (orgComponent.isPresent()) {

            Component updatedComponent = orgComponent.get();

            updatedComponent.setExternalNumber(component.getExternalNumber());
            updatedComponent.setDiscontinued(component.isDiscontinued());
            Supplier supplier = supplierRepository.findById(component.getSupplier().getId())
                    .orElseThrow(() -> new RuntimeException("Supplier not found"));

            updatedComponent.setSupplier(supplier);

            componentRepository.save(updatedComponent);

            return new ResponseEntity<>(updatedComponent, HttpStatus.OK);
        }
        return ResponseEntity.notFound().build();
    }
}