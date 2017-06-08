/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news;

import org.neo4j.ogm.config.Configuration;
import org.neo4j.ogm.session.Session;
import org.neo4j.ogm.session.SessionFactory;



/**
 *
 * @author zua
 */
public class Neo4jSessionFactory {

    private static String DATABASE_URL;
    private static String USERNAME;
    private static String PASSWORD;

    private static Neo4jSessionFactory factory;

    private Configuration configuration;
    private SessionFactory sessionFactory;
    
    private Neo4jSessionFactory() {
        if(DATABASE_URL == null) {
            DATABASE_URL = "http://localhost:7474";

            if(USERNAME == null) {
                USERNAME = "neo4j";
            }

            if(PASSWORD == null) {
                PASSWORD = "unicidade";
            }
        }

        Configuration.Builder builder = new Configuration.Builder();
        configuration = builder.uri(DATABASE_URL).credentials(USERNAME, PASSWORD).autoIndex("validate").build();
        sessionFactory = new SessionFactory(configuration, "flex.backend.news.db");
    }
    
    public static Neo4jSessionFactory getInstance() {
        if(factory == null) {
            factory = new Neo4jSessionFactory();
        }
	return factory;
    }
    
    public Session getNeo4jSession() {
	return sessionFactory.openSession();
    }

    public static void prepareForTest(String url) {
        DATABASE_URL = url;
        USERNAME = "neo4j";
        PASSWORD = "password";
    }

    
    
}
