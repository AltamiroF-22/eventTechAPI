package com.eventotech.api.repositories;

import java.util.UUID; 
import com.eventotech.api.domain.event.Event;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EventRepository extends JpaRepository<Event, UUID> {

}