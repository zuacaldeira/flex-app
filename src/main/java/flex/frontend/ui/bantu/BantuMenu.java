/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import flex.frontend.ui.FlexButton;
import flex.frontend.ui.FlexMenu;

/**
 *
 * @author zua
 */
public class BantuMenu extends FlexMenu {

    private FlexButton homeButton;
    private FlexButton videosButton;
    private FlexButton documentsButton;
    private FlexButton booksButton;
    private FlexButton nationsButton;
    private FlexButton tribesButton;
    private FlexButton religionsButton;
    private FlexButton timelineButton;

    public BantuMenu() {
    }

    @Override
    protected void addActions() {
        homeButton();
        videosButton();
        documentsButton();
        booksButton();
        nationsButton();
        tribesButton();
        religionsButton();
        timelineButton();
    }

    private void homeButton() {
        homeButton = new HomeButton();
        addComponent(homeButton);
    }

    private void videosButton() {
        videosButton = new FlexButton("Videos", VaadinIcons.YOUTUBE);
        videosButton.addClickListener(event -> {
            Page.getCurrent().setLocation("bantus/videos");
        });
        addComponent(videosButton);
    }

    private void documentsButton() {
        documentsButton = new FlexButton("Documents", VaadinIcons.PAPERCLIP);
        documentsButton.addClickListener(event -> {
            Page.getCurrent().setLocation("bantus/documents");
        });
        addComponent(documentsButton);
    }
    
    private void booksButton() {
        booksButton = new FlexButton("Books", VaadinIcons.BOOK);
        booksButton.addClickListener(event -> {
            Page.getCurrent().setLocation("bantus/books");
        });
        addComponent(booksButton);
    }
    
    private void nationsButton() {
        nationsButton = new FlexButton("Nations", VaadinIcons.MAP_MARKER);
        nationsButton.addClickListener(event -> {
            Page.getCurrent().setLocation("bantus/nations");
        });
        addComponent(nationsButton);
    }
    
    private void tribesButton() {
        tribesButton = new FlexButton("Tribes", VaadinIcons.SHIELD);
        tribesButton.addClickListener(event -> {
            Page.getCurrent().setLocation("bantus/tribes");
        });
        addComponent(tribesButton);
    }

    private void religionsButton() {
        religionsButton = new FlexButton("Religions", VaadinIcons.SHIELD);
        religionsButton.addClickListener(event -> {
            Page.getCurrent().setLocation("bantus/religions");
        });
        addComponent(religionsButton);
    }

    private void timelineButton() {
        timelineButton = new FlexButton("Timeline", VaadinIcons.CALENDAR_CLOCK);
        timelineButton.addClickListener(event -> {
            Page.getCurrent().setLocation("bantus/timeline");
        });
        addComponent(timelineButton);
    }
    
}
