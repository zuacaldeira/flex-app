/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.services;
import flex.backend.news.db.ApiArticle;

/**
 *
 * @author zua
 */
public class Neo4jQueries {
    
    public static String findSourceBySourceId(String sourceId) {
        return "MATCH (n:ApiSource{sourceId:" + wrapString(sourceId) + "}) RETURN n";
    }
    
    public static String findArticleByTitle(String title) {
        return "MATCH (n:ApiArticle{" + "title:" + wrapString(title) + "}) RETURN n";
    }
    
    public static String mergeArticle(ApiArticle article) {
        return "MERGE (n:ApiArticle{" + "title:" + wrapString(article.getTitle()) + "}) RETURN n";
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
