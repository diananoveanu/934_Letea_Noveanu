package testing.repository;

import testing.domain.Nota;
import testing.domain.Pair;
import testing.validation.Validator;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
