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
import utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticlesRepository {

    public Collection<NewsArticle> loadNodes(DataProviderType type, String value, FlexUser user) {
        if (value == null || value.isEmpty()) {
            return loadNodes(type, user);
        }

        switch (type) {
            case CATEGORY:
                return ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(user.getUsername(), getCategoryDBCaption(value));
            case PUBLISHER:
                return ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(user.getUsername(), getSourceIdForSourceName(value));
            case LANGUAGES:
                return ServiceLocator.getInstance().findArticlesService().findArticlesWithLanguage(user.getUsername(), MyDateUtils.getLanguageCode(value));
            case COUNTRIES:
                return ServiceLocator.getInstance().findArticlesService().findArticlesWithCountry(user.getUsername(), MyDateUtils.getCountryCode(value));
            case SEARCH:
                return ServiceLocator.getInstance().findArticlesService().findArticlesWithText(value);
            default:
                return ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername());
        }
    }

    public Collection<NewsArticle> loadNodes(DataProviderType type, FlexUser user) {
        switch (type) {
            case LATEST:
                return ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername());
            case OLDEST:
                return ServiceLocator.getInstance().findArticlesService().findOldest(user.getUsername());
            case READ:
                return ServiceLocator.getInstance().findArticlesService().findAllRead(user.getUsername());
            case FAVORITE:
                return ServiceLocator.getInstance().findArticlesService().findAllFavorite(user.getUsername());
            case FAKE:
                return ServiceLocator.getInstance().findArticlesService().findAllFake(user.getUsername());
            default:
                return ServiceLocator.getInstance().findArticlesService().findLatest(user.getUsername());
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
