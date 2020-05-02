package com.bridgelabz.utility;

import com.bridgelabz.pojo.Person;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

public abstract class AddressBook {
    public abstract List<Person> addPerson(Person person) throws IOException;

    public abstract  int getCountOfRecords() throws IOException;

    public abstract Person getPersonInfo(int index) throws IOException;

    public abstract ArrayList<Person> updatePerson(int index, Person person) throws IOException;

    public abstract int removePerson(int index) throws IOException;

    public abstract ArrayList<Person> sortByName() throws IOException;

    public abstract ArrayList<Person> sortByZip() throws IOException;

    public abstract File getFile();

    public abstract void printAll() throws IOException;
}
