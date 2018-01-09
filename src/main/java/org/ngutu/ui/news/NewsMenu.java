/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import org.ngutu.ui.common.AbstractMenu;
import com.vaadin.ui.UI;

/**
 *
 * @author zua
 */
public class NewsMenu extends AbstractMenu {

    private static final long serialVersionUID = 8366211712669711650L;

    public NewsMenu() {
        super();
    }

    @Override
    public MasterDetailView getBody() {
        return (MasterDetailView) ((NewsView) UI.getCurrent().getContent()).getBody();
    }

    @Override
    protected MenuActions createMenuActions() {
        MenuActions menuActions = new NewsMenuActions();
        return menuActions;
    }

}
