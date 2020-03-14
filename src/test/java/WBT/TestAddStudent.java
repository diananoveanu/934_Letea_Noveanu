package WBT;

import WBT.domain.Nota;
import WBT.domain.Student;
import WBT.domain.Tema;
import WBT.repository.NotaXMLRepository;
import WBT.repository.StudentXMLRepository;
import WBT.repository.TemaXMLRepository;
import WBT.service.Service;
import WBT.validation.NotaValidator;
import WBT.validation.StudentValidator;
import WBT.validation.TemaValidator;
import WBT.validation.Validator;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.io.IOException;
import java.io.PrintWriter;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;
import java.nio.file.Paths;

import static org.junit.Assert.assertEquals;

public class TestAddStudent {
    private StudentXMLRepository studentXMLRepository;
    private NotaXMLRepository notaXMLRepository;
    private TemaXMLRepository temaXMLRepository;

    private Service service;

    @Before
    public void setup() {
        Validator<Student> vs = new StudentValidator();
        Validator<Nota> ns = new NotaValidator();
        Validator<Tema> ts = new TemaValidator();

        studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/WBT/studenti.xml");
        notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/WBT/note.xml");
        temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/WBT/teme.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }

    @After
    public void tearDown() {
        try {
            String defaultFileContent = new String(Files.readAllBytes(Paths.get("src/test/java/WBT/defaultFile.xml")), StandardCharsets.UTF_8);

            PrintWriter printWriter = new PrintWriter("src/test/java/WBT/studenti.xml");

            printWriter.print(defaultFileContent);
            printWriter.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    @Test
    public void testAddStudent1() {
        assertEquals(service.saveStudent("1", "Diana", 935), 1);
    }

    @Test
    public void testAddStudent2() {
        assertEquals(service.saveStudent("2", "Roland", 934), 1);
        assertEquals(service.saveStudent("2", "Roland", 934), 0);

    }
}