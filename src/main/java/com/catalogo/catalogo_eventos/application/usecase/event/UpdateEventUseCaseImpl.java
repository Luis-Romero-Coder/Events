package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalogo.catalogo_eventos.domain.exception.BusinessRuleException;
import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.UpdateEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;

@Service
public class UpdateEventUseCaseImpl implements UpdateEventUseCase {
    private final EventRepositoryPort eventRepository;

    public UpdateEventUseCaseImpl(EventRepositoryPort eventReposiory){
        this.eventRepository = eventReposiory;
    }

    @Override
    @Transactional
    public Event update(Long id, Event event){
        Event existing = eventRepository.findById(id)
            .orElseThrow(()-> new BusinessRuleException("Event not found: " + id));
        if(event.getName() != null && !event.getName().isBlank() && !event.getName().equals(existing.getName())){
            if (eventRepository.existsByName(event.getName())){
            throw new BusinessRuleException("An event with the same name already exists");
        }
    
        existing.setName(event.getName());
        }
        if (event.getDescription() != null) existing.setDescription(event.getDescription());
        if (event.getDate() != null) existing.setDate(event.getDate());
        if (event.getVenueId() != null) existing.setVenueId(event.getVenueId());
        return eventRepository.save(existing);
    }
}
