package flex.frontend.ui.map;

import flex.backend.db.ApiSource;
import flex.frontend.ui.Countries;

/**
 * Created by zua on 12/04/17.
 */
public class GeoInfo {
    private final ApiSource source;
    private final CityData countryData;

    public GeoInfo(ApiSource source) {
        this.source = source;
        countryData = Countries.getCountryData(source.getCountry());
    }

    public ApiSource getSource() {
        return source;
    }

    public CityData getCountryData() {
        return countryData;
    }
}
