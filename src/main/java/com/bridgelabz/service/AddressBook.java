package com.bridgelabz.service;

import com.bridgelabz.exception.AddressBookException;
import com.bridgelabz.pojo.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AddressBook {
    //    TO ADD PERSON
    public abstract List<Person> addPerson(Person person) throws IOException;

    //    TO GET COUNT OF RECORDS OF PEOPLE
    public abstract int getCountOfRecords() throws IOException;

    //    TO GET INFORMATION ABOUT SPECIFIC PERSON
    public abstract Person getPersonInfo(int index) throws IOException;

    //    TO UPDATE INFORMATION OF PERSON
    public abstract ArrayList<Person> updatePerson(int index, Person person) throws IOException;

    //    TO REMOVE SPECIFIC PERSON
    public abstract int removePerson(int index) throws IOException;

    //    TO SORT BY PERSON NAME
    public abstract List<Person> sortByName() throws IOException;

    //    TO SORT BY PERSON ZIP CODE
    public abstract List<Person> sortByZip() throws IOException;

    //    TO GET FILE
    public abstract File getFile();

    //    TO PRINT ALL RECORDS
    public abstract boolean printAll() throws IOException;

    //    TO CREATE NEW ADDRESS BOOK
    public abstract void createNewAddressBook(String fileName) throws AddressBookException;

    //    TO OPEN EXISTING ADDRESS BOOK
    public abstract boolean openExistingAddressBook(String fileName) throws IOException;

    //    TO SAVE FILE
    public abstract boolean saveAs(String fileName, String newFileName);
}
