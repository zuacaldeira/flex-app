/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import db.FlexUser;
import db.NewsSource;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class SourceViewTest {

    public SourceViewTest() {
    }

    @Test
    public void testNew() {
        System.out.println("new SourceView()");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "pt", "PT");
        assertNotNull(new SourceView(source));
    }

    /**
     * Test of getName method, of class SourceView.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "pt", "PT");
        assertNull(new SourceView(source).getName());
    }

    /**
     * Test of getDesc method, of class SourceView.
     */
    @Test
    public void testGetDesc() {
        System.out.println("getDesc");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "pt", "PT");
        assertNull(new SourceView(source).getDesc());
    }

    /**
     * Test of getCategory method, of class SourceView.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "pt", "PT");
        assertNull(new SourceView(source).getCategory());
    }

    /**
     * Test of getLanguage method, of class SourceView.
     */
    @Test
    public void testGetLanguage() {
        System.out.println("getLanguage");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "pt", "PT");
        assertNull(new SourceView(source).getLanguage());
    }

    /**
     * Test of getCountry method, of class SourceView.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "pt", "PT");
        assertNull(new SourceView(source).getCountry());
    }

    /**
     * Test of buttonClick method, of class SourceView.
     */
    @Test
    public void testButtonClick() {
        System.out.println("buttonClick");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "pt", "PT");
        SourceView view = new SourceView(source);
        assertNotNull(view);
    }

}
