package com.catalogo.catalogo_eventos.domain.exception;

public class DomainValidationException extends  RuntimeException {
    public DomainValidationException(String message){super(message);}
}
