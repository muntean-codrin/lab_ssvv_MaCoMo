package lab_ssvv_tests;

import domain.Student;
import domain.Tema;
import org.junit.Test;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import service.Service;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import validation.ValidationException;

import static junit.framework.TestCase.assertFalse;

public class TemaTests {
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
    public void addTema_InvalidData_EmptyDescription() {
        setup();

        String nrTema = "12";
        String descriere = "";
        int deadline = 10;
        int primire = 4;


        Tema tema = new Tema(nrTema, descriere, deadline,primire);

        try {
            service.addTema(tema);
            assertFalse(true);
        } catch (ValidationException exception) {

        }

    }

    @Test
    public void addTema_ValidData() {
        setup();

        String nrTema = "25";
        String descriere = "descriere";
        int deadline = 8;
        int primire = 5;


        Tema tema = new Tema(nrTema, descriere, deadline,primire);

        try {
            service.addTema(tema);
        } catch (ValidationException exception) {
            assertFalse(true);
        }

        assert(service.findTema(nrTema) != null);

    }
}
