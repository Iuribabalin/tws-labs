package ru.iuribabalin.client.exception;

public class ClientException extends Exception {
    private Integer code;

    public ClientException(String message, Integer code) {
        super(message);
        this.code = code;
    }

    public ClientException(String message) {
        super(message);
        this.code = 500;
    }

    public Integer getCode() {
        return code;
    }
}
