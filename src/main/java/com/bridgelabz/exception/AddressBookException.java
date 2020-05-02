package com.bridgelabz.exception;

public class AddressBookException extends Throwable {
    public enum Exception{
        AlreadyExist
    }

    public Exception type;

    public AddressBookException(Exception type) {
        this.type = type;
    }
}
