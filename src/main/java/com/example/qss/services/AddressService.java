
package com.example.qss.services;

import com.example.qss.model.Address;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.Optional;

public interface AddressService {
    Page<Address> getAllAddresses(Pageable pageable);

    Optional<Address> getAddressById(Long id);

    Address saveAddress(Address address);

    void deleteAddress(Long id);
}
