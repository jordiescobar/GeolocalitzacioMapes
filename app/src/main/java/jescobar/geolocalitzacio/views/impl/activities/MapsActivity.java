package jescobar.geolocalitzacio.views.impl.activities;

import android.os.Bundle;
import android.support.v4.app.FragmentActivity;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.octo.android.robospice.SpiceManager;
import com.octo.android.robospice.persistence.exception.SpiceException;
import com.octo.android.robospice.request.listener.RequestListener;

import jescobar.geolocalitzacio.R;
import jescobar.geolocalitzacio.models.business.entities.Pois;
import jescobar.geolocalitzacio.network.PoisRetrofitSpiceService;
import jescobar.geolocalitzacio.presenter.impl.MainViewPresenterImpl;
import jescobar.geolocalitzacio.presenter.interfaces.IMainViewPresenter;
import jescobar.geolocalitzacio.views.interfaces.IMainView;

public class MapsActivity extends FragmentActivity implements OnMapReadyCallback, IMainView {

    private GoogleMap mMap;
    private SpiceManager spiceManager = new SpiceManager(PoisRetrofitSpiceService.class);
    private ListPoisListener listPoisListener;
    private IMainViewPresenter presenter;
    private Pois.Llista  llistaPois;
    private SupportMapFragment mapFragment;
    private ProgressBar progressBar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        presenter = new MainViewPresenterImpl();
        presenter.onCreate(this);

        progressBar = (ProgressBar) findViewById(R.id.progressBar);
    }


    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Sydney and move the camera
        LatLng sydney = new LatLng(-34, 151);
        mMap.addMarker(new MarkerOptions().position(sydney).title("Marker in Sydney"));
        mMap.moveCamera(CameraUpdateFactory.newLatLng(sydney));
    }


    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    public RequestListener<Pois.Llista> getListListener() {
        return listPoisListener;
    }

    // ============================================================================================
    // Ens definirà que fer en cas de que retorni un resultat o no.
    // ============================================================================================
    /**
     * Implements a Listener class to manage received data
     */
    public final class ListPoisListener implements RequestListener<Pois.Llista> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {
            hideProgressBar();
            Toast.makeText(MapsActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();
        }

        @Override
        public void onRequestSuccess(Pois.Llista result) {
            Toast.makeText(MapsActivity.this, "success ", Toast.LENGTH_SHORT).show();
            llistaPois = result;
            presenter.setPois(result);
            presenter.showPois();
        }
    }

    // ============================================================================================
    // IMPL. IMainView
    // ============================================================================================

    @Override
    public void showProgressBar() {
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgressBar() {
        progressBar.setVisibility(View.GONE);
    }

    @Override
    public void showMarksPoints() {
        mapFragment.getView().setVisibility(View.VISIBLE);
    }

    @Override
    public void hideMarksPoints() {
        mapFragment.getView().setVisibility(View.GONE);
    }
}
