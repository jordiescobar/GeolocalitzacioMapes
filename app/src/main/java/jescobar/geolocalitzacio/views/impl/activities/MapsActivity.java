package jescobar.geolocalitzacio.views.impl.activities;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
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

import java.util.List;

import jescobar.geolocalitzacio.R;
import jescobar.geolocalitzacio.models.business.entities.Pois;
import jescobar.geolocalitzacio.network.PoisRetrofitSpiceService;
import jescobar.geolocalitzacio.network.Response;
import jescobar.geolocalitzacio.presenter.impl.MainViewPresenterImpl;
import jescobar.geolocalitzacio.presenter.interfaces.IMainViewPresenter;
import jescobar.geolocalitzacio.views.interfaces.IMainView;

public class MapsActivity extends BaseActivity implements OnMapReadyCallback, IMainView {

    public final class PoisesListListener implements RequestListener<Pois.Llista> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

            Toast.makeText(MapsActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onRequestSuccess(Pois.Llista result) {

            Toast.makeText(MapsActivity.this, "success", Toast.LENGTH_SHORT).show();
            presenter.setPois(result);
            presenter.showPois();

        }
    }

    /**
     * Implements a Listener class to manage received data
     */
    public final class UpdatePuntsInteresListener implements RequestListener<Response> {

        @Override
        public void onRequestFailure(SpiceException spiceException) {

            Toast.makeText(MapsActivity.this, R.string.download_error, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void onRequestSuccess(Response response) {

            Toast.makeText(MapsActivity.this, response.getMessage(), Toast.LENGTH_SHORT).show();

            if(response.getStatus()) {

                presenter.getPoisFromService();

            }
        }

    }

    private GoogleMap mMap;
    private SpiceManager spiceManager = new SpiceManager(PoisRetrofitSpiceService.class);
    private ListPoisListener listPoisListener;
    private IMainViewPresenter presenter;
    private Pois.Llista  llistaPois;
    private SupportMapFragment mapFragment;
    private ProgressBar progressBar;
    private Button btnCercar;

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

        btnCercar = (Button) findViewById(R.id.btn_Cercar);
        btnCercar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                clearMarkers();
                EditText nomCiutat = (EditText) findViewById(R.id.edText_cercador);

                if(nomCiutat.getText().toString().equals("")){

                    presenter.getPoisFromService();

                } else {

                    presenter.getPoisFromServiceByCityName(nomCiutat.getText().toString());

                }

                presenter.showPois();

            }
        });
    }


    @Override
    protected void onStart() {

        super.onStart();
        presenter.getPoisFromService();


    }

    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;
    }



    public SpiceManager getSpiceManager() {
        return spiceManager;
    }

    public RequestListener<Pois.Llista> getListListener() {
        return listPoisListener;
    }

    @Override
    public RequestListener<?> getUpdateListener() {
        return null;
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
    public void hideMarksPoints() {
        mapFragment.getView().setVisibility(View.GONE);
    }

    @Override
    public void showPoises(List<Pois> poisesList) {

        //LatLng lastLatLng = new LatLng(42.1727, 2.47631);
        for (int i = 0; i < poisesList.size(); i++) {

            LatLng latLng = new LatLng(poisesList.get(i).getLatitude(), poisesList.get(i).getLongitude());
            mMap.addMarker(new MarkerOptions().position(latLng).title(poisesList.get(i).getName()));
            mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(latLng, 17));

        }

    }

    public void clearMarkers(){

        this.mMap.clear();

    }
}
