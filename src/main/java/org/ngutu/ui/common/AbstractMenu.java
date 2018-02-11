/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.common;

import com.vaadin.shared.ui.MarginInfo;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.Image;
import com.vaadin.ui.TextField;
import com.vaadin.ui.UI;
import db.auth.FlexUser;
import org.ngutu.ui.logo.FlexLogo;
import org.ngutu.ui.news.MenuActions;

/**
 *
 * @author zua
 */
public abstract class AbstractMenu extends HorizontalLayout {

    private static final long serialVersionUID = 1394190815709078040L;

    private FlexLogo logo;
    private TextField searchBox;

    private HorizontalLayout actions;
    private Image picture;

    public static final String MENU_HEIGHT = "64px";

    public AbstractMenu() {
        initLogo();
        initActions();
        super.addComponents(logo, actions);
        super.setComponentAlignment(logo, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(actions, Alignment.MIDDLE_RIGHT);
        super.setWidth("100%");
        super.setExpandRatio(actions, 1f);
        super.setHeight(MENU_HEIGHT);
        super.setSpacing(true);
        super.setMargin(new MarginInfo(false, true));
        super.setStyleName("flex-menu");
    }
    
    protected abstract MenuActions createMenuActions();

    public FlexLogo getLogo() {
        return logo;
    }

    private void initLogo() {
        logo = new FlexLogo();
    }

    private FlexUser getUser() {
        if (UI.getCurrent() != null) {
            return (FlexUser) UI.getCurrent().getSession().getAttribute("user");
        }
        return null;
    }

    private void initActions() {
        actions = createMenuActions();
    }

    public abstract AbstractBody getBody();

    public final TextField getSearchBox() {
        return searchBox;
    }

    public HorizontalLayout getActions() {
        return actions;
    }

    public Image getPicture() {
        return picture;
    }

}
