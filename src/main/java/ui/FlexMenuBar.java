/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.icons.VaadinIcons;
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
public class FlexMenuBar extends MenuBar {

    // Main Menu (top level)
    private MenuItem news;
    private MenuItem categories;
    private MenuItem publishers;
    private MenuItem status;

    private MenuBar.Command command;
    private final FlexUser user;
    
    public FlexMenuBar(FlexUser user) {
        this.user = user;
        initMenuBar();
    }
    
    private void initMenuBar() {
        setSizeUndefined();
        setAutoOpen(true);
        setStyleName("flex-menu-bar");
        addStyleName(ValoTheme.MENUBAR_BORDERLESS);
        initCommand();
        initMenuNews();
    }
    
    private void initCommand() {
        command = (MenuItem selectedItem) -> { 
            selectedItem.setStyleName("selected");
            updateBody(selectedItem);
        };
    }
    
    private void initMenuNews() {
        news = addItem("News", null);

        categories = addItem("Categories", null, null);
        updateNewsCategory();

        publishers = addItem("Publishers", null, null);
        updateNewsPublisher();

        status = addItem("Status", null, null);
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
        news.addItem("Latest", VaadinIcons.ARROW_CIRCLE_DOWN, command);
        news.addItem("Oldest", VaadinIcons.ARROW_CIRCLE_UP, command);
    }
    
    private void updateNewsByStatus() {
        status.addItem("Read", VaadinIcons.EYE_SLASH, command);
        status.addItem("Favorite", VaadinIcons.STAR, command);
        status.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, command);
    }

    private void updateNewsSettings() {
        news.addItem("Settings", VaadinIcons.COG, command);
    }
    
    private String getUsername() {
        return user.getUsername();
    }
    
    @Override
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }

    private void updateBody(MenuItem selectedItem) {
        FlexUtils.getInstance().getBody(this).updateData(getDataProviderType(selectedItem), selectedItem.getText());
    }
    
    public DataProviderType getDataProviderType(MenuItem selectedItem) {
        if(selectedItem.getParent() != null && selectedItem.getParent().getText().equals("Categories")) {
            return DataProviderType.CATEGORY;
        }
        if(selectedItem.getParent() != null && selectedItem.getParent().getText().equals("Publishers")) {
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
            updateBody(selectedItem);
            if (previous != null) {
                previous.setStyleName(null);
            }
            selectedItem.setStyleName("selected");
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


