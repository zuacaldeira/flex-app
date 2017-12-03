/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.UI;
import components.FlexButton;
import data.DataProviderType;
import db.FlexUser;
import components.FlexPanel;
import db.NewsArticle;
import components.CanPopulate;

/**
 *
 * @author zua
 */
public class BooksBody extends FlexPanel implements CanPopulate {

    private static final long serialVersionUID = 6273025631274336910L;

    private FlexUser user;
    private Grid<NewsArticle> grid;
    private VerticalLayout layout;
    private HorizontalLayout actions;

    public BooksBody() {
        initUser();
        initGrid();
        initActions();
        layout = new VerticalLayout(grid, actions);
        layout.setSizeFull();
        layout.setExpandRatio(grid, 1f);
        super.addStyleName("flex-body");
        super.setSizeFull();
        super.setContent(layout);
    }

    private void initUser() {
        if (UI.getCurrent() != null) {
            System.out.println("Found USER -> " + UI.getCurrent().getSession().getAttribute("user"));
            user = (FlexUser) UI.getCurrent().getSession().getAttribute("user");
            System.out.println("NEWS VIEW USER -> " + user);
        }
    }

    private void initGrid() {
        grid = new Grid<>();
    }
    
    private void initActions() {
        FlexButton deleteAll = new FlexButton("Delete", VaadinIcons.TRASH);
        FlexButton updateAll = new FlexButton("Update", VaadinIcons.REFRESH);
        FlexButton addAllToCart = new FlexButton("Add to Cart", VaadinIcons.CART);
        actions = new HorizontalLayout(deleteAll, updateAll, addAllToCart);
    }

    public FlexUser getUser() {
        return user;
    }

    @Override
    public void populate() {
        this.populate(DataProviderType.LATEST, "latest");
    }

    public void populate(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        //new FlexBodyWorker(user, masterDetailView, type, value).start();
        System.out.println("FlexBodyThread#run(): DONE");
    }

    private boolean isOwner(FlexUser user) {
        return true;
    }

    private boolean isDeveloper(FlexUser user) {
        return false;
    }

    private boolean isAdmin(FlexUser user) {
        return false;
    }
}
