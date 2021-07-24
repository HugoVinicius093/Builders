package com.builders.clientshandler.service.exception;

public class ClientException extends Exception{

    private static final long serialVersionUID = -6304519107418735310L;

    public ClientException() { super(); }

    public ClientException(String message) { super(message); }

    public ClientException(String message, Throwable cause) { super(message, cause); }

    public ClientException(Throwable cause) { super(cause); }
}
