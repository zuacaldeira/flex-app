/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import db.auth.FlexUser;
import backend.services.news.PublishedByService;
import backend.utils.MyDateUtils;
import db.news.NewsSource;
import db.relationships.PublishedBy;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class PublishedByRepository {

    public PublishedByRepository() {
    }

    public Iterable<PublishedBy> loadNodes(DataProviderType type, String value, FlexUser user) {
        PublishedByService service = ServiceLocator.getInstance().findPublishedByService();
        String username = (user != null) ? user.getUsername() : null;
        switch (type) {
            case CATEGORY:
                return service.findArticlesTaggedAs(username, getCategoryDBCaption(value));
            case PUBLISHER:
                return service.findArticlesPublishedBy(username, getSourceIdForSourceName(value));
            case LANGUAGES:
                return service.findArticlesWithLanguage(username, MyDateUtils.getLanguageCode(value));
            case COUNTRIES:
                return service.findArticlesWithCountry(username, MyDateUtils.getCountryCode(value));
            case SEARCH:
                return service.findArticlesWithText(value);
            default:
                return service.findLatest(username);
        }
    }

    public Iterable<PublishedBy> loadNodes(DataProviderType type, String value) {
        PublishedByService service = ServiceLocator.getInstance().findPublishedByService();
        switch (type) {
            case CATEGORY:
                return service.findArticlesTaggedAs(getCategoryDBCaption(value));
            case PUBLISHER:
                return service.findArticlesPublishedBy(getSourceIdForSourceName(value));
            case LANGUAGES:
                return service.findArticlesWithLanguage(MyDateUtils.getLanguageCode(value));
            case COUNTRIES:
                return service.findArticlesWithCountry(MyDateUtils.getCountryCode(value));
            case SEARCH:
                return service.findArticlesWithText(value);
            default:
                return service.findLatest();
        }
    }

    public Iterable<PublishedBy> loadNodes(DataProviderType type, FlexUser user) {
        PublishedByService service = ServiceLocator.getInstance().findPublishedByService();
        String username = (user != null) ? user.getUsername() : null;
        switch (type) {
            case LATEST:
                return service.findLatest(username);
            case OLDEST:
                return service.findOldest(username);
            case READ:
                return service.findRead(username);
            case FAVORITE:
                return service.findFavorite(username);
            case FAKE:
                return service.findFake(username);
            default:
                return service.findLatest(username);
        }
    }

    public Iterable<PublishedBy> loadNodes(DataProviderType type) {
        PublishedByService service = ServiceLocator.getInstance().findPublishedByService();
        switch (type) {
            case LATEST:
                return service.findLatest();
            case OLDEST:
                return service.findOldest();
            default:
                return service.findLatest();
        }
    }

    protected static String getCategoryDBCaption(String cat) {
        if (!cat.isEmpty()) {
            char c = cat.charAt(0);
            StringBuilder builder = new StringBuilder(cat.trim());
            builder = builder.replace(0, 1, String.valueOf(Character.toLowerCase(c)));
            String result = builder.toString();
            System.out.println(result);
            result = result.replace(" ", "-");
            System.out.println(result);
            return result;
        }
        return cat;
    }

    protected static String getSourceIdForSourceName(String value) {
        NewsSource source = ServiceLocator.getInstance().findSourcesService().findSourceNamed(value);
        return (source != null) ? source.getSourceId() : null;
    }

}
