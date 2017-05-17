/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.backend.news.db;

import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class GraphEntityTest {
    
    public GraphEntityTest() {
    }

    /**
     * Test of getId method, of class GraphEntity.
     */
    @Test
    public void testGetId() {
        System.out.println("getId");
        GraphEntity instance = new GraphEntityImpl();
        instance.setId(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, instance.getId().longValue());
    }

    /**
     * Test of setId method, of class GraphEntity.
     */
    @Test
    public void testSetId() {
        System.out.println("setId");
        GraphEntity instance = new GraphEntityImpl();
        instance.setId(Long.MIN_VALUE);
        assertEquals(Long.MIN_VALUE, instance.getId().longValue());
    }

    public class GraphEntityImpl extends GraphEntity {

        @Override
        public String getPropertyName() {
            return "name";
        }

        @Override
        public String getPropertyValue() {
            return "value";
        }
    }
    
}
