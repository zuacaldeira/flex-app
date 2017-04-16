package flex.frontend.ui;

/**
 * Created by zua on 12/04/17.
 */
public class CountryData {
    private final String country;
    private final String city;
    private final String id;
    private double altitude;
    private double latitude;
    private double longitude;

    public CountryData(String id, String country, String city, double latitude, double longitude, double altitude) {
        this.id = id;
        this.country = country;
        this.city = city;
        this.latitude = latitude;
        this.longitude = longitude;
        this.altitude = altitude;
    }


    public double getLatitude() {
        return latitude;
    }

    public double getLongitude() {
        return longitude;
    }

    public String getCountry() {
        return country;
    }

    public String getCity() {
        return city;
    }

    public double getAltitude() {
        return altitude;
    }

    public String getId() {
        return id;
    }
}
