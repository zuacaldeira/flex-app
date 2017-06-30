/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.source;

import ui.news.source.SourceView;
import com.tngtech.java.junit.dataprovider.DataProvider;
import com.tngtech.java.junit.dataprovider.DataProviderRunner;
import com.tngtech.java.junit.dataprovider.UseDataProvider;
import db.news.NewsSource;
import org.junit.Test;
import static org.junit.Assert.*;
import org.junit.runner.RunWith;

/**
 *
 * @author zua
 */
@RunWith(DataProviderRunner.class)
public class SourceViewTest {
    
    public SourceViewTest() {
    }
    
    @DataProvider
    public static Object[][] sourcesProvider() {
        NewsSource source = new NewsSource("sourceId", "name", "description", "url", "category", "language", "country");
        SourceView view = new SourceView(source);
        return new Object[][] {
            {view, source}  
        };
    }

    /**
     * Test of getSource method, of class SourceView.
     * @param view
     * @param source
     */
    @Test
    @UseDataProvider("sourcesProvider")
    public void testGetNewsSource(SourceView view, NewsSource source) {
        System.out.println("getApiSource");
        assertNotNull(view.getItem());
        assertEquals(source.getSourceId(), view.getItem().getSourceId());
    }

    /**
     * Test of getName method, of class SourceView.
     * @param view
     * @param source
     */
    @Test
    @UseDataProvider("sourcesProvider")
    public void testGetName(SourceView view, NewsSource source) {
        System.out.println("getName");
        assertEquals(source.getName(), view.getName().getValue());
    }

    /**
     * Test of getDesc method, of class SourceView.
     * @param view
     * @param source
     */
    @Test
    @UseDataProvider("sourcesProvider")
    public void testGetDesc(SourceView view, NewsSource source) {
        System.out.println("getDesc");
        assertEquals(source.getDescription(), view.getDesc().getValue());
    }

    /**
     * Test of getCategory method, of class SourceView.
     * @param view
     * @param source
     */
    @Test
    @UseDataProvider("sourcesProvider")
    public void testGetCategory(SourceView view, NewsSource source) {
        System.out.println("getCategory");
        assertEquals(source.getCategory(), view.getCategory().getValue());
    }

    /**
     * Test of getLanguage method, of class SourceView.
     * @param view
     * @param source
     */
    @Test
    @UseDataProvider("sourcesProvider")
    public void testGetLanguage(SourceView view, NewsSource source) {
        System.out.println("getLanguage");
        assertEquals(source.getLanguage(), view.getLanguage().getValue());
    }

    /**
     * Test of getCountry method, of class SourceView.
     * @param view
     * @param source
     */
    @Test
    @UseDataProvider("sourcesProvider")
    public void testGetCountry(SourceView view, NewsSource source) {
        System.out.println("getCountry");
        assertEquals(source.getCountry(), view.getCountry().getValue());
    }
    
}
