/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;
import com.vaadin.server.Page;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import db.histories.FlexEvent;
import db.histories.FlexNote;
import db.news.FlexUser;
import utils.FlexUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    // Main Menu (top level)
    private MenuBar menuBar;
    private MenuItem news;
    private MenuItem history;
    private MenuItem timelines;
    private MenuBar.Command command;
    
    // News related menu items
    private MenuItem latest;
    private MenuItem oldest;
    private MenuItem categories;
    private MenuItem publishers;
    private MenuItem settings;
    
    // History related menu items
    private MenuItem topics;
    private MenuItem civilizations;
    private MenuItem languages;
    private MenuItem dna;
    private MenuItem humanRights;

    // Timelines
    private MenuItem events;
    
    // Logout button 
    private final LogoutButton logoutButton;
    private final FlexUser user;
    private MenuItem status;
    
    public FlexMenu(FlexUser user) {
        this.user = user;
        super.setSizeFull();
        super.setMargin(false);
        super.setSpacing(false);
        super.setStyleName("flex-menu");
        initMenuBar();
        logoutButton = new LogoutButton();
        logoutButton.addClickListener(event -> {
            getSession().setAttribute("user", null);
            Page.getCurrent().setLocation("/flex-app");
        });
        logoutButton.addUsername(getUsername());
        super.addComponents(new FlexLogo(), menuBar, logoutButton);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_LEFT);
        super.setComponentAlignment(logoutButton, Alignment.MIDDLE_RIGHT);
    }
    
    private void initMenuBar() {
        menuBar = new MenuBar();
        menuBar.setWidthUndefined();
        menuBar.setHeightUndefined();
        menuBar.setAutoOpen(true);
        menuBar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        initCommand();
        initMenuNews();
    }
    
    private void initCommand() {
        command = (MenuItem selectedItem) -> { updateBodyWithMVCActor(selectedItem); };
    }
    
    private void initMenuNews() {
        news = menuBar.addItem("News", null);

        categories = menuBar.addItem("Categories", null, command);
        updateNewsCategory();

        publishers = menuBar.addItem("Publishers", null, command);
        updateNewsPublisher();

        status = menuBar.addItem("Status", null, command);
        updateNewsByStatus();
        
        updateNewsByTime();
        news.addSeparator();
        updateNewsSettings();
    }
        
    private void updateNewsCategory() {
        Iterable<String> cats = ServiceLocator.getInstance().findSourcesService().findCategories();
        cats.forEach(cat -> {
            if(cat != null) {
                categories.addItem(cat, command);
            }
        });
    }

    private void updateNewsPublisher() {
        Iterable<String> sources = ServiceLocator.getInstance().findSourcesService().findNames();
        sources.forEach(src -> {
            if(src != null) {
                publishers.addItem(src, command);
            }
        });
    }

    private void updateNewsByTime() {
        latest = news.addItem("Latest", VaadinIcons.ARROW_CIRCLE_DOWN, command);
        oldest = news.addItem("Oldest", VaadinIcons.ARROW_CIRCLE_UP, command);
    }
    
    private void updateNewsByStatus() {
        status.addItem("Read", VaadinIcons.EYE_SLASH, command);
        status.addItem("Favorite", VaadinIcons.STAR, command);
        status.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, command);
    }

    private void updateNewsSettings() {
        settings = news.addItem("Settings", VaadinIcons.COG, command);
    }
    
    private String getUsername() {
        return user.getUsername();
    }
    
    @Override
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }

    private void updateBodyWithMVCActor(MenuItem selectedItem) {
        FlexUtils.getInstance().getBody(this).setDataProviderType(getDataProviderType(selectedItem), selectedItem.getText());
    }
    
    public DataProviderType getDataProviderType(MenuItem selectedItem) {
        if(selectedItem.getParent().getText().equals("Categories")) {
            return DataProviderType.CATEGORY;
        }
        if(selectedItem.getParent().getText().equals("Publishers")) {
            return DataProviderType.PUBLISHER;
        }
        else if(selectedItem.getText().equals("Latest")) {
            return DataProviderType.LATEST;
        }
        else if(selectedItem.getText().equals("Oldest")) {
            return DataProviderType.OLDEST;
        }
        else if(selectedItem.getText().equals("Read")) {
            return DataProviderType.READ;
        }
        else if(selectedItem.getText().equals("Favorite")) {
            return DataProviderType.FAVORITE;
        }
        else if(selectedItem.getText().equals("Fake")) {
            return DataProviderType.FAKE;
        }
        return null;
    }
    

    private class ShowUserEventsCommand implements MenuBar.Command {
        
        private MenuItem previous;

        public ShowUserEventsCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            updateBodyWithMVCActor(selectedItem);
            if (previous != null) {
                previous.setStyleName(null);
            }
            selectedItem.setStyleName("on");
            previous = selectedItem;
        }
    }

    private class NewEventCommand implements MenuBar.Command {

        public NewEventCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            FlexEvent event = new FlexEvent();
            event.setOwner(user);
            Window w = new FlexWindow("Create new event", new FlexEventForm(user, event));
            w.setModal(true);
            w.setWidth("50%");
            w.setHeightUndefined();
            getUI().addWindow(w);
        }
    }
    

    private class NewNoteCommand implements MenuBar.Command {
        public NewNoteCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            FlexNote note = new FlexNote();
            note.setOwner(user);
            Window w = new Window("Create new note", new FlexNoteForm(note));
            w.setModal(true);
            w.setWidth("50%");
            w.setHeight("75%");
            getUI().addWindow(w);
        }
    }

}


