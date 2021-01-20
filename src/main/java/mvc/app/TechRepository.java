package mvc.app;

import org.springframework.data.repository.CrudRepository;

public interface TechRepository extends CrudRepository<Technician, Long> {

    boolean existsById(long id);

    Technician findById(long id);
}
