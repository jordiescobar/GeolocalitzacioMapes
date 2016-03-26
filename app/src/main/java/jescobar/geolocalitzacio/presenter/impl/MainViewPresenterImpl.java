package jescobar.geolocalitzacio.presenter.impl;

import android.content.Context;

import java.util.List;

import jescobar.geolocalitzacio.models.business.entities.Pois;
import jescobar.geolocalitzacio.models.persistence.daos.impl.PoisRESTDAO;
import jescobar.geolocalitzacio.models.persistence.daos.interfaces.PoisDAO;
import jescobar.geolocalitzacio.presenter.interfaces.IMainViewPresenter;
import jescobar.geolocalitzacio.views.interfaces.IMainView;

/**
 * Created by Pekee on 14/03/2016.
 */
public class MainViewPresenterImpl implements IMainViewPresenter{

    private IMainView view;
    private List<Pois> poises;
    private PoisDAO poisDAO;


    @Override
    public void onCreate(IMainView view) {
        this.view = view;
        poisDAO = new PoisRESTDAO((Context)view);
    }

    @Override
    public void setPois(Pois.Llista list) {
        poises = list;
    }

    @Override
    public void getPoisFromService() {
        view.showProgressBar();

        // Aquí, és on demanem les dades
        // Obetnim dades del cloud
        poises = poisDAO.getAll();
    }

    @Override
    public void getPoisFromServiceByCityName(String cityName) {
        poises = poisDAO.getByCity(cityName);
    }

    @Override
    public void showPois() {
        view.hideProgressBar();
        view.showMarksPoints(poises);
    }

    @Override
    public void onItemClicked(int position) {

    }
}
