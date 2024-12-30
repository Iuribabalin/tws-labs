package ru.iuribabalin.client.exception;

public class ConnectionException extends Exception {
    public ConnectionException(String message) {
        super(message.concat("\nPleas create service for Juddi"));
    }
}
