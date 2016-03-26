package jescobar.geolocalitzacio.models.persistence.daos.impl;

import android.content.Context;

import com.octo.android.robospice.persistence.DurationInMillis;
import com.octo.android.robospice.request.listener.RequestListener;
import com.octo.android.robospice.request.retrofit.RetrofitSpiceRequest;

import java.util.ArrayList;
import java.util.List;

import jescobar.geolocalitzacio.R;
import jescobar.geolocalitzacio.models.business.entities.Pois;
import jescobar.geolocalitzacio.models.persistence.daos.interfaces.PoisDAO;
import jescobar.geolocalitzacio.network.PoisAPI;
import jescobar.geolocalitzacio.views.impl.activities.BaseActivity;
import jescobar.geolocalitzacio.views.impl.activities.MapsActivity;

/**
 * Created by Pekee on 14/03/2016.
 */
public class PoisRESTDAO implements PoisDAO{

    Context context;

    public PoisRESTDAO(Context context){
        this.context = context;
    }


    @Override
    public List<Pois> getByCity(final String city) {

        List<Pois> puntsInteres = new ArrayList();

        RetrofitSpiceRequest<Pois.Llista, PoisAPI> request = new RetrofitSpiceRequest<Pois.Llista, PoisAPI>(Pois.Llista.class, PoisAPI.class) {

            @Override
            public Pois.Llista loadDataFromNetwork() throws Exception {

                return getService().getByCity(city);

            }
        };

        ((BaseActivity) context).getSpiceManager().execute(request, context.getString(R.string.cache_pois), 100, (RequestListener<Pois.Llista>) ((BaseActivity) context).getListListener());

        return puntsInteres;

    }

    @Override
    public List<Pois> getAll() {

        List<Pois> poises = new ArrayList();

        // create request object
        RetrofitSpiceRequest<Pois.Llista, PoisAPI> request = new RetrofitSpiceRequest<Pois.Llista, PoisAPI>(Pois.Llista.class, PoisAPI.class) {
            @Override
            public Pois.Llista loadDataFromNetwork() throws Exception {
                return getService().getAll();
            }
        };
        ((MapsActivity) context).getSpiceManager()
                .execute(request, context.getString(
                        R.string.cache_pois),
                        DurationInMillis.ONE_MINUTE,
                        (RequestListener<Pois.Llista>) ((MapsActivity) context).getListListener()
                );


        return poises;
    }

}
