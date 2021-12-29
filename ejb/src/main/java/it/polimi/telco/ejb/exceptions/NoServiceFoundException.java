package it.polimi.telco.ejb.exceptions;

public class NoServiceFoundException extends Exception{
    public NoServiceFoundException(String message) {
        super(message);
    }
}
