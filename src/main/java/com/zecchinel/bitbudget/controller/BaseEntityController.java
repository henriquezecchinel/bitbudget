package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.model.BaseEntity;
import com.zecchinel.bitbudget.repository.BaseEntityRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

public abstract class BaseEntityController<T extends BaseEntity, D> {

    protected abstract BaseEntityRepository<T> getRepository();

    protected abstract D convertToDto(T entity);

    @GetMapping
    public List<D> getAll() {
        return getRepository().findAll().stream()
                .map(entity -> convertToDto((T) entity))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<D> get(@PathVariable Long id) {
        return getRepository().findById(id)
                .map(entity -> ResponseEntity.ok(convertToDto((T) entity)))
                .orElse(ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<?> create(@RequestBody T entity) {
        try {
            // TODO: Validate and sanitize entity
            T savedEntity = getRepository().save(entity);
            return ResponseEntity.ok(convertToDto(savedEntity));
        } catch (Exception e) {
            // TODO: Improve error handling
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @PutMapping("/{id}")
    public ResponseEntity<T> update(@PathVariable Long id, @RequestBody T entityDetails) {
        if (getRepository().existsById(id)) {
            entityDetails.setId(id);
            // TODO: Validate and sanitize entityDetails
            return ResponseEntity.ok(getRepository().save(entityDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        if (getRepository().existsById(id)) {
            getRepository().deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}