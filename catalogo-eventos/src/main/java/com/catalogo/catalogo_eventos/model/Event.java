package com.catalogo.catalogo_eventos.model;

public class Event {
    private Long id;
    private String name;
    private String date;
    private String venueId;

    public Event(){}
    public Event(Long id, String name, String date, String venueId) {
        this.id = id;
        this.name = name;
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
    public String getDate() {
        return date;
    }
    public void setDate(String date) {
        this.date = date;
    }
    public String getVenueId() {
        return venueId;
    }
    public void setVenueId(String venueId) {
        this.venueId = venueId;
    }

    
}
