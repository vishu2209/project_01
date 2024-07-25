package com.example.qss.controller;

import com.example.qss.model.Address;
import com.example.qss.services.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.Optional;

@RestController
@RequestMapping("/api/addresses")
public class AddressController {

    private final AddressService addressService;

    public AddressController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping
    public ResponseEntity<Page<Address>> getAllAddresses(Pageable pageable) {
        Page<Address> addresses = addressService.getAllAddresses(pageable);
        return ResponseEntity.ok(addresses);
    }

    @GetMapping("/{id}")
    public ResponseEntity<Address> getAddressById(@PathVariable Long id) {
        Optional<Address> address = addressService.getAddressById(id);
        return address.map(ResponseEntity::ok).orElseGet(() -> ResponseEntity.notFound().build());
    }

    @PostMapping
    public ResponseEntity<Address> createAddress(@Valid @RequestBody Address address) {
        Address savedAddress = addressService.saveAddress(address);
        return ResponseEntity.status(HttpStatus.CREATED).body(savedAddress);
    }

    @PutMapping("/{id}")
    public ResponseEntity<Address> updateAddress(@PathVariable Long id, @Valid @RequestBody Address addressDetails) {
        Optional<Address> existingAddress = addressService.getAddressById(id);
        if (existingAddress.isPresent()) {
            addressDetails.setId(id);
            Address updatedAddress = addressService.saveAddress(addressDetails);
            return ResponseEntity.ok(updatedAddress);
        }
        return ResponseEntity.notFound().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteAddress(@PathVariable Long id) {
        Optional<Address> existingAddress = addressService.getAddressById(id);
        if (existingAddress.isPresent()) {
            addressService.deleteAddress(id);
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.notFound().build();
    }
}

