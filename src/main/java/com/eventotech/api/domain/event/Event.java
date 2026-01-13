package com.eventotech.api.domain.event;

import lombok.Setter;
import lombok.Getter;
import java.util.Date;
import java.util.UUID;
import jakarta.persistence.Id; 
import lombok.NoArgsConstructor;
import lombok.AllArgsConstructor;
import jakarta.persistence.Table;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;

@Table(name = "event")
@Entity
@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class Event {    

    @Id
    @GeneratedValue()
    private  UUID id;

    private String title;

    private String description;

    private String imgUrl;

    private String eventUrl;

    private Boolean remote;

    private Date date;
}
