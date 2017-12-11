/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import db.auth.FlexUser;
import db.news.NewsArticle;
import db.news.NewsSource;
import io.reactivex.Observable;
import services.news.NewsArticleService;
import utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticlesRepository {

    public ArticlesRepository() {
    }

    public Observable<NewsArticle> loadNodes(DataProviderType type, String value, FlexUser user) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
        String username = (user != null) ? user.getUsername() : null;
        switch (type) {
            case CATEGORY:
                return Observable.fromIterable(service.findArticlesTaggedAs(username, getCategoryDBCaption(value)));
            case PUBLISHER:
                return Observable.fromIterable(service.findArticlesPublishedBy(username, getSourceIdForSourceName(value)));
            case LANGUAGES:
                return Observable.fromIterable(service.findArticlesWithLanguage(username, MyDateUtils.getLanguageCode(value)));
            case COUNTRIES:
                return Observable.fromIterable(service.findArticlesWithCountry(username, MyDateUtils.getCountryCode(value)));
            case SEARCH:
                return Observable.fromIterable(service.findArticlesWithText(value));
            default:
                return Observable.fromIterable(service.findLatest(username));
        }
    }

    public Observable<NewsArticle> loadNodes(DataProviderType type, String value) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
        switch (type) {
            case CATEGORY:
                return Observable.fromIterable(service.findArticlesTaggedAs(getCategoryDBCaption(value)));
            case PUBLISHER:
                return Observable.fromIterable(service.findArticlesPublishedBy(getSourceIdForSourceName(value)));
            case LANGUAGES:
                return Observable.fromIterable(service.findArticlesWithLanguage(MyDateUtils.getLanguageCode(value)));
            case COUNTRIES:
                return Observable.fromIterable(service.findArticlesWithCountry(MyDateUtils.getCountryCode(value)));
            case SEARCH:
                return Observable.fromIterable(service.findArticlesWithText(value));
            default:
                return Observable.fromIterable(service.findLatest());
        }
    }

    public Observable<NewsArticle> loadNodes(DataProviderType type, FlexUser user) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
        String username = (user != null) ? user.getUsername() : null;
        switch (type) {
            case LATEST:
                return Observable.fromIterable(service.findLatest(username));
            case OLDEST:
                return Observable.fromIterable(service.findOldest(username));
            case READ:
                return Observable.fromIterable(service.findRead(username));
            case FAVORITE:
                return Observable.fromIterable(service.findFavorite(username));
            case FAKE:
                return Observable.fromIterable(service.findFake(username));
            default:
                return Observable.fromIterable(service.findLatest(username));
        }
    }

    public Observable<NewsArticle> loadNodes(DataProviderType type) {
        NewsArticleService service = ServiceLocator.getInstance().findArticlesService();
        switch (type) {
            case LATEST:
                return Observable.fromIterable(service.findLatest());
            case OLDEST:
                return Observable.fromIterable(service.findOldest());
            default:
                return Observable.fromIterable(service.findLatest());
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
