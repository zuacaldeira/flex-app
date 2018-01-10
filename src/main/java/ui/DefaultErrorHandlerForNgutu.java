/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.server.DefaultErrorHandler;
import com.vaadin.server.ErrorEvent;

/**
 *
 * @author zua
 */
public class DefaultErrorHandlerForNgutu extends DefaultErrorHandler {

    private static final long serialVersionUID = -7577945535466240884L;

    public DefaultErrorHandlerForNgutu() {
    }

    @Override
    public void error(ErrorEvent event) {
        String cause = "<b>The click failed because:</b><br/>";
        for (Throwable t = event.getThrowable(); t != null; t = t.getCause()) {
            if (t.getCause() == null) {
                cause += t.getClass().getName() + "<br/>";
            }
        }
        System.out.println("Error: " + cause);
        doDefault(event);
    }

}
