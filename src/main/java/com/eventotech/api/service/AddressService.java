package com.eventotech.api.service;

import java.util.UUID;
import com.eventotech.api.domain.event.Event;
import org.springframework.stereotype.Service;
import com.eventotech.api.domain.address.Address;
import com.eventotech.api.domain.event.EventRequestDTO;
import com.eventotech.api.repositories.AddressRepository;
import org.springframework.beans.factory.annotation.Autowired;


@Service
public class AddressService {
 
    @Autowired
    private AddressRepository addressRepository;

    public Address createdAddress(EventRequestDTO data, Event event){

        Address newAddress = new Address();

        newAddress.setId(UUID.randomUUID());
        newAddress.setEvent(event);
        newAddress.setCity(data.city());
        newAddress.setUf(data.uf()); 
        
        return addressRepository.save(newAddress);
    }
}