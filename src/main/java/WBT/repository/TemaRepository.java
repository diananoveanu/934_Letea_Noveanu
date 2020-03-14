package WBT.repository;

import WBT.domain.Tema;
import WBT.validation.Validator;

public class TemaRepository extends AbstractCRUDRepository<String, Tema> {
    public TemaRepository(Validator<Tema> validator) {
        super(validator);
    }
}

