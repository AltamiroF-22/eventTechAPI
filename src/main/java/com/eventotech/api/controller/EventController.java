package com.eventotech.api.controller;

import java.util.List;
import com.eventotech.api.domain.event.Event;
import com.eventotech.api.service.EventService;
import org.springframework.http.ResponseEntity;
import com.eventotech.api.domain.event.EventRequestDTO;
import com.eventotech.api.domain.event.EventResponseDTO;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.beans.factory.annotation.Autowired;


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

    @GetMapping()
    public ResponseEntity<List<EventResponseDTO>> getUpcomingEvents(
            @RequestParam(defaultValue = "0") Integer page, 
            @RequestParam(defaultValue = "10") Integer size) {

        List<EventResponseDTO> allEvents = this.eventService.getUpcomingEvents(page, size);

        return ResponseEntity.ok(allEvents);
    }
}