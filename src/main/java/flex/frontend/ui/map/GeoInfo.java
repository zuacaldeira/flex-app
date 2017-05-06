package flex.frontend.ui.map;

import flex.backend.news.db.NewsSource;
import flex.frontend.ui.news.Countries;

/**
 * Created by zua on 12/04/17.
 */
public class GeoInfo {
    private final NewsSource source;
    private final CityData countryData;

    public GeoInfo(NewsSource source) {
        this.source = source;
        countryData = Countries.getCountryData(source.getCountry());
    }

    public NewsSource getSource() {
        return source;
    }

    public CityData getCountryData() {
        return countryData;
    }
}
