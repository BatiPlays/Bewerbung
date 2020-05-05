package com.homestream.HomeStream.main.exception;

/**
 * An exception that is thrown whenever a requested ID cannot be forund in the persistence layer
 *
 * @author Justin Braack
 */
public class IdNotFoundException extends RuntimeException {

    /**
     * Create a new IdNotFoundException
     */
    public IdNotFoundException(){
        super();
    }

    /**
     * Create a new IdNotFoundException
     * @param message The error message for this exception
     */
    public IdNotFoundException(String message){
        super(message);
    }

    /**
     * Create a new IdNotFoundException
     * @param message The error message for this exception
     * @param err The underlying Exception, etc. that was thrown previously
     */
    public IdNotFoundException(String message, Throwable err){
        super(message, err);
    }
}
