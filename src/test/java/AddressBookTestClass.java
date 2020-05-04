import com.bridgelabz.controller.AddressBookController;
import com.bridgelabz.exception.AddressBookException;
import com.bridgelabz.pojo.Person;
import com.bridgelabz.utility.FileSystem;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import java.io.File;
import java.io.IOException;
import java.util.List;

public class AddressBookTestClass {
    AddressBookController controller;
    Person person;
    FileSystem fileSystem;
    List<Person> list;

    @Before
    public void setUp() throws Exception {
        controller = new AddressBookController();
        String fileName = "ABC";
        fileSystem = new FileSystem(fileName);
    }

    @Test
    public void givenPersonInfo_WhenAddInFile_ShouldReturnFile() throws IOException {
        person = new Person("vaibhav", "hake", "E-1", "kalamboli", "maharashtra", "410217", "8939729377");
        list = controller.addPerson(person);
        Assert.assertEquals("vaibhav", list.get(0).getFirstName());
    }

    @Test
    public void givenNewPersonInfo_WhenAddInFile_ShouldReturnFile() throws IOException {
        person = new Person("bharat", "hake", "E-1", "kalamboli", "maharashtra", "410218", "8939729377");
        list = controller.addPerson(person);
        Assert.assertEquals("maharashtra", list.get(1).getState());
    }

    @Test
    public void givenNewPersonInfo1_WhenAddInFile_ShouldReturnFile() throws IOException {
        person = new Person("v", "hake", "E-1", "panvel", "maharashtra", "410218", "8939729377");
        list = controller.addPerson(person);
        Assert.assertEquals("v", list.get(2).getFirstName());
    }

    @Test
    public void givenFile_ShouldReturnCountOfPeople() throws IOException {
        int count = controller.getCountOfRecords();
        Assert.assertEquals(3, count);
    }

    @Test
    public void givenUpdatedPersonInfo_WhenUpdated_ShouldReturnUpdatedFile() throws IOException {
        person = new Person("v", "h", "E-1", "kalamboli", "maharashtra", "410218", "8939729377");
        list = controller.updatePerson(2, person);
        Assert.assertEquals("h", list.get(2).getLastName());
    }

    @Test
    public void givenIndex_ShouldReturnPersonInfo() throws IOException {
        Person person = controller.getPersonInfo(2);
        Assert.assertEquals("v", person.getFirstName());
    }

    @Test
    public void givenIndex_WhenRemovePersonInfo_ShouldReturnUpdatedFile() throws IOException {
        int count = controller.removePerson(2);
        Assert.assertEquals(2, count);
    }

    @Test
    public void givenFileName_ShouldReturnFileInfo() {
        File file = controller.getFile();
        Assert.assertEquals("ABC.json", file.getName());
    }

    @Test
    public void givenFileName_WhenSortedByName_ShouldReturnSortedData() throws IOException {
        list = controller.sortByName();
        Assert.assertEquals("bharat", list.get(0).getFirstName());
    }

    @Test
    public void givenFileName_WhenSortedByZip_ShouldReturnSortedData() throws IOException {
        list = controller.sortByZip();
        Assert.assertEquals("410217", list.get(0).getZip());
    }

    @Test
    public void givenNewFileName_ShouldReturnFileInfo() throws IOException, AddressBookException {
        String fileName = "XYZ";
        controller.createNewAddressBook(fileName);
        person = new Person("vaibhav", "hake", "E-1", "kalamboli", "maharashtra", "410217", "8939729399");
        list = controller.addPerson(person);
        Assert.assertEquals("8939729399", list.get(0).getPhone());
    }

    @Test
    public void givenNewFileName_WhenAlreadyExist_ShouldReturnException() throws IOException {
        String fileName = "XYZ";
        try {
            controller.createNewAddressBook(fileName);
        } catch (AddressBookException e) {
            Assert.assertEquals(AddressBookException.Exception.AlreadyExist, e.type);
        }
    }

    @Test
    public void givenFileName_ShouldReturnAllData() throws IOException {
        boolean isPrinted = controller.printAll();
        Assert.assertTrue(isPrinted);
    }

    @Test
    public void givenFileName_IfAlreadyExists_ShouldReturnTrue() throws IOException {
        String fileName = "ABC";
        boolean isExist = controller.openExistingAddressBook(fileName);
        Assert.assertTrue(isExist);
    }

    @Test
    public void givenFileName_IfDoesNotExists_ShouldReturnFalse() throws IOException {
        String fileName = "ABCD";
        boolean isExist = controller.openExistingAddressBook(fileName);
        Assert.assertFalse(isExist);
    }

    @Test
    public void givenFileName_WhenSaveAsAnotherName_ShouldReturnTrue() throws IOException {
        String fileName = "XYZ";
        String newFileName = "DEF";
        boolean isChangedFileName = controller.saveAs(fileName, newFileName);
        Assert.assertTrue(isChangedFileName);
    }
}
