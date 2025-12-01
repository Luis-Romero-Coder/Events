package com.catalogo.catalogo_eventos.infrastructure.adapters.in.web.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class VenueRequest {

    @NotBlank(message = "Venue name must not be blank")
    @Size(max = 255, message = "Venue name must be at most 255 characters")
    private String name;

    @NotBlank(message = "Address must not be blank")
    @Size(max = 500, message = "Address must be at most 500 characters")
    private String address;

    public VenueRequest() {
    }

    public VenueRequest(String name, String address) {
        this.name = name;
        this.address = address;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
