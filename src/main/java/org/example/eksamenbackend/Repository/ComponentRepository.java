package org.example.eksamenbackend.Repository;

import org.example.eksamenbackend.Model.Component;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ComponentRepository extends JpaRepository<Component, Integer> {
}
