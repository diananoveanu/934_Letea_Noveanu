package testing;

import org.junit.After;
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

import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class BigBangTests {
    private StudentXMLRepository studentXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private TemaXMLRepository temaXMLRepository;

    private Service service;

    @Before
    public void setup() throws FileNotFoundException {
        Validator<Student> vs = new StudentValidator();
        Validator<Nota> ns = new NotaValidator();
        Validator<Tema> ts = new TemaValidator();

        studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/testing/studenti.xml");
        notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/testing/note.xml");
        temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/testing/teme.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);


        PrintWriter writer = new PrintWriter("src/test/java/testing/studenti.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "    <student ID=\"1000\">\n" +
                "        <Nume>Diana</Nume>\n" +
                "        <Grupa>935</Grupa>\n" +
                "    </student>\n" +
                "</Entitati>\n");
        writer.close();

        writer = new PrintWriter("src/test/java/testing/teme.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "<tema ID=\"1000\">\n" +
                "<Descriere>File</Descriere>\n" +
                "<Deadline>7</Deadline>\n" +
                "<Startline>6</Startline>\n" +
                "</tema>\n" +
                "</Entitati>\n");
        writer.close();

        writer = new PrintWriter("src/test/java/testing/note.xml");
        writer.print("<?xml version=\"1.0\" encoding=\"UTF-8\" standalone=\"no\"?>\n" +
                "<Entitati>\n" +
                "</Entitati>\n");
        writer.close();

    }

    @After
    public void tearDown() {
        try {
            String defaultFileContent = new String(Files.readAllBytes(Paths.get("src/test/java/testing/defaultFile.xml")), StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter("src/test/java/testing/studenti.xml");
            PrintWriter printWriter1 = new PrintWriter("src/test/java/testing/note.xml");
            PrintWriter printWriter2 = new PrintWriter("src/test/java/testing/teme.xml");

            printWriter.print(defaultFileContent);
            printWriter1.println(defaultFileContent);
            printWriter2.println(defaultFileContent);
            printWriter.close();
            printWriter1.close();
            printWriter2.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }


    @Test
    public void testAddAssignment(){
        assertEquals(service.saveTema("1", "1", 8, 7), 1);
    }

    @Test
    public void testAddStudent(){
        assertEquals(service.saveStudent("1", "Andrei", 231), 1);
    }

    @Test
    public void testAddGrade(){
        assertEquals(service.saveNota("1000", "1000", 9, 3, "good"), -1);
    }

    @Test
    public void testBigBang(){
        assertEquals(service.saveStudent("2", "Alexandru", 231), 1);
        assertEquals(service.saveTema("2", "1", 8, 7), 1);
        assertEquals(service.saveNota("2", "2", 9, 3, "good"), 1);

    }
}