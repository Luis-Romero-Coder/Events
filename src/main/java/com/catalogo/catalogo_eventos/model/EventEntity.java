package com.catalogo.catalogo_eventos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import jakarta.validation.constraints.NotNull;
import java.time.LocalDateTime;

@Entity
@Table(name = "events", uniqueConstraints = @UniqueConstraint(columnNames = {"name"}))
public class EventEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false, unique = true)
    @NotBlank(message = "El nombre del evento es obligatorio")
    @Size(max = 150, message = "El nombre del evento debe tener como máximo 150 caracteres")
    private String name;

    @Size(max = 1000, message = "La descripción debe tener como máximo 1000 caracteres")
    private String description;

    @Column(name = "event_date", nullable = false)
    @NotNull(message = "La fecha del evento es obligatoria")
    private LocalDateTime date;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "venue_id")
    private VenueEntity venue;

    public EventEntity() {}

    public EventEntity(String name, String description, LocalDateTime date, VenueEntity venue) {
        this.name = name;
        this.description = description;
        this.date = date;
        this.venue = venue;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getDescription() { return description; }
    public void setDescription(String description) { this.description = description; }

    public LocalDateTime getDate() { return date; }
    public void setDate(LocalDateTime date) { this.date = date; }

    public VenueEntity getVenue() { return venue; }
    public void setVenue(VenueEntity venue) { this.venue = venue; }
}
