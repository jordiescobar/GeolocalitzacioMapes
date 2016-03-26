package jescobar.geolocalitzacio.presenter.interfaces;

import jescobar.geolocalitzacio.models.business.entities.Pois;
import jescobar.geolocalitzacio.views.interfaces.IMainView;

/**
 * Created by Pekee on 14/03/2016.
 */
public interface IMainViewPresenter {

    public void onCreate(IMainView view);

    public void setPois(Pois.Llista list);

    public void getPoisFromService();

    void getPoisFromServiceByCityName(String cityName);

    public void showPois();

    public void onItemClicked(int position);


}
