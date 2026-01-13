package com.eventotech.api.repositories;

import java.util.UUID; 
import com.eventotech.api.domain.coupon.Coupon;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CouponRepository extends JpaRepository<Coupon, UUID> {
    
}