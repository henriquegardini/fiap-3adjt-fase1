package br.com.fiap.techchallenge.controller.exception;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message) {
        super(message);
    }

}
