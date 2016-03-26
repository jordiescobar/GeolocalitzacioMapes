package jescobar.geolocalitzacio.network;

import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import jescobar.geolocalitzacio.models.business.entities.Pois;

/**
 * Created by Pekee on 14/03/2016.
 */
public class PoisRetrofitSpiceRequest extends RetrofitSpiceRequest<Pois.Llista, PoisAPI> {

    public PoisRetrofitSpiceRequest() {
        super(Pois.Llista.class, PoisAPI.class);
    }

    @Override
    public Pois.Llista loadDataFromNetwork() throws Exception {
        return null;
    }

}
