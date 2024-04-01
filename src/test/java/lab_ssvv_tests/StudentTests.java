package lab_ssvv_tests;

import domain.Student;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.util.stream.StreamSupport;

import static junit.framework.TestCase.assertEquals;
import static junit.framework.TestCase.assertFalse;

public class StudentTests {

     Service service;

    public void setup() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @Test
    public void addStudent_ValidData_CreatedSuccessfully() {
        setup();
        String idStudent = "test";
        String numeStudent = "john";
        int grupa = 935;
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
        } catch (ValidationException exception) {
            assertFalse(true);
        }

        assert(service.findStudent(idStudent) != null);
    }

    @Test
    public void addStudent_InvalidID_TooShort() {
        setup();
        String idStudent = "a"; // ID too short (below boundary value)
        String numeStudent = "john";
        int grupa = 935;
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_InvalidID_TooLong() {
        setup();
        String idStudent = "testtesttesttest"; // ID too long (above boundary value)
        String numeStudent = "john";
        int grupa = 935;
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_ValidName() {
        setup();
        String idStudent = "test";
        String numeStudent = "John Doe"; // Valid name (normal case)
        int grupa = 935;
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_InvalidName_Empty() {
        setup();
        String idStudent = "test";
        String numeStudent = ""; // Empty name
        int grupa = 935;
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_ValidGroup() {
        setup();
        String idStudent = "test";
        String numeStudent = "John Doe";
        int grupa = 935; // Valid group (normal case)
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_InvalidGroup_TooLow() {
        setup();
        String idStudent = "test";
        String numeStudent = "John Doe";
        int grupa = 99; // Group below boundary value
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_InvalidGroup_TooHigh() {
        setup();
        String idStudent = "test";
        String numeStudent = "John Doe";
        int grupa = 1000; // Group above boundary value
        String email = "john_doe@yahoo.com";
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_ValidEmail() {
        setup();
        String idStudent = "test";
        String numeStudent = "John Doe";
        int grupa = 935;
        String email = "john_doe@yahoo.com"; // Valid email (normal case)
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_InvalidEmail_MissingAtSymbol() {
        setup();
        String idStudent = "test";
        String numeStudent = "John Doe";
        int grupa = 935;
        String email = "johndoeyahoo.com"; // Missing "@" symbol
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }

    @Test
    public void addStudent_InvalidEmail_InvalidDomain() {
        setup();
        String idStudent = "test";
        String numeStudent = "John Doe";
        int grupa = 935;
        String email = "john_doe@invalid"; // Invalid domain
        Student student = new Student(idStudent, numeStudent, grupa, email);

        try {
            service.addStudent(student);
            assertFalse(true); // Expecting ValidationException to be thrown
        } catch (ValidationException e) {
            // Expected behavior
        }
    }


}
