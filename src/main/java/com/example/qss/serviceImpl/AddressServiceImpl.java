package com.example.qss.serviceImpl;

import com.example.qss.model.Address;
import com.example.qss.repository.AddressRepository;
import com.example.qss.services.AddressService;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AddressServiceImpl implements AddressService {

    private final AddressRepository addressRepository;

    public AddressServiceImpl(AddressRepository addressRepository) {
        this.addressRepository = addressRepository;
    }

    @Override
    public Page<Address> getAllAddresses(Pageable pageable) {
        return addressRepository.findAll(pageable);
    }

    @Override
    public Optional<Address> getAddressById(Long id) {
        return addressRepository.findById(id);
    }

    @Override
    public Address saveAddress(Address address) {
        return addressRepository.save(address);
    }

    @Override
    public void deleteAddress(Long id) {
        addressRepository.deleteById(id);
    }
}