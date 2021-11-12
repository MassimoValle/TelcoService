package it.polimi.telco.ejb.exceptions;

public class AlreadyRegisteredException extends Exception {
    public AlreadyRegisteredException(String message) {
        super(message);
    }
}
