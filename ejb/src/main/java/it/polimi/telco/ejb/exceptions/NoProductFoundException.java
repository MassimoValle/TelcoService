package it.polimi.telco.ejb.exceptions;

public class NoProductFoundException extends Exception{

    public NoProductFoundException(String message) {
        super(message);
    }
}
