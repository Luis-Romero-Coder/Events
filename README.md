Event Catalog – Hexagonal Architecture (Ports & Adapters)
General Description

This project implements an Event and Venue management system using Hexagonal Architecture, guaranteeing a clean, decoupled and easy to maintain design.
It is developed with Java 17, Spring Boot 3.5.7, Maven, JPA/Hibernate, MapStruct (without Lombok) and prepared to integrate with Angular and Docker.

This repository corresponds to the progress up to HU3, and serves as the basis for the implementation of HU4.

Project Structure

com.catalogo.catalogo_eventos/

│

├── domain/

│   ├── model/

│   ├── ports/in/

│   ├── ports/out/

│   └── exception/

│

├── application/

│   └── usecase/

│       ├── event/

│       └── venue/

│

└── infrastructure/

   ├── adapters/
    
   │   ├── in/web/
    
   │   └── out/jpa/
    
   ├── mapper/
    
   ├── config/
    
   └── exception/
    


Technologies

Java 17

Spring Boot 3.5.7

maven

JPA/Hibernate

MapStruct

H2/MySQL

OpenAPI (Swagger)

Docker-ready

Project Architecture

The project is organized following Hexagonal Architecture with a strict separation between:

1. Domain Layer

Contains:

Domain entities (pure, no JPA annotations)

Business exceptions

Input ports (Use Cases Interfaces)

Output ports (Repository Ports)

2. Application Layer

Contains the use cases:

Create/Get/Update/Delete/List Events

Create/Get/Update/Delete/List Venues

Each use case interacts only with the ports in the domain.

3. Infrastructure Layer

Includes:

REST adapters

JPA Adapters

JPA Entities

JPA Repositories

Mappers

Configurations (UseCaseConfig, OpenApiConfig, MapStructConfig)

Centralized exception handling
