package com.catalogo.catalogo_eventos.repository;

import com.catalogo.catalogo_eventos.model.Event;
import org.springframework.stereotype.Repository;

import java.util.*;
@Repository
public class EventRepository {
    private final Map<Long, Event> eventStorage = new HashMap<>();

    public List<Event> findAll(){
        return new ArrayList<>(eventStorage.values());
    }

    public Optional<Event> findById(Long id){
        return Optional.ofNullable(eventStorage.get(id));
    }

    public Event save(Event event){
        eventStorage.put(event.getId(), event);
        return event;
    }

    public void deleteById(Long id){
        eventStorage.remove(id);
    }
}
