package com.catalogo.catalogo_eventos.controller;

import com.catalogo.catalogo_eventos.model.EventEntity;
import com.catalogo.catalogo_eventos.service.EventService;
import jakarta.validation.Valid;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.net.URI;

@RestController
@RequestMapping("/api/events")
public class EventController {

    private final EventService eventService;

    public EventController(EventService eventService) {
        this.eventService = eventService;
    }


    @GetMapping
    public ResponseEntity<Page<EventEntity>> list(
            @RequestParam(required = false) String name,
            Pageable pageable
    ) {
        return ResponseEntity.ok(eventService.list(name, pageable));
    }

    @GetMapping("/{id}")
    public ResponseEntity<EventEntity> get(@PathVariable Long id) {
        return ResponseEntity.ok(eventService.findById(id));
    }

    @PostMapping
    public ResponseEntity<EventEntity> create(@Valid @RequestBody EventEntity event) {
        EventEntity created = eventService.create(event);
        return ResponseEntity.created(URI.create("/api/events/" + created.getId())).body(created);
    }

    @PutMapping("/{id}")
    public ResponseEntity<EventEntity> update(@PathVariable Long id, @Valid @RequestBody EventEntity event) {
        return ResponseEntity.ok(eventService.update(id, event));
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable Long id) {
        eventService.delete(id);
        return ResponseEntity.noContent().build();
    }
}
