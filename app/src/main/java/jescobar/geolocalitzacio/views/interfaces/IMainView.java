package jescobar.geolocalitzacio.views.interfaces;

import java.util.List;

import jescobar.geolocalitzacio.models.business.entities.Pois;

/**
 * Created by Pekee on 14/03/2016.
 */
public interface IMainView {

    public void showProgressBar();

    public void hideProgressBar();

    public void showMarksPoints();

    public void hideMarksPoints();

    void showPoises(List<Pois> poisesList);


}
