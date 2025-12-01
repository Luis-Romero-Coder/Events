package com.catalogo.catalogo_eventos.infrastructure.adapters.in.web;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.model.PageRequests;
import com.catalogo.catalogo_eventos.domain.model.PaginatedResult;
import com.catalogo.catalogo_eventos.domain.ports.in.CreateEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.DeleteEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.GetEventUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.ListEventsUseCase;
import com.catalogo.catalogo_eventos.domain.ports.in.UpdateEventUseCase;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.EventRequest;
import com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto.EventResponse;
import com.catalogo.catalogo_eventos.infrastructure.mapper.EventMapper;

import jakarta.validation.Valid;

@RestController
@RequestMapping("/api/events")
public class EventRestAdapter {

    private final CreateEventUseCase createEventUseCase;
    private final GetEventUseCase getEventUseCase;
    private final ListEventsUseCase listEventsUseCase;
    private final UpdateEventUseCase updateEventUseCase;
    private final DeleteEventUseCase deleteEventUseCase;
    private final EventMapper mapper;

    public EventRestAdapter(CreateEventUseCase createEventUseCase,
                            GetEventUseCase getEventUseCase,
                            ListEventsUseCase listEventsUseCase,
                            UpdateEventUseCase updateEventUseCase,
                            DeleteEventUseCase deleteEventUseCase,
                            EventMapper mapper) {
        this.createEventUseCase = createEventUseCase;
        this.getEventUseCase = getEventUseCase;
        this.listEventsUseCase = listEventsUseCase;
        this.updateEventUseCase = updateEventUseCase;
        this.deleteEventUseCase = deleteEventUseCase;
        this.mapper = mapper;
    }

    @PostMapping
    public ResponseEntity<EventResponse> create(@Valid @RequestBody EventRequest request) {
        Event domain = mapper.toDomain(request);
        Event created = createEventUseCase.create(domain);
        return ResponseEntity.status(201).body(mapper.toResponse(created));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventResponse> getById(@PathVariable Long id) {
        Event e = getEventUseCase.getById(id);
        return ResponseEntity.ok(mapper.toResponse(e));
    }

    @GetMapping
    public ResponseEntity<PaginatedResult<EventResponse>> list(
            @RequestParam(value = "name", required = false) String name,
            Pageable pageable) {

        // Convert Spring Pageable -> domain PageRequest
        PageRequests pageRequest = new PageRequests(pageable.getPageNumber(), pageable.getPageSize(), pageable.getSort().toString());
        PaginatedResult<Event> domainPage = listEventsUseCase.list(name, pageRequest);

        List<EventResponse> content = domainPage.getContent()
                .stream()
                .map(mapper::toResponse)
                .collect(Collectors.toList());

        PaginatedResult<EventResponse> responsePage = new PaginatedResult<>(
                content,
                domainPage.getTotalElements(),
                domainPage.getTotalPages(),
                domainPage.getPageNumber(),
                domainPage.getPageSize()
        );

        return ResponseEntity.ok(responsePage);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventResponse> update(@PathVariable Long id, @Valid @RequestBody EventRequest request) {
        Event domain = mapper.toDomain(request);
        Event updated = updateEventUseCase.update(id, domain);
        return ResponseEntity.ok(mapper.toResponse(updated));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        deleteEventUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }
}
