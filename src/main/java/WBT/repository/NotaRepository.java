package WBT.repository;

import WBT.domain.Nota;
import WBT.domain.Pair;
import WBT.validation.Validator;

public class NotaRepository extends AbstractCRUDRepository<Pair<String, String>, Nota> {
    public NotaRepository(Validator<Nota> validator) {
        super(validator);
    }
}
