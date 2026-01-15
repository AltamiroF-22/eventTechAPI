package com.eventotech.api.service;

import java.util.Date; 
import java.util.Map;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import com.eventotech.api.domain.event.Event;
import com.eventotech.api.domain.event.EventRequestDTO;
import com.eventotech.api.repositories.EventRepository;
import com.eventotech.api.config.CloudinaryConfig;
import org.springframework.web.multipart.MultipartFile;
import com.cloudinary.Cloudinary;
import com.cloudinary.utils.ObjectUtils;

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