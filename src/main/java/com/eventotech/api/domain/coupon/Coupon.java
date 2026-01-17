package com.eventotech.api.domain.coupon;

import lombok.Setter;
import lombok.Getter;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Id;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import com.eventotech.api.domain.event.Event;

@Table(name = "coupon")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Coupon {    

    @Id
    @GeneratedValue(strategy = GenerationType.UUID)
    private  UUID id;

    private String code;

    private Integer discount;

    private Date valid;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;
}
