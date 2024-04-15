package service;

import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.StreamSupport;

import org.junit.Test;

import domain.Nota;
import domain.Student;
import repository.NotaXMLRepo;
import repository.StudentXMLRepo;
import repository.TemaXMLRepo;
import validation.NotaValidator;
import validation.StudentValidator;
import validation.TemaValidator;
import view.UI;

public class ServiceTest {
	private StudentValidator studentValidator = new StudentValidator();
	private TemaValidator temaValidator = new TemaValidator();
	private String filenameStudent = "fisiere/mock-Studenti.xml";
	private String filenameTema = "fisiere/mock-Teme.xml";
	private String filenameNota = "fisiere/mock-Note.xml";

	private StudentXMLRepo studentXMLmockRepository = new StudentXMLRepo(filenameStudent);
	private TemaXMLRepo temaXMLmockRepository = new TemaXMLRepo(filenameTema);
	private NotaValidator notaValidator = new NotaValidator(studentXMLmockRepository, temaXMLmockRepository);
	private NotaXMLRepo notaXMLmockRepository = new NotaXMLRepo(filenameNota);
	private Service service = new Service(studentXMLmockRepository, studentValidator, temaXMLmockRepository, temaValidator, notaXMLmockRepository, notaValidator);
	
	@Test
	public void testAddNota() {

		// var student = new Student

		// var students = studentXMLmockRepository.findAll().
		List<Student> students = StreamSupport.stream(studentXMLmockRepository
			.findAll().spliterator(), false)
    		.collect(Collectors.toList());

		List<Tema> temas = StreamSupport.stream(temaXMLmockRepository
			.findAll().spliterator(), false)
    		.collect(Collectors.toList());	

		Nota nota = new Nota(
			"test-nota-id",
			students.get(0).getID()

		);
	}
}
