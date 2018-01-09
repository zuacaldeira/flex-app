/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import org.ngutu.ui.common.AbstractView;
import com.vaadin.navigator.ViewChangeListener;
import com.vaadin.ui.UI;
import data.DataProviderType;
import db.auth.FlexUser;
import org.ngutu.ui.common.AbstractBody;

/**
 *
 * @author zua
 */
public class NewsView extends AbstractView {

    private static final long serialVersionUID = 8467619842785075810L;

    public NewsView() {
    }

    public void replaceBody(AbstractBody flexBody) {
        replaceComponent(getBody(), flexBody);
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    @Override
    protected NewsMenu createMenu() {
        return new NewsMenu();
    }

    @Override
    protected EmbeddedMasterDetailView createBody() {
        return new EmbeddedMasterDetailView();
    }

    @Override
    public AbstractBody getBody() {
        return (AbstractBody) super.getBody();
    }

    @Override
    public NewsMenu getMenu() {
        return (NewsMenu) super.getMenu();
    }

    @Override
    public void enter(ViewChangeListener.ViewChangeEvent event) {
        EmbeddedMasterDetailView newBody = createBody();
        replaceBody(newBody);

        Thread thread = null;
        String parameters = event.getParameters();
        if (parameters == null || parameters.isEmpty()) {
            thread = new NewsBodyWorker(newBody, getUser(), DataProviderType.LATEST, null);
        } else if (!parameters.isEmpty()) {
            parameters = parameters.replace('-', ' ');
            String parts[] = parameters.split("/");
            String context = null;
            String target = null;
            if (parts.length == 2) {
                System.out.println("2-PARTS " + parts[0] + " - " + parts[1]);
                context = parts[0];
                target = parts[1];
                thread = new NewsBodyWorker(newBody, getUser(), DataProviderType.valueOf(context.toUpperCase()), target);
            } else if (parts.length == 1) {
                System.out.println("1-PART " + parts[0]);
                context = parts[0];
                thread = new NewsBodyWorker(newBody, getUser(), DataProviderType.valueOf(context.toUpperCase()), null);
            }
        }

        if (thread != null) {
            thread.start();
        }
    }

}
