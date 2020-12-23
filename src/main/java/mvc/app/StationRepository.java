package mvc.app;

import org.springframework.data.repository.CrudRepository;

public interface StationRepository extends CrudRepository<WeatherStation, Long> {

    boolean existsById(long id);

    @Override
    <S extends WeatherStation> S save(S entity);

    WeatherStation findById(long id);
}
