/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.auth0.client.auth.AuthAPI;

/**
 *
 * @author zua
 */
public class NgutuAuthAPI extends AuthAPI {
    private static final String DOMAIN = "ngutu.eu.auth0.com";
    private static final String CLIENT_ID = "K8hEG_ew0eF4fv9tRDY1RZ72RjPK-n_Q";
    private static final String CLIENT_SECRET = "oAka59gWaZ0rgnmq61geaMEpcB-RPAANal9M6seQSqeidnHWQK5JIDXeApJ0OJZ5";
    
    public NgutuAuthAPI() {
        super(DOMAIN, CLIENT_ID, CLIENT_SECRET);
    }
}
