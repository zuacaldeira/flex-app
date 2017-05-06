/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;
import flex.backend.news.db.NewsArticle;
import flex.backend.news.db.NewsSource;
import flex.backend.news.db.Publishes;

/**
 *
 * @author zua
 */
public class Neo4jQueries {
    
    public static String findSourceBySourceId(String sourceId) {
        return "MATCH (n:NewsSource{sourceId:" + wrapString(sourceId) + "}) RETURN n";
    }
    
    public static String findArticleByTitle(String title) {
        return "MATCH (n:NewsArticle{title:" + wrapString(title) + "}) RETURN n";
    }
    
    public static String findAuthorByName(String name) {
        return "MATCH (n:NewsAuthor{name:" + wrapString(name) + "}) RETURN n";
    }
    public static String mergeQuery(NewsArticle article) {
        return "MERGE (n:NewsArticle{title:" + wrapString(article.getTitle()) + "}) RETURN n";
    }

    public static String mergeQuery(NewsSource source) {
        return "MERGE (n:NewsSource{sourceId:" + wrapString(source.getSourceId()) + "}) RETURN n";
    }

    static String mergeQuery(String className, String property, String value) {
        return "MERGE (n:" + className + "{" + property + ":" + wrapString(value) + "}) RETURN n";
    }

    public static String mergeToPublish(Publishes toPublish) {
        String n = "n = MERGE (n:NewsSource{" + "sourceId:" + wrapString(toPublish.getSource().getSourceId()) + "}) RETURN n";
        String m = "m = MERGE (m:NewsAuthor{" + "name:" + wrapString(toPublish.getAuthor().getName()) + "}) RETURN m";
        String r = " MERGE (n)-[r:PUBLISH]->(m) RETURN r";
        return n + m + r;
    }

    private static String wrapString(String value) {
        if(value != null) {
            return "\"" + value + "\"";
        }
        else {
            return "";
        }
    }
    
}
