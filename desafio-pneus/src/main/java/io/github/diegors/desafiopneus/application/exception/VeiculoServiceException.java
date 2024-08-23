package io.github.diegors.desafiopneus.application.exception;

public class VeiculoServiceException extends RuntimeException {

    public VeiculoServiceException(String message) {
        super(message);
    }

    public VeiculoServiceException(String message, Throwable cause) {
        super(message, cause);
    }
}
