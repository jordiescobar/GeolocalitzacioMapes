package jescobar.geolocalitzacio.network;

import jescobar.geolocalitzacio.models.business.entities.Pois;
import retrofit.http.GET;
import retrofit.http.Path;

/**
 * Created by Pekee on 14/03/2016.
 */
public interface PoisAPI {

    @GET("/pois")
    Pois.Llista getAll();

    @GET("/pois/{city}")
    Pois getById(@Path("city") int id);

}
