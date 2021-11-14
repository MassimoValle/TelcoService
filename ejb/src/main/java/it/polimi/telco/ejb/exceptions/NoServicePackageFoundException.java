package it.polimi.telco.ejb.exceptions;

public class NoServicePackageFoundException  extends Exception {
    public NoServicePackageFoundException(String message) {
        super(message);
    }
}