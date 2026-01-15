package com.eventotech.api.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import com.eventotech.api.domain.event.Event;
import com.eventotech.api.domain.event.EventRequestDTO;
import com.eventotech.api.service.EventService;


@RestController
@RequestMapping("/api/event")
public class EventController {

    @Autowired
    private EventService eventService;

    @PostMapping
    public ResponseEntity<Event> createEvent(@ModelAttribute EventRequestDTO body) {
        Event createdEvent = eventService.createEvent(body);
        
        return ResponseEntity.ok(createdEvent);
    }
}