package testing;

import org.junit.Before;
import org.junit.Test;
import testing.domain.Nota;
import testing.domain.Student;
import testing.domain.Tema;
import testing.repository.NotaXMLRepository;
import testing.repository.StudentXMLRepository;
import testing.repository.TemaXMLRepository;
import testing.service.Service;
import testing.validation.NotaValidator;
import testing.validation.StudentValidator;
import testing.validation.TemaValidator;
import testing.validation.Validator;

import java.io.PrintWriter;

import static org.junit.Assert.assertEquals;

public class IncrementalTests {
    private Service service;


    @Before
    public void setUp() throws Exception {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        PrintWriter writer = new PrintWriter("studenti.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "    <student ID=\"1\">\n" +
                "        <Nume>Diana</Nume>\n" +
                "        <Grupa>935</Grupa>\n" +
                "    </student>\n" +
                "</Entitati>\n");
        writer.close();

        writer = new PrintWriter("teme.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "<tema ID=\"1\">\n" +
                "<Descriere>Best</Descriere>\n" +
                "<Deadline>7</Deadline>\n" +
                "<Startline>6</Startline>\n" +
                "</tema>\n" +
                "</Entitati>\n");
        writer.close();

        writer = new PrintWriter("note.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "</Entitati>\n");
        writer.close();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");

        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        service = new Service(fileRepository1, fileRepository2, fileRepository3);
    }

    @Test
    public void testAddStudent(){
        assertEquals(1, service.saveStudent("gh", "geo", 231));
    }

    @Test
    public void testAddAssignmentAddStudent(){
        assertEquals(1, service.saveTema("2", "Integrale", 7, 5));
        assertEquals(1, service.saveStudent("2", "Alexandra", 935));
    }

    @Test
    public void testAddAll(){
        assertEquals(1, service.saveStudent("3", "Cosmin", 231));
        assertEquals(1, service.saveTema("3", "best", 8, 5));
        assertEquals(1, service.saveNota("3", "3", 8, 7, "ok"));
    }

}
