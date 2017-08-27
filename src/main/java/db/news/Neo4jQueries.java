/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package db.news;

import utils.FlexUtils;

/**
 *
 * @author zua
 */
public class Neo4jQueries {
    
    private static Neo4jQueries instance;
    
    private Neo4jQueries() {}
    
    
    public static Neo4jQueries getInstance() {
        if(instance == null) {
            instance = new Neo4jQueries();
        }
        return instance;
    }
    
    public  <T extends GraphEntity> String findQuery(T object) {
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
    
    public  <T extends GraphEntity> String findByIdQuery(T object) {
        return findQuery(object.getClass().getSimpleName(), "id", object.getId());
    }
    

    
    public  String findSourceBySourceId(String sourceId) {
        return findQuery(NewsSource.class.getSimpleName(), "sourceId", FlexUtils.getInstance().wrapUp(sourceId));
    }
    
    public  String findArticleByTitle(String title) {
        return findQuery(NewsArticle.class.getSimpleName(), "title", FlexUtils.getInstance().wrapUp(title));
    }
    
    public  String findAuthorByName(String name) {
        return findQuery(NewsAuthor.class.getSimpleName(), "name", FlexUtils.getInstance().wrapUp(name));
    }
    
    private  String findNewsSourceQuery(NewsSource newsSource) {
        //System.out.println("Source ID = " + newsSource.getSourceId());
        return findSourceBySourceId(newsSource.getSourceId());
    }

    private  String findNewsAuthorQuery(NewsAuthor newsAuthor) {
        return findAuthorByName(newsAuthor.getName());
    }
    
    private  String findNewsArticleQuery(NewsArticle newsArticle) {
        return findArticleByTitle(newsArticle.getTitle());
    }

    private  String findQuery(String className, String property, String value) {
        String query = "MATCH (n:" + className + "{" + property + ":" + value + "}) RETURN n";
        //System.out.println(query);
        return query;
    }

    private  String findQuery(String className, String property, Long value) {
        return "MATCH (n:" + className + "{" + property + ":" + value + "}) RETURN n";
    }

}
