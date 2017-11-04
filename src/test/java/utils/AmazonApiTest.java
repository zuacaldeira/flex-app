/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import com.ECS.client.jax.Items;
import de.malkusch.amazon.ecs.exception.RequestException;
import java.io.IOException;
import org.junit.Test;
import static org.junit.Assert.*;

/**
 *
 * @author zua
 */
public class AmazonApiTest {
    
    public AmazonApiTest() {
    }

    @Test
    public void testConstructor() throws IOException {
        System.out.println("new()");
        AmazonApi instance = new AmazonApi();
        assertNotNull(instance.getConfiguration().getAccessKey());
        assertNotNull(instance.getConfiguration().getSecretKey());
        assertNotNull(instance.getConfiguration().getAssociateTag());
        System.out.println(instance.getConfiguration().getAccessKey());
    }
    /**
     * Test of makeApiCall method, of class AmazonApi.
     */
    @Test
    public void testMakeApiCall() throws IOException, RequestException {
        System.out.println("makeApiCall");
        AmazonApi instance = new AmazonApi();
        Items items = instance.makeApiCall("Books", "Java");
        assertTrue(items.getTotalResults().intValue() > 0);
        items.getItem().forEach(it -> {
            System.out.println(it.getDetailPageURL());
        });
        System.out.println();
    }
    
}
