package com.eventotech.api.repositories;

import java.util.UUID; 
import java.util.Date;
import org.springframework.data.domain.Page;
import com.eventotech.api.domain.event.Event;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.Query; 
import org.springframework.data.repository.query.Param;
import org.springframework.data.jpa.repository.JpaRepository;


public interface EventRepository extends JpaRepository<Event, UUID> {

    @Query("SELECT e FROM Event e WHERE e.date >= :currentDate ORDER BY e.date ASC")
    public Page<Event> findUpcomingEvents(@Param("currentDate") Date currentDate, Pageable pageable);
}