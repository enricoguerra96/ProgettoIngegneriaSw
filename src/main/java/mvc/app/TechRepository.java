package mvc.app;

import org.springframework.data.repository.CrudRepository;

import java.util.List;
import java.util.Optional;

public interface TechRepository extends CrudRepository<Technician, Long> {

    //List<Technician> findByLastName(String lastName);

    boolean existsById(long id);

    Technician findById(long id);
}
