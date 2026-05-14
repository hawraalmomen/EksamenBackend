package org.example.eksamenbackend.Controller;

import org.example.eksamenbackend.Model.BillOfMaterials;
import org.example.eksamenbackend.Repository.BillOfMaterialsRepository;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/assemblies")
public class AssembliesController {

    private final BillOfMaterialsRepository repo;

    public AssembliesController(BillOfMaterialsRepository repo) {
        this.repo = repo;
    }

    // get /assemblies
    @GetMapping
    public List<BillOfMaterials> getAssemblies() {
        return repo.findAll();
    }

    // get /assemblies/{id}
    @GetMapping("/{id}")
    public ResponseEntity<?> getAssemblyById(@PathVariable int id) {

        return repo.findById(id)
                .<ResponseEntity<?>>map(ResponseEntity::ok)
                .orElseGet(() -> ResponseEntity
                        .status(HttpStatus.NOT_FOUND)
                        .body("Assembly findes ikke"));
    }

    // post /assemblies
    @PostMapping
    public ResponseEntity<?> createAssembly(@RequestBody BillOfMaterials bom) {

        if (bom.getName() == null || bom.getName().isBlank()) {
            return ResponseEntity
                    .badRequest()
                    .body("Navn må ikke være tomt");
        }

        // blot for at undgå duplicates
        boolean exists = repo.findAll().stream()
                .anyMatch(a -> a.getName().equalsIgnoreCase(bom.getName()));

        if (exists) {
            return ResponseEntity
                    .status(HttpStatus.CONFLICT)
                    .body("Assembly findes allerede");
        }

        BillOfMaterials saved = repo.save(bom);

        return ResponseEntity
                .status(HttpStatus.CREATED)
                .body(saved);
    }


}
