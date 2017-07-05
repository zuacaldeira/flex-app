/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import com.vaadin.icons.VaadinIcons;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.HorizontalLayout;
import com.vaadin.ui.MenuBar;
import com.vaadin.ui.MenuBar.MenuItem;
import com.vaadin.ui.Window;
import com.vaadin.ui.themes.ValoTheme;
import db.histories.FlexEvent;
import db.histories.FlexNote;
import db.news.FlexUser;
import db.news.GraphEntity;
import db.news.NewsArticle;
import java.util.TreeSet;
import utils.FlexUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    private ActorSystem as = ActorSystem.create();
    private Thread runner;
    
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
    private MenuItem notes;
    private MenuItem civilizations;
    private MenuItem languages;
    private MenuItem dna;
    private MenuItem humanRights;
    
    // Logout button 
    private LogoutButton logoutButton;
    private FlexUser user;
    
    public FlexMenu(FlexUser user) {
        this.user = user;
        super.setSizeFull();
        super.setMargin(false);
        super.setSpacing(false);
        super.setStyleName("flex-menu");
        initMenuBar();
        logoutButton = new LogoutButton();
        logoutButton.addUsername(getUsername());
        super.addComponents(menuBar, logoutButton);
        super.setComponentAlignment(menuBar, Alignment.MIDDLE_RIGHT);
        super.setComponentAlignment(logoutButton, Alignment.MIDDLE_LEFT);
    }
    
    
    
    private void initMenuBar() {
        menuBar = new MenuBar();
        menuBar.setWidthUndefined();
        menuBar.setHeightUndefined();
        menuBar.setAutoOpen(true);
        menuBar.setStyleName(ValoTheme.MENUBAR_BORDERLESS);
        initCommand();
        initMenuNews();
        initMenuHistory();
        initMenuTimelines();
        //command.menuSelected(latest);
    }
    
    private void initCommand() {
        command = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                Iterable<NewsArticle> nodes = null;
                if(selectedItem.getParent() == categories) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(selectedItem.getText(), 100);
                }
                else if(selectedItem.getParent() == publishers) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(selectedItem.getText());
                }
                else if(selectedItem.getText().equals("Latest")) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesLatest();
                }
                else if(selectedItem.getText().equals("Oldest")) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesOldest();
                }
                else if(selectedItem.getText().equals("Read")) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesRead(getUsername());                        
                }
                else if(selectedItem.getText().equals("Favorite")) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesFavorite(getUsername());                        
                }
                else if(selectedItem.getText().equals("Fake")) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesFake(getUsername());                        
                }

                initAndUpdateBody(nodes);
            }
        };
    }
    
    private <T extends GraphEntity> void initAndUpdateBody(Iterable<T> nodes) {
        FlexBody body = new FlexBody(user);
        FlexUtils.getInstance().getMainView(FlexMenu.this).replaceBody(body);
        new Thread(() -> {
            final UpdateBodyMessage message = new UpdateBodyMessage<T>(body, nodes);
            ActorRef ref = as.actorOf(MVCActor.props());
            ref.tell(message, null);
        }).start();
    }
    
    private void initMenuNews() {
        news = menuBar.addItem("News", null);
        updateNewsCategory();
        updateNewsPublisher();
        news.addSeparator();
        updateNewsByTime();
        news.addSeparator();
        updateNewsByStatus();
        news.addSeparator();
        updateNewsSettings();
    }
        
    private void initMenuHistory() {
        history = menuBar.addItem("History", null);
        
        notes = history.addItem("New Event", null);
        notes.addItem("All", new ShowUserEventsCommand());
        notes.addSeparator();
        notes.addItem("New Event", VaadinIcons.PLUS, new NewEventCommand());
        history.addSeparator();
        
        dna = history.addItem("DNA History", null);
        dna.addItem("Paternal Haplotypes", null);
        dna.addItem("Maternal Haplotypes", null);
        dna.addSeparator();
        dna.addItem("New Haplotype", VaadinIcons.PLUS, null);
        
        civilizations = history.addItem("Human History", null);
        civilizations.addItem("New Topic", VaadinIcons.PLUS, command);
        civilizations.addSeparator();
        civilizations.addItem("References", null);
        
        languages = history.addItem("Languages", null);
        languages.addItem("New Alphabet", VaadinIcons.PLUS, command);
        languages.addItem("New Language", VaadinIcons.PLUS, command);
        
        humanRights = history.addItem("Human Rights and Conflicts", null);
        humanRights.addItem("Universal Declaration", command);
        humanRights.addItem("Reports and Statistics", command);
        humanRights.addSeparator();
        humanRights.addItem("Report Violations", VaadinIcons.ALARM, command);
    }
    
    private void initMenuTimelines() {
        timelines = menuBar.addItem("Timelines", null);
    }

    private void updateNewsCategory() {
        categories = news.addItem("Categories", null, command);
        categories.addItem("general", command);
        categories.addItem("sport", command);
        categories.addItem("politics", command);
        categories.addItem("entertainment", command);
        categories.addItem("technology", command);
        categories.addItem("business", command);
        categories.addItem("gaming", command);
        categories.addItem("music", command);
        categories.addItem("science-and-nature", command);
    }

    private void updateNewsPublisher() {
        publishers = news.addItem("Publishers", null, command);
        String[] pubs = 
        {"Die Zeit",                
        "Engadget",
        "Entertainment Weekly",
        "ESPN",
        "ESPN Cric Info",
        "Financial Times",
        "Focus",
        "Football Italia",
        "Fortune",
        "FourFourTwo",
        "Fox Sports",
        "Google News",
        "Gruenderszene",
        "Hacker News",
        "Handelsblatt",
        "IGN",
        "Independent",
        "Mashable",
        "Metro",
        "Mirror",
        "MTV News",
        "MTV News (UK)",
        "National Geographic",
        "New Scientist",
        "Newsweek",
        "New York Magazine",
        "NFL News",
        "Polygon",
        "Recode",
        "Reddit /r/all",
        "Reuters",
        "Spiegel Online",
        "T3n",
        "TalkSport",
        "TechCrunch",
        "TechRadar",
        "The Economist",
        "The Guardian (AU)",
        "The Guardian (UK)",
        "The Hindu",
        "The Huffington Post",
        "The Lad Bible",
        "The New York Times",
        "The Next Web",
        "The Sport Bible",
        "The Times of India",
        "The Verge",
        "The Wall Street Journal",
        "The Washington Post",
        "Time",
        "USA Today",
        "Wired.de",
        "ABC News (AU)",
        "Al Jazeera English",
        "Ars Technica",
        "Wirtschafts Woche",
        "Associated Press",
        "BBC News",
        "BBC Sport",
        "Bloomberg",
        "Breitbart News",
        "Business Insider",
        "Business Insider (UK)",
        "Buzzfeed",
        "CNBC",
        "CNN",
        "Daily Mail",
        "Der Tagesspiegel",
        "The Telegraph"
        };
        
        TreeSet<String> set = new TreeSet<>();
        for(String p: pubs) {
            set.add(p);
        }
        for(String s: set) {
            publishers.addItem(s, command);
        }
    }
    private void updateNewsByTime() {
        latest = news.addItem("Latest", VaadinIcons.ARROW_CIRCLE_DOWN, command);
        oldest = news.addItem("Oldest", VaadinIcons.ARROW_CIRCLE_UP, command);
    }
    private void updateNewsByStatus() {
        news.addItem("Read", VaadinIcons.EYE_SLASH, command);
        news.addItem("Favorite", VaadinIcons.STAR, command);
        news.addItem("Fake", VaadinIcons.EXCLAMATION_CIRCLE, command);
    }

    private void updateNewsSettings() {
        settings = news.addItem("Settings", VaadinIcons.COG, command);
    }
    
    private String getUsername() {
        return user.getUsername();
    }
    
    public SecuredUI getUI() {
        return (SecuredUI) super.getUI();
    }

    private class ShowUserEventsCommand implements MenuBar.Command {

        public ShowUserEventsCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            initAndUpdateBody(ServiceLocator.getInstance().findEventService().findAll());
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


