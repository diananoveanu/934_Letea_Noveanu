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
import WBT.console.UI;

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
