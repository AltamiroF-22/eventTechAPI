package com.eventotech.api.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eventotech.api.domain.coupon.Coupon;
import com.eventotech.api.domain.coupon.CouponRequestDTO;
import com.eventotech.api.domain.event.Event;
import com.eventotech.api.repositories.CouponRepository;
import com.eventotech.api.repositories.EventRepository;

import java.util.Date;
import java.util.UUID;


@Service 
public class CouponService {

    @Autowired
    private CouponRepository couponRepository;

    @Autowired
    private EventRepository eventRepository;

    public Coupon addCouponToEvent(UUID eventId, CouponRequestDTO couponData ) {
         Event event = eventRepository.findById(eventId)
            .orElseThrow(() -> new IllegalArgumentException("Event not found"));

        // Verificar se já existe um coupon com o mesmo código para este evento
        if (couponRepository.existsByCodeAndEvent(couponData.code(), event)) {
            throw new IllegalArgumentException("Coupon code already exists for this event");
        }

        // Verificar se a data de validade é no futuro
        Date validDate = new Date(couponData.valid());
        if (validDate.before(new Date())) {
            throw new IllegalArgumentException("Coupon valid date must be in the future");
        }
            
        Coupon coupon = new Coupon();
        coupon.setCode(couponData.code());
        coupon.setDiscount(couponData.discount());
        coupon.setValid(validDate);
        coupon.setEvent(event);

        return couponRepository.save(coupon);
    }


}