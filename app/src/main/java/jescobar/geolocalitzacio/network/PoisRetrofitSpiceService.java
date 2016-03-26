package jescobar.geolocalitzacio.network;

import com.octo.android.robospice.retrofit.RetrofitGsonSpiceService;

/**
 * Created by Pekee on 14/03/2016.
 */
public class PoisRetrofitSpiceService extends RetrofitGsonSpiceService {

    private final static String BASE_URL = "http://192.168.0.247/pmdm/pois/v1/pois";

    public void onCreate() {
        super.onCreate();
        addRetrofitInterface(PoisAPI.class);
    }


    @Override
    protected String getServerUrl() {
        return BASE_URL;
    }
}
