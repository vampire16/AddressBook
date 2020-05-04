package com.bridgelabz.service;

import com.bridgelabz.controller.AddressBookController;
import com.bridgelabz.exception.AddressBookException;
import com.bridgelabz.pojo.Person;
import com.bridgelabz.utility.FileSystem;

import java.io.File;
import java.io.IOException;
import java.util.Map;
import java.util.Scanner;

public class AddressBookApplication {
    static Scanner scanner = new Scanner(System.in);
    static AddressBookController controller = new AddressBookController();
    static AddressBookApplication application = new AddressBookApplication();

    public static void main(String[] args) throws IOException, AddressBookException {
        System.out.println("Welcome");
        application.gui();
    }

    public void gui() throws AddressBookException, IOException {
        while (true) {
            System.out.println("Enter index number");
            System.out.println("1.create new address book");
            System.out.println("2.open address book");
            System.out.println("3.close address book");
            int choice = scanner.nextInt();
            switch (choice) {
                case 1:
                    System.out.println("Enter book name");
                    String bookName = scanner.next();
                    controller.createNewAddressBook(bookName);
                    System.out.println(bookName + " address book is created");
                    Person person = this.info();
                    controller.addPerson(person);
                    System.out.println(person.getFirstName() + " info added");
                    break;
                case 2:
                    Map<Integer, File> addressBooks = controller.getAddressBooks();
                    for (Map.Entry<Integer, File> personEntry : addressBooks.entrySet()) {
                        System.out.println(personEntry.getKey() + "  " + personEntry.getValue().getName());
                    }
                    System.out.println("enter index number to select book");
                    int index = scanner.nextInt();
                    String fileName = addressBooks.get(index).getName();
                    controller.openExistingAddressBook(fileName);
                    System.out.println(fileName + " address book is opened");
                    System.out.println("Enter index number");
                    System.out.println("1.add new Person info");
                    System.out.println("2.update person info");
                    System.out.println("3.delete person info");
                    System.out.println("4.person info sorted by name");
                    System.out.println("5.person info sorted by zip");
                    new FileSystem(addressBooks.get(index).getName().replace(".json", ""));
                    int select = scanner.nextInt();
                    switch (select) {
                        case 1:
                            Person person1 = this.info();
                            controller.addPerson(person1);
                            System.out.println(person1.getFirstName() + " info added in " + fileName);
                            continue;
                        case 2:
                            controller.printAll();
                            System.out.println("enter index number");
                            int index1 = scanner.nextInt();
                            Person person2 = this.info();
                            controller.updatePerson(index1 - 1, person2);
                            continue;
                        case 3:
                            controller.printAll();
                            System.out.println("Enter index number");
                            int index2 = scanner.nextInt();
                            controller.removePerson(index2 - 1);
                            continue;
                        case 4:
                            controller.sortByName();
                            controller.printAll();
                            continue;
                        case 5:
                            controller.sortByZip();
                            controller.printAll();
                            continue;
                    }
                case 3:
                    System.exit(0);
                    break;
            }
        }
    }

    public Person info() {
        System.out.println("Enter first name : ");
        String firstName = scanner.next();
        System.out.println("Enter last name : ");
        String lastName = scanner.next();
        System.out.println("Enter address : ");
        String address = scanner.next();
        System.out.println("Enter city : ");
        String city = scanner.next();
        System.out.println("Enter state : ");
        String state = scanner.next();
        System.out.println("Enter zip : ");
        String zip = scanner.next();
        System.out.println("Enter phone : ");
        String phone = scanner.next();
        Person person = new Person(firstName, lastName, address, city, state, zip, phone);
        return person;
    }
}
