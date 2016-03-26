package jescobar.geolocalitzacio.models.business.entities;

import java.util.ArrayList;

/**
 * Created by Pekee on 14/03/2016.
 */
public class Pois {

    private int id;
    private String name, city;
    private double latitude, longitude;

    public Pois(int id, String name, String city, double latitude, double longitude) {
        this.id = id;
        this.name = name;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public double getLatitude() {
        return latitude;
    }

    public void setLatitude(double latitude) {
        this.latitude = latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public void setLongitude(double longitude) {
        this.longitude = longitude;
    }

    @SuppressWarnings("serial")
    public static class Llista extends ArrayList<Pois> {

    }
}
