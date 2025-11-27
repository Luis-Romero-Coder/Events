package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.catalogo.catalogo_eventos.domain.exception.BusinessRuleException;
import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.CreateEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
@Service
public class CreateEventUseCaseImpl implements CreateEventUseCase {
    private final EventRepositoryPort eventRepository;

    public CreateEventUseCaseImpl(EventRepositoryPort eventRepository){
        this.eventRepository = eventRepository;
    }

    @Override
    @Transactional
    public Event create(Event event){
        if(event.getName()== null || event.getName().isBlank()){
            throw new BusinessRuleException("Event name must no be blanck");
        }
        if (eventRepository.existsByName(event.getName())){
            throw new BusinessRuleException("An event with the same name already exists");
        }
        return eventRepository.save(event);
    }
}
