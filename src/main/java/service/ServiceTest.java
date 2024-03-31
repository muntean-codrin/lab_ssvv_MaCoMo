package service;

import domain.Student;
import org.junit.jupiter.api.BeforeEach;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import java.util.stream.StreamSupport;

import static org.junit.jupiter.api.Assertions.*;

class ServiceTest {

    Service service;

    @BeforeEach
    void setUp() {
        StudentValidator studentValidator = new StudentValidator();
        TemaValidator temaValidator = new TemaValidator();
        String filenameStudent = "fisiere/Studenti.xml";
        String filenameTema = "fisiere/Teme.xml";
        String filenameNota = "fisiere/Note.xml";

        //StudentFileRepository studentFileRepository = new StudentFileRepository(filenameStudent);
        //TemaFileRepository temaFileRepository = new TemaFileRepository(filenameTema);
        //NotaValidator notaValidator = new NotaValidator(studentFileRepository, temaFileRepository);
        //NotaFileRepository notaFileRepository = new NotaFileRepository(filenameNota);

        StudentXMLRepo studentXMLRepository = new StudentXMLRepo(filenameStudent);
        TemaXMLRepo temaXMLRepository = new TemaXMLRepo(filenameTema);
        NotaValidator notaValidator = new NotaValidator(studentXMLRepository, temaXMLRepository);
        NotaXMLRepo notaXMLRepository = new NotaXMLRepo(filenameNota);
        service = new Service(studentXMLRepository, studentValidator, temaXMLRepository, temaValidator, notaXMLRepository, notaValidator);
    }

    @org.junit.jupiter.api.Test
    void addStudent() {

        var stud = new Student("id", "name", 935, "mail@gmail.com");

        var listSize = StreamSupport.stream(service.getAllStudenti().spliterator(), false)
                .toList().size();

        service.addStudent(stud);

        var list = StreamSupport.stream(service.getAllStudenti().spliterator(), false)
                .toList();

        assertEquals(list.size(), listSize + 1);

        service.deleteStudent("id");
    }

    @org.junit.jupiter.api.Test
    void addStudentFailed() {

        var stud = new Student("id", "name", -1, "mail@gmail.com");

        try {
            service.addStudent(stud);
            assert (false);
        }
        catch (ValidationException e){
        }
        catch(Exception e)
        {
            assert(false);
        }
    }
}