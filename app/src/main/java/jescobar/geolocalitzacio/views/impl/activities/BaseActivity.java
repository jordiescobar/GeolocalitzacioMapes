package jescobar.geolocalitzacio.views.impl.activities;

import android.support.v7.app.AppCompatActivity;

import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.request.listener.RequestListener;

import jescobar.geolocalitzacio.network.PoisRetrofitSpiceService;

/**
 * Created by Pekee on 26/03/2016.
 */
public abstract class BaseActivity extends AppCompatActivity {

    private SpiceManager spiceManager = new SpiceManager(PoisRetrofitSpiceService.class);

    @Override
    protected void onStart() {

        spiceManager.start(this);
        super.onStart();

    }

    @Override
    protected void onStop() {

        spiceManager.shouldStop();
        super.onStop();

    }

    public SpiceManager getSpiceManager() {

        return spiceManager;

    }

    public abstract RequestListener<?> getListListener();
    public abstract RequestListener<?> getUpdateListener();

}