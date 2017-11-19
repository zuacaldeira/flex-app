/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package data;

import com.google.common.collect.Lists;
import db.FlexUser;
import db.Neo4jSessionFactory;
import db.NewsArticle;
import db.NewsSource;
import java.util.Collection;
import java.util.HashMap;
import org.neo4j.ogm.cypher.query.SortOrder;
import org.neo4j.ogm.session.Session;
import services.Neo4jQueries;
import utils.MyDateUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class ArticlesRepository {

    private final Session session;

    public ArticlesRepository() {
        session = Neo4jSessionFactory.getInstance().getNeo4jSession();
    }

    public Collection<NewsArticle> loadNodesWithoutBackend(DataProviderType type, String value, FlexUser user) {
        System.out.println("INSIDE loadNodesWithoutBackend");
        String query = createQuery(type, value, user);
        return Lists.newArrayList(session.query(NewsArticle.class, query, new HashMap<>()));
    }

    private String createQuery(DataProviderType type, String value, FlexUser user) {
        int limit = 10;
        switch (type) {
            case LATEST:
                return Neo4jQueries.getFindAllQuery(NewsArticle.class, user.getUsername(), null, null, new SortOrder().add(SortOrder.Direction.DESC, "title"), limit);
            case OLDEST:
                return Neo4jQueries.getFindAllQuery(NewsArticle.class, user.getUsername(), null, null, new SortOrder().add(SortOrder.Direction.ASC, "title"), limit);
            case READ:
                return Neo4jQueries.getMatchStateQuery(NewsArticle.class, "READ", user.getUsername(), null, null, limit);
            case FAVORITE:
                return Neo4jQueries.getMatchStateQuery(NewsArticle.class, "FAVORITE", user.getUsername(), null, null, limit);
            case FAKE:
                return Neo4jQueries.getMatchStateQuery(NewsArticle.class, "FAKE", user.getUsername(), null, null, limit);
            case CATEGORY:
                return Neo4jQueries.findArticlesWitCategory(user.getUsername(), value, limit);
            case PUBLISHER:
                return Neo4jQueries.findArticlesWithSource(user.getUsername(), value, limit);
            case LANGUAGES:
                return Neo4jQueries.findArticlesWithLanguage(user.getUsername(), value, limit);
            case COUNTRIES:
                return Neo4jQueries.findArticlesWithCountry(user.getUsername(), value, limit);
            case SEARCH:
                return Neo4jQueries.findArticlesWithText(user.getUsername(), value, limit);
            default:
                return Neo4jQueries.getFindAllQuery(NewsArticle.class, user.getUsername(), null, null, new SortOrder().add(SortOrder.Direction.DESC), limit);
        }
    }

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
