package testing;

import org.junit.AfterClass;
import org.junit.Before;
import org.junit.BeforeClass;
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
    public void setup() {
        Validator<Student> vs = new StudentValidator();
        Validator<Nota> ns = new NotaValidator();
        Validator<Tema> ts = new TemaValidator();

        studentXMLRepository = new StudentXMLRepository(vs, "src/test/java/testing/studenti.xml");
        notaXMLRepository = new NotaXMLRepository(ns, "src/test/java/testing/note.xml");
        temaXMLRepository = new TemaXMLRepository(ts, "src/test/java/testing/teme.xml");
        service = new Service(studentXMLRepository, temaXMLRepository, notaXMLRepository);

    }

    @BeforeClass
    public static void tearDown() {
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


    @AfterClass
    public static void tearDown1() {
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
        assertEquals(service.saveNota("1", "1", 9, 3, "good"), 1);
    }

    @Test
    public void testBigBang(){
        assertEquals(service.saveStudent("2", "Alexandru", 231), 1);
        assertEquals(service.saveTema("2", "1", 8, 7), 1);
        assertEquals(service.saveNota("2", "2", 9, 3, "good"), 1);

    }
}