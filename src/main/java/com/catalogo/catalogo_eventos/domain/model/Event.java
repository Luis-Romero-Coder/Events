package com.catalogo.catalogo_eventos.domain.model;

import java.time.LocalDateTime;
import java.util.Objects;

public class Event {
    private Long id;
    private String name;
    private String description;
    private LocalDateTime date;
    private Long venueId;

    public Event(){}

    public Event(Long id, String name, String description, LocalDateTime date, Long venueId) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.date = date;
        this.venueId = venueId;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public LocalDateTime getDate() {
        return date;
    }

    public void setDate(LocalDateTime date) {
        this.date = date;
    }

    public Long getVenueId() {
        return venueId;
    }

    public void setVenueId(Long venueId) {
        this.venueId = venueId;
    }

    @Override
    public boolean equals(Object o){
        if(this == o)return true;
        if(!(o instanceof Event)) return false;
        Event event = (Event) o;
        return Objects.equals(id, event.id);
    }

    @Override
    public int hashCode(){
        return Objects.hash(id);
    }
}
