/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news;

import db.news.Neo4jTest;
import javax.naming.NamingException;
import org.junit.AfterClass;
import org.junit.BeforeClass;
import utils.MockInitialContext;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public abstract class UITest extends Neo4jTest {
    
    @BeforeClass
    public static void mock() throws NamingException {
        MockInitialContext mockContext = new MockInitialContext();
        ServiceLocator.getInstance().setInitialContext(mockContext);
    }
    
    @AfterClass
    public static void unmock() {
        ServiceLocator.getInstance().setInitialContext(null);
    }
}
