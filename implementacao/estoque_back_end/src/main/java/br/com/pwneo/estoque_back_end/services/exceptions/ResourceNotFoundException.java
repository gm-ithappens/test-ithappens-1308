package br.com.pwneo.estoque_back_end.services.exceptions;

public class ResourceNotFoundException extends RuntimeException{
    private static final Long serialVersionUID = 1L;

    public ResourceNotFoundException(Integer id){
        super("Resource not found. Id " + id);
    }
}