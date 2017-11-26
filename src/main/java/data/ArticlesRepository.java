/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import db.FlexUser;
import db.NewsArticle;
import db.NewsSource;
import java.util.Collection;
import services.NewsArticleServiceInterface;
import utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticlesRepository {

    public ArticlesRepository() {
    }

    public Collection<NewsArticle> loadNodes(DataProviderType type, String value, FlexUser user) {
        if (value == null || value.isEmpty()) {
            return loadNodes(type, user);
        }
        String username = (user != null) ? user.getUsername() : null;
        NewsArticleServiceInterface service = ServiceLocator.getInstance().findArticlesService();
        switch (type) {
            case CATEGORY:
                return service.findArticlesWithCategory(username, getCategoryDBCaption(value));
            case PUBLISHER:
                return service.findArticlesWithSource(username, getSourceIdForSourceName(value));
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

    public Collection<NewsArticle> loadNodes(DataProviderType type, FlexUser user) {
        String username = (user != null) ? user.getUsername() : null;
        NewsArticleServiceInterface service = ServiceLocator.getInstance().findArticlesService();
        switch (type) {
            case LATEST:
                return service.findLatest(username);
            case OLDEST:
                return service.findOldest(username);
            case READ:
                return service.findAllRead(username);
            case FAVORITE:
                return service.findAllFavorite(username);
            case FAKE:
                return service.findAllFake(username);
            default:
                return service.findLatest(username);
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
