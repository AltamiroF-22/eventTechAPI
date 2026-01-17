package com.eventotech.api.controller;

import org.springframework.http.ResponseEntity;
import org.springframework.http.HttpStatus;
import com.eventotech.api.domain.coupon.Coupon;
import com.eventotech.api.service.CouponService;
import com.eventotech.api.domain.coupon.CouponRequestDTO;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.beans.factory.annotation.Autowired;
import jakarta.validation.Valid;

import java.util.UUID;


@RestController
@RequestMapping("/api/coupon")
public class CouponController {
    
    @Autowired
    private CouponService couponService;


    @PostMapping("/event/{event_id}")
    public ResponseEntity<?> addCouponToEvent(@PathVariable UUID event_id, @Valid @RequestBody CouponRequestDTO data) {

        try {
            Coupon coupon = couponService.addCouponToEvent(event_id, data);

            return ResponseEntity.status(HttpStatus.CREATED).body(coupon);
            
        } catch (IllegalArgumentException e) {
            return ResponseEntity.badRequest().body(e.getMessage());
        }
    }

    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<String> handleIllegalArgument(IllegalArgumentException e) {
        return ResponseEntity.badRequest().body(e.getMessage());
    }
}