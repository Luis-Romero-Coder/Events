package com.catalogo.catalogo_eventos.infrastructure.adapters.in.web;

import com.catalogo.catalogo_eventos.domain.model.Event;
import com.catalogo.catalogo_eventos.domain.ports.in.*;
import jakarta.validation.Valid;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.net.URI;
import java.time.LocalDateTime;




@RestController
@RequestMapping("/api/events")
@Validated
public class EventRestAdapter {
    private final CreateEventUseCase createUseCase;
    private final GetEventUseCase getUseCase;
    private final UpdateEventUseCase updateUseCase;
    private final DeleteEventUseCase deleteUseCase;
    private final ListEventUseCase listUseCase;
    public EventRestAdapter(CreateEventUseCase createUseCase, GetEventUseCase getUseCase,
            UpdateEventUseCase updateUseCase, DeleteEventUseCase deleteUseCase, ListEventUseCase listUseCase) {
        this.createUseCase = createUseCase;
        this.getUseCase = getUseCase;
        this.updateUseCase = updateUseCase;
        this.deleteUseCase = deleteUseCase;
        this.listUseCase = listUseCase;
    }


    public static class CreateEventRequest{
        @NotBlank(message="Name must not be blank")
        @Size(max= 255, message="Name can not exceed 255 characters")
        public String name;

        @Size(max= 2000, message="Name can not exceed 2000 characters")
        public String description;
        
        public LocalDateTime date;
        public Long venueId;
    }

    public static class UpdateEventRequest{
        @Size(max = 255)
        public String name;

        @Size(max = 2000)
        public String description;

        public LocalDateTime date;
        public Long venueId;
    }

    @PostMapping
    public ResponseEntity<Event> create(@Valid @RequestBody CreateEventRequest req) {
        Event toCreate = new Event(null, req.name, req.description, req.date, req.venueId);
        Event created = createUseCase.create(toCreate);
        return ResponseEntity.created(URI.create("/api/events" + created.getId())).body(created);
    }
    
    @GetMapping("/{id}")
    public ResponseEntity<Event> getById(@PathVariable @Min(1) Long id) {
        Event e = getUseCase.getById(id);
        return ResponseEntity.ok(e);
    }
    
    @PutMapping("/{id}")
    public ResponseEntity<Event> update(@PathVariable @Min(1) Long id, @Valid @RequestBody UpdateEventRequest req) {
        Event toUpdate = new Event(null, req.name, req.description, req.date, req.venueId);
        Event updated =  updateUseCase.update(id, toUpdate);
        return ResponseEntity.ok(updated);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable @Min(1) Long id){
        deleteUseCase.delete(id);
        return ResponseEntity.noContent().build();
    }

    @GetMapping
    public ResponseEntity<Page<Event>> list( @RequestParam(required = false)String name, @RequestParam(defaultValue = "0") int page, @RequestParam(defaultValue = "10") int size){
        Pageable pageable = PageRequest.of(page, size);
        Page<Event> result = listUseCase.list(name, pageable);
        return ResponseEntity.ok(result);
    }
}
