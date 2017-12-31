/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package utils;

import static org.testng.Assert.assertNotNull;
import org.testng.annotations.Test;


/**
 *
 * @author zua
 */
public class ServiceNotFoundExceptionTest {
    
    public ServiceNotFoundExceptionTest() {
    }

    @Test
    public void testNew() {
        assertNotNull(new ServiceNotFoundException());
    }
    
}
