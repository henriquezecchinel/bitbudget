package com.zecchinel.bitbudget.controller;

import com.zecchinel.bitbudget.dto.OwnerDto;
import com.zecchinel.bitbudget.model.Owner;
import com.zecchinel.bitbudget.repository.OwnerRepository;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/owners")
public class OwnerController {

    private final OwnerRepository ownerRepository;

    public OwnerController(OwnerRepository ownerRepository) {
        this.ownerRepository = ownerRepository;
    }

    @GetMapping
    public List<OwnerDto> getAllOwners() {
        return ownerRepository.findAll().stream()
                .map(owner -> new OwnerDto(owner.getName()))
                .collect(Collectors.toList());
    }

    @GetMapping("/{id}")
    public ResponseEntity<OwnerDto> getOwner(@PathVariable Long id) {
        Optional<Owner> owner = ownerRepository.findById(id);
        return owner.map(o -> ResponseEntity.ok(new OwnerDto(o.getName())))
                .orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public OwnerDto createOwner(@RequestBody Owner owner) {
        Owner newOwner = new Owner();
        newOwner.setName(owner.getName());

        Owner savedOwner = ownerRepository.save(newOwner);
        return new OwnerDto(savedOwner.getName());
    }

    @PutMapping("/{id}")
    public ResponseEntity<Owner> updateOwner(@PathVariable Long id, @RequestBody Owner ownerDetails) {
        Optional<Owner> owner = ownerRepository.findById(id);
        if (owner.isPresent()) {
            ownerDetails.setId(id);
            return ResponseEntity.ok(ownerRepository.save(ownerDetails));
        } else {
            return ResponseEntity.notFound().build();
        }
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteOwner(@PathVariable Long id) {
        if (ownerRepository.existsById(id)) {
            ownerRepository.deleteById(id);
            return ResponseEntity.noContent().build();
        } else {
            return ResponseEntity.notFound().build();
        }
    }
}