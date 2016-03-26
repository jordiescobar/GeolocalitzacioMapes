package jescobar.geolocalitzacio.models.persistence.daos.interfaces;

import java.util.List;

import jescobar.geolocalitzacio.models.business.entities.Pois;

/**
 * Created by Pekee on 14/03/2016.
 */
public interface PoisDAO {

    List<Pois> getByCity(String city);
    List<Pois> getAll();

}
