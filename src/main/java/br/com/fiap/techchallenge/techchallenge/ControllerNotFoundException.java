package br.com.fiap.techchallenge.techchallenge;

public class ControllerNotFoundException extends RuntimeException {

    public ControllerNotFoundException(String message) {
        super(message);
    }

}
