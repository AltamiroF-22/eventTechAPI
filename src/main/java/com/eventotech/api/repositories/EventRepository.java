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

    @Query(value = "SELECT e.* FROM event e " +
        "LEFT JOIN address a ON e.id = a.event_id " +
        "WHERE e.date >= :currentDate " +
        "AND (CAST(:title AS text) IS NULL OR e.title ILIKE '%' || :title || '%') " +
        "AND (CAST(:city AS text) IS NULL OR a.city ILIKE '%' || :city || '%') " +
        "AND (CAST(:uf AS text) IS NULL OR a.uf ILIKE '%' || :uf || '%') " +
        "AND (CAST(:startDate AS timestamp) IS NULL OR e.date >= CAST(:startDate AS timestamp)) " +
        "AND (CAST(:endDate AS timestamp) IS NULL OR e.date <= CAST(:endDate AS timestamp)) " +
        "ORDER BY e.date ASC", nativeQuery = true)
    public Page<Event> searchEvents(@Param("currentDate")   Date currentDate,
                                    @Param("title")         String title,
                                    @Param("city")          String city,
                                    @Param("uf")            String uf,
                                    @Param("startDate")     Date startDate,
                                    @Param("endDate")       Date endDate,
                                    Pageable                pageable
    );
}