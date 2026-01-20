package com.eventotech.api.service;

import java.util.Map;
import java.util.UUID;
import java.util.List;
import java.util.Date; 
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;
import com.eventotech.api.domain.event.Event;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.data.domain.Pageable;
import com.eventotech.api.config.CloudinaryConfig;
import org.springframework.data.domain.PageRequest;
import org.springframework.web.multipart.MultipartFile;
import com.eventotech.api.domain.event.EventRequestDTO;
import com.eventotech.api.domain.event.EventResponseDTO;
import com.eventotech.api.repositories.EventRepository;
import org.springframework.beans.factory.annotation.Autowired;

@Service
public class EventService {

    @Autowired
    private Cloudinary cloudinary;
    
    @Autowired
    private CloudinaryConfig cloudinaryConfig;
    
    @Autowired
    private EventRepository eventRepository;

    public Event createEvent(EventRequestDTO data){

        String imgUrl = null;

        if(!data.image().isEmpty()){
            imgUrl = uploadImageAndGetUrl(data.image());
        }

        Event newEvent = new Event();
        newEvent.setId(UUID.randomUUID());
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        
      
        if (data.date() != null) {
            newEvent.setDate(new Date(data.date()));
        } else {
            newEvent.setDate(new Date()); 
        }
        
        newEvent.setRemote(data.remote());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setImgUrl(imgUrl);
        
        return eventRepository.save(newEvent);
    }

    public List<EventResponseDTO> getUpcomingEvents(Integer page, Integer size){
        Pageable pageable = PageRequest.of(page, size);

        Page<Event> eventsPage = eventRepository.findUpcomingEvents(new Date(), pageable);

        return eventsPage.stream()
            .map(event -> new EventResponseDTO(
                event.getId(),
                event.getTitle(),
                event.getDescription(),
                event.getDate(),
                "",
                "",
                event.getRemote(),
                event.getEventUrl(),
                event.getImgUrl()
            ))
            .toList();
    }

    private String uploadImageAndGetUrl(MultipartFile image) {
        try {
            Map<?, ?> uploadResult = cloudinary.uploader().upload(image.getBytes(), 
                ObjectUtils.asMap("upload_preset", cloudinaryConfig.getUploadPreset()));
            return uploadResult.get("secure_url").toString();
        } catch (Exception e) {
            throw new RuntimeException("Erro ao fazer upload da imagem: " + e.getMessage());
        }
    }
}