package it.polimi.telco.ejb.exceptions;

public class NoReviewFoundException extends Exception{
    public NoReviewFoundException(String message) {
        super(message);
    }
}
