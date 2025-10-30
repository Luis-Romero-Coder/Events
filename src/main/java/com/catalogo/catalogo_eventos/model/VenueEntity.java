package com.catalogo.catalogo_eventos.model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Entity
@Table(name = "venues")
public class VenueEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    @NotBlank(message = "El nombre del venue es obligatorio")
    @Size(max = 100, message = "El nombre del venue debe tener como máximo 100 caracteres")
    private String name;

    @Size(max = 255, message = "La dirección debe tener como máximo 255 caracteres")
    private String address;

    public VenueEntity() {}

    public VenueEntity(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public Long getId() { return id; }
    public void setId(Long id) { this.id = id; }

    public String getName() { return name; }
    public void setName(String name) { this.name = name; }

    public String getAddress() { return address; }
    public void setAddress(String address) { this.address = address; }
}
