/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package view.body;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Grid;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.HorizontalLayout;
import components.FlexButton;
import data.DataProviderType;
import db.FlexUser;
import components.FlexPanel;
import db.NewsArticle;
import factory.FlexViewFactory;
import java.util.Collection;
import utils.ServiceLocator;
import view.menu.CanPopulate;

/**
 *
 * @author zua
 */
public class BooksBody extends FlexPanel implements CanPopulate {

    private static final long serialVersionUID = 6273025631274336910L;

    private final FlexUser user;
    private Grid<NewsArticle> grid;
    private VerticalLayout layout;
    private HorizontalLayout actions;

    public BooksBody(FlexUser user) {
        this.user = user;
        initGrid();
        initActions();
        layout = new VerticalLayout(grid, actions);
        layout.setSizeFull();
        layout.setExpandRatio(grid, 1f);
        super.addStyleName("flex-body");
        super.setSizeFull();
        super.setContent(layout);
    }

    private void initGrid() {
        Collection<NewsArticle> articleList = ServiceLocator.getInstance().findArticlesService().findAll(0, 100);
        
        grid = new Grid<>("The Articles Grid", articleList);
        grid.addComponentColumn(b -> FlexViewFactory.getInstance().createArticleView(user, b)).setCaption("ISBN");

        if(isAdmin(user) || isDeveloper(user) || isOwner(user)) {
            grid.getEditor().setEnabled(true);
        }
        grid.setSizeFull();
        
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