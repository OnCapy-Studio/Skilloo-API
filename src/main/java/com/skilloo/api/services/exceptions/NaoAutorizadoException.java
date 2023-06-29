package com.skilloo.api.services.exceptions;

public class NaoAutorizadoException extends RuntimeException {
    public NaoAutorizadoException(String s) {
        super(s);
    }
}
