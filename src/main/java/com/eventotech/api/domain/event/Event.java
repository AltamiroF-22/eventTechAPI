package com.eventotech.api.domain.event;

import lombok.Setter;
import lombok.Getter;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Id; 
import jakarta.persistence.OneToOne;
import jakarta.persistence.CascadeType;
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.Column;
import com.eventotech.api.domain.address.Address;

@Table(name = "event")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {    

    @Id
    private  UUID id;

    private String title;

    private String description;

    @Column(name = "img_url")
    private String imgUrl;

    @Column(name = "event_url")
    private String eventUrl;

    private Boolean remote;

    private Date date;
    
    @OneToOne(mappedBy = "event", cascade = CascadeType.ALL)
    private Address address;
}
