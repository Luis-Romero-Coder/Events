package com.catalogo.catalogo_eventos.service;

import com.catalogo.catalogo_eventos.model.Event;
import com.catalogo.catalogo_eventos.repository.EventRepository;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
public class EventService {
    private final EventRepository eventRepository;

    public EventService(EventRepository eventRepository){
        this.eventRepository = eventRepository;
    }

    public List<Event> findAllEvents(){
        return eventRepository.findAll();
    }

    public Optional<Event> getEventById(Long id){
        return eventRepository.findById(id);
    }

    public Event createEvent(Event event){
        return eventRepository.save(event);
    }

    public void deleteEventById(Long id){
        eventRepository.deleteById(id);
    }
}
