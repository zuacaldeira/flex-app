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
import com.vaadin.ui.Notification;
import com.vaadin.ui.themes.ValoTheme;
import db.news.NewsArticle;
import java.util.TreeSet;
import utils.FlexUtils;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexMenu extends HorizontalLayout {

    private Thread runner;
    private MenuBar menuBar;
    private MenuItem news;
    private MenuItem history;
    private MenuItem timelines;
    private MenuItem categories;
    private MenuItem publishers;
    private MenuItem authors;
    private MenuBar.Command command;
    private MenuItem timely;
    private MenuItem settings;
    private MenuItem status;
    private MenuItem notes;
    private MenuItem civilizations;
    private MenuItem languages;
    private MenuItem dna;
    private MenuItem humanRights;
    
    
    public FlexMenu() {
        super.setSizeFull();
        super.setMargin(false);
        super.setSpacing(false);
        super.setStyleName("flex-menu");
        super.setDefaultComponentAlignment(Alignment.MIDDLE_CENTER);
        menuBar = new MenuBar();
        menuBar.setSizeFull();
        menuBar.setAutoOpen(true);
        menuBar.setStyleName(ValoTheme.MENUBAR_BORDERLESS+ " " + ValoTheme.MENUBAR_SMALL);
        initMenuBar();
        super.addComponent(menuBar);
    }
    
    
    
    private void initMenuBar() {
        initCommand();
        initMenuNews();
        initMenuHistory();
        initMenuTimelines();
    }
    
    private void initCommand() {
        command = new MenuBar.Command() {
            @Override
            public void menuSelected(MenuItem selectedItem) {
                FlexBody body = new FlexBody();
                Iterable<NewsArticle> nodes = null;
                if(selectedItem.getParent() == categories) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesWithCategory(selectedItem.getText(), 100);
                }
                else if(selectedItem.getParent() == publishers) {
                    nodes = ServiceLocator.getInstance().findArticlesService().findArticlesWithSource(selectedItem.getText());
                }
                else if(selectedItem.getParent() == timely) {
                    if(selectedItem.getText().equals("Latest")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesLatest();
                    }
                    else if(selectedItem.getText().equals("Oldest")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesOldest();
                    }
                    else if(selectedItem.getText().equals("Today")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesToday();                        
                    }
                    else if(selectedItem.getText().equals("This Week")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesThisWeek();                        
                    }
                    else if(selectedItem.getText().equals("This Month")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesThisMonth();                        
                    }
                }
                else if(selectedItem.getParent() == status) {
                    if(selectedItem.getText().equals("Read")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesRead(getUsername());                        
                    }
                    else if(selectedItem.getText().equals("Unread")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesUnread(getUsername());                        
                    }
                    else if(selectedItem.getText().equals("Favorite")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesFavorite(getUsername());                        
                    }
                    else if(selectedItem.getText().equals("Fake")) {
                        nodes = ServiceLocator.getInstance().findArticlesService().findArticlesFake(getUsername());                        
                    }
                }

                FlexUtils.getInstance().getMainView(FlexMenu.this).replaceBody(body);

                final UpdateBodyMessage message = new UpdateBodyMessage(body, nodes);
                
                if(FlexMenu.this.runner != null && FlexMenu.this.runner.isAlive()) {
                    FlexMenu.this.runner.interrupt();
                }
                
                FlexMenu.this.runner = new Thread(() -> {
                    ActorSystem as = ActorSystem.create();
                    ActorRef ref = as.actorOf(MVCActor.props());
                    ref.tell(message, null);
                });
                runner.start();
            }
        };
    }
    
    private void initMenuNews() {
        news = menuBar.addItem("News", null);
        updateNewsCategory();
        updateNewsPublisher();
        news.addSeparator();
        updateNewsByTime();
        updateNewsByStatus();
        news.addSeparator();
        updateNewsSettings();
    }
        
    private void initMenuHistory() {
        history = menuBar.addItem("History", null);
        
        notes = history.addItem("My Notes", null);
        notes.addItem("Today", command);
        notes.addItem("All", command);
        notes.addSeparator();
        notes.addItem("New Note", VaadinIcons.PLUS, new NewNoteCommand());
        history.addSeparator();
        
        dna = history.addItem("DNA Flow", null);
        dna.addItem("Paternal Haplotypes", null);
        dna.addItem("Maternal Haplotypes", null);
        dna.addSeparator();
        dna.addItem("New Haplotype", VaadinIcons.PLUS, null);
        
        civilizations = history.addItem("Civilizational Flow", null);
        civilizations.addItem("New Civilization", VaadinIcons.PLUS, command);
        civilizations.addSeparator();
        civilizations.addItem("References", null);
        
        languages = history.addItem("Linguistic Flow", null);
        languages.addItem("New Alphabet", VaadinIcons.PLUS, command);
        languages.addItem("New Language", VaadinIcons.PLUS, command);
        languages.addSeparator();
        languages.addItem("References", null);
        
        humanRights = history.addItem("Human Rights", null);
        humanRights.addItem("Universal Declaration", command);
        humanRights.addItem("Reports and Statistics", command);
        humanRights.addSeparator();
        humanRights.addItem("New Violations", VaadinIcons.PLUS, command);
        humanRights.addSeparator();
        humanRights.addItem("References", null);

        history.addSeparator();
        history.addItem("All References", null);
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
        timely = news.addItem("When", null, command);
        timely.addItem("Latest", command);
        timely.addItem("Oldest", command);
        /*timely.addSeparator();
        timely.addItem("Today", command);
        timely.addItem("This Week", command);
        timely.addItem("This month", command);*/
    }
    private void updateNewsByStatus() {
        status = news.addItem("Status", null, command);
        status.addItem("Read", command);
        status.addItem("Favorite", command);
        status.addItem("Fake", command);
    }

    private void updateNewsSettings() {
        settings = news.addItem("Settings", VaadinIcons.COG, command);
    }
    
    private String getUsername() {
        return (String) getSession().getAttribute("username");
    }

    private static class NewNoteCommand implements MenuBar.Command {
        public NewNoteCommand() {
        }

        @Override
        public void menuSelected(MenuItem selectedItem) {
            Notification.show("Selected menu Item " + selectedItem.getText());
            //Window w = new Window("Create new note", new FlexNoteForm(new FlexNote()));
        }
    }
}
