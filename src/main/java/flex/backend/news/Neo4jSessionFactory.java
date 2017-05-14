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

    private static Neo4jSessionFactory factory = new Neo4jSessionFactory();
    private static final String defaultURL="http://localhost:7474";
    private static final String username ="neo4j";
    private static final String password ="unicidade";

    private Configuration configuration;
    private SessionFactory sessionFactory;
    
    public static Neo4jSessionFactory getInstance() {
	return factory;
    }
    
    private Neo4jSessionFactory() {
        Configuration.Builder builder = new Configuration.Builder();
        configuration = builder.uri(defaultURL).credentials(username, password).autoIndex("assert").build();
        sessionFactory = new SessionFactory(configuration, "flex.backend.news.db");
    }
    
    public Session getNeo4jSession() {
	return sessionFactory.openSession();
    }

}
