/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import db.news.NewsAuthor;
import java.util.Set;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class FlexUtilsTest {
    
    public FlexUtilsTest() {
    }

    /**
     * Test of extractAuthors method, of class FlexUtils.
     */
    @Test
    public void testExtractAuthors() {
        System.out.println("extractAuthors");
        Set<NewsAuthor> result = FlexUtils.getInstance().extractAuthors("zua, tu, eu");
        assertTrue(result.contains(new NewsAuthor("zua")));
        assertTrue(result.contains(new NewsAuthor("tu")));
        assertTrue(result.contains(new NewsAuthor("eu")));
    }
    
}
