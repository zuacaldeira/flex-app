/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

/**
 *
 * @author zua
 */
public class Neo4jQueries {
    
    public static <T extends GraphEntity> String findQuery(T object) {
        if(object instanceof NewsSource) {
            return findNewsSourceQuery((NewsSource) object);
        }
        else if(object instanceof NewsAuthor) {
            return findNewsAuthorQuery((NewsAuthor) object);
        }
        else if(object instanceof NewsArticle) {
            return findNewsArticleQuery((NewsArticle) object);
        }
        else {
            return findByIdQuery(object);
        }
    }
    
    public static <T extends GraphEntity> String findByIdQuery(T object) {
        return findQuery(object.getClass().getSimpleName(), "id", object.getId());
    }
    

    
    public static String findSourceBySourceId(String sourceId) {
        return findQuery(NewsSource.class.getSimpleName(), "sourceId", sourceId);
    }
    
    public static String findArticleByTitle(String title) {
        return findQuery(NewsArticle.class.getSimpleName(), "title", title);
    }
    
    public static String findAuthorByName(String name) {
        return findQuery(NewsAuthor.class.getSimpleName(), "name", name);
    }
    
    private static String wrapString(String value) {
        if(value != null) {
            return "\"" + value + "\"";
        }
        else {
            return "";
        }
    }

    private static String findNewsSourceQuery(NewsSource newsSource) {
        return findSourceBySourceId(newsSource.getSourceId());
    }

    private static String findNewsAuthorQuery(NewsAuthor newsAuthor) {
        return findAuthorByName(newsAuthor.getName());
    }
    
    private static String findNewsArticleQuery(NewsArticle newsArticle) {
        return findArticleByTitle(newsArticle.getTitle());
    }

    private static String findQuery(String className, String property, String value) {
        return "MATCH (n:" + className + "{" + property + ":" + wrapString(value) + "}) RETURN n";
    }

    private static String findQuery(String className, String property, Long value) {
        return "MATCH (n:" + className + "{" + property + ":" + value + "}) RETURN n";
    }

    private static String mergeQuery(String className, String property, String value) {
        return "MERGE (n:" + className + "{" + property + ":" + wrapString(value) + "}) RETURN n";
    }
}
