package testing;

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
import testing.console.UI;

public class Main {
    public static void main(String[] args) {
        Validator<Student> studentValidator = new StudentValidator();
        Validator<Tema> temaValidator = new TemaValidator();
        Validator<Nota> notaValidator = new NotaValidator();

        StudentXMLRepository fileRepository1 = new StudentXMLRepository(studentValidator, "studenti.xml");
        TemaXMLRepository fileRepository2 = new TemaXMLRepository(temaValidator, "teme.xml");
        NotaXMLRepository fileRepository3 = new NotaXMLRepository(notaValidator, "note.xml");

        Service service = new Service(fileRepository1, fileRepository2, fileRepository3);
        UI consola = new UI(service);
        consola.run();

        //PENTRU GUI
        // de avut un check: daca profesorul introduce sau nu saptamana la timp
        // daca se introduce nota la timp, se preia saptamana din sistem
        // altfel, se introduce de la tastatura
    }
}
