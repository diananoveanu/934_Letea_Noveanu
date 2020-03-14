package WBT.repository;

import WBT.domain.Student;
import WBT.validation.Validator;

public class StudentRepository extends AbstractCRUDRepository<String, Student> {
    public StudentRepository(Validator<Student> validator) {
        super(validator);
    }
}

