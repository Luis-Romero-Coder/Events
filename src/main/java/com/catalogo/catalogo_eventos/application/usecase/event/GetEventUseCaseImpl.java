package com.catalogo.catalogo_eventos.application.usecase.event;

import org.springframework.stereotype.Service;

import com.catalogo.catalogo_eventos.domain.exception.BusinessRuleException;
import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.GetEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.out.EventRepositoryPort;
@Service
public class GetEventUseCaseImpl implements GetEventUseCase {
    private final EventRepositoryPort eventRespository;

    public GetEventUseCaseImpl(EventRepositoryPort eventRepository){
        this.eventRespository = eventRepository;
    }
    @Override
    public Event getById(Long id){
        return eventRespository.findById(id).orElseThrow(()->
        new BusinessRuleException("Event not found with id " +  id));
    }
}
