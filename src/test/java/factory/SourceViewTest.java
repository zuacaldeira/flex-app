/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package factory;

import db.auth.FlexUser;
import db.news.NewsSource;
import db.news.Tag;
import db.relationships.TaggedSourceAs;
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
        NewsSource source = new NewsSource();
        assertNotNull(new SourceView(user, source));
    }

    /**
     * Test of getName method, of class SourceView.
     */
    @Test
    public void testGetName() {
        System.out.println("getName");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource();
        source.setName("name");
        assertNull(new SourceView(user, source).getName());
    }

    /**
     * Test of getDesc method, of class SourceView.
     */
    @Test
    public void testGetDesc() {
        System.out.println("getDesc");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource();
        source.setDescription("description");
        assertNull(new SourceView(user, source).getDesc());
    }

    /**
     * Test of getCategory method, of class SourceView.
     */
    @Test
    public void testGetCategory() {
        System.out.println("getCategory");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource();
        TaggedSourceAs tagged = new TaggedSourceAs();
        tagged.setSource(source);
        tagged.setTag(new Tag("category"));
        assertNull(new SourceView(user, source).getCategory());
    }

    /**
     * Test of getLanguage method, of class SourceView.
     */
    @Test
    public void testGetLanguage() {
        System.out.println("getLanguage");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource();
        source.setLanguage("language");
        assertNull(new SourceView(user, source).getLanguage());
    }

    /**
     * Test of getCountry method, of class SourceView.
     */
    @Test
    public void testGetCountry() {
        System.out.println("getCountry");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource();
        source.setCountry("PT");
        assertNull(new SourceView(user, source).getCountry());
    }

    /**
     * Test of buttonClick method, of class SourceView.
     */
    @Test
    public void testButtonClick() {
        System.out.println("buttonClick");
        FlexUser user = new FlexUser("test:username", "test:password");
        NewsSource source = new NewsSource();
        SourceView view = new SourceView(user, source);
        assertNotNull(view);
    }

}
