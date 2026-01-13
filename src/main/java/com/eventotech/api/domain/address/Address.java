package com.eventotech.api.domain.address;

import lombok.Setter;
import lombok.Getter;
import java.util.UUID;
import jakarta.persistence.Id; 
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.JoinColumn;
import com.eventotech.api.domain.event.Event;

@Table(name = "address")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Address {    

    @Id
    private  UUID id;

    private String city;

    private String uf;

    @ManyToOne
    @JoinColumn(name = "event_id", nullable = false)
    private Event event;

}
