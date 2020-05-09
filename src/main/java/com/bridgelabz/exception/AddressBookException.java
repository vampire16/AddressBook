package com.bridgelabz.exception;

public class AddressBookException extends Throwable {
    public Exception type;

    public AddressBookException(Exception type) {
        this.type = type;
    }

    public enum Exception {
        AlreadyExist
    }
}
