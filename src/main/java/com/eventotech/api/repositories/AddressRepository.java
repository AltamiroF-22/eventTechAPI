package com.eventotech.api.repositories;

import java.util.UUID; 
import com.eventotech.api.domain.address.Address;
import org.springframework.data.jpa.repository.JpaRepository;

public interface AddressRepository extends JpaRepository<Address, UUID> {
    
}