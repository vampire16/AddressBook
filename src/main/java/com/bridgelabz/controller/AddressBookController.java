package com.bridgelabz.controller;

import com.bridgelabz.exception.AddressBookException;
import com.bridgelabz.pojo.Person;
import com.bridgelabz.utility.AddressBook;
import com.bridgelabz.utility.FileSystem;
import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;


public class AddressBookController extends AddressBook {
    ObjectMapper objectMapper = new ObjectMapper();
    File file = null;
    ArrayList<Person> list = null;

    public void writeData(File file, ArrayList<Person> list) throws IOException {
        objectMapper.writeValue(file, list);
    }

    public ArrayList<Person> readData(File file) throws IOException {
        return objectMapper.readValue(file, new TypeReference<ArrayList<Person>>() {});
    }

    @Override
    public List<Person> addPerson(Person person) throws IOException {
        if (FileSystem.getFile().length() == 0) {
            list = new ArrayList<>();
            list.add(person);
            file = FileSystem.getFile();
            this.writeData(file, list);
        } else {
            file = FileSystem.getFile();
            list = this.readData(file);
            list.add(person);
            this.writeData(file, list);
        }
        return list;
    }

    @Override
    public Person getPersonInfo(int index) throws IOException {
        file = FileSystem.getFile();
        list = this.readData(file);
        return list.get(index);
    }

    @Override
    public int getCountOfRecords() throws IOException {
        file = FileSystem.getFile();
        list = this.readData(file);
        return list.size();
    }

    @Override
    public ArrayList<Person> updatePerson(int index, Person person) throws IOException {
        file = FileSystem.getFile();
        list = this.readData(file);
        list.get(index).setFirstName(person.getFirstName());
        list.get(index).setLastName(person.getLastName());
        list.get(index).setAddress(person.getAddress());
        list.get(index).setCity(person.getCity());
        list.get(index).setState(person.getState());
        list.get(index).setZip(person.getZip());
        list.get(index).setPhone(person.getPhone());
        this.writeData(file,list);
        return list;
    }

    @Override
    public int removePerson(int index) throws IOException {
        file = FileSystem.getFile();
        list = this.readData(file);
        list.remove(index);
        this.writeData(file,list);
        return this.getCountOfRecords();
    }

    @Override
    public List<Person> sortByName() throws IOException {
        file = FileSystem.getFile();
        list = this.readData(file);
        list.sort(Comparator.comparing(Person::getFirstName));
        this.writeData(file,list);
        return list;
    }

    @Override
    public List<Person> sortByZip() throws IOException {
        file = FileSystem.getFile();
        list = this.readData(file);
        list.sort(Comparator.comparing(Person::getZip));
        this.writeData(file,list);
        return list;
    }

    @Override
    public File getFile() {
        return FileSystem.getFile();
    }

    @Override
    public boolean printAll() throws IOException {
        file = FileSystem.getFile();
        list = this.readData(file);
        list.forEach(person -> System.out.println(person.getFirstName()+" "+person.getLastName()));
        return true;
    }

    @Override
    public void createNewAddressBook(String fileName) throws AddressBookException {
        File file = new File("src/test/resources/"+fileName+".json");
        if(file.exists()){
            throw new AddressBookException(AddressBookException.Exception.AlreadyExist);
        }else {
            new FileSystem(fileName);
        }
    }

    @Override
    public boolean openExistingAddressBook(String fileName) throws IOException {
        File file = new File("src/test/resources/"+fileName+".json");
        if(file.exists()) {
            file = FileSystem.getFile();
            list = this.readData(file);
            return true;
        }
        return false;
    }

    @Override
    public boolean saveAs(String fileName, String newFileName) {
        File file = new File("src/test/resources/"+fileName+".json");
        File newFile = new File("src/test/resources/"+newFileName+".json");
        if(file.exists())
            return file.renameTo(newFile);
        return false;
    }
}
