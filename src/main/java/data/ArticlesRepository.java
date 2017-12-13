/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import db.auth.FlexUser;
import db.news.NewsArticle;
import db.news.NewsSource;
import backend.services.news.NewsArticleService;
import backend.utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticlesRepository {

    public ArticlesRepository() {
    }

    public Iterable<NewsArticle> loadNodes(DataProviderType type, String value, FlexUser user) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
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

    public Iterable<NewsArticle> loadNodes(DataProviderType type, String value) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
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

    public Iterable<NewsArticle> loadNodes(DataProviderType type, FlexUser user) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
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

    public Iterable<NewsArticle> loadNodes(DataProviderType type) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
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
