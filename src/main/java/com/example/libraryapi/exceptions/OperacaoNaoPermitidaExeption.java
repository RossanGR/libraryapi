package com.example.libraryapi.exceptions;

public class OperacaoNaoPermitidaExeption extends RuntimeException{
    public OperacaoNaoPermitidaExeption(String message) {
        super(message);
    }
}
