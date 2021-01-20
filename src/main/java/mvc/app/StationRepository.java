package mvc.app;

import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<WeatherStation, Long> {

    boolean existsById(long id);

    WeatherStation findById(long id);
}