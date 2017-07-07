package ui.histories.videos;

import ui.news.article.*;
import ui.GraphEntityView;
import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.*;
import com.vaadin.ui.themes.ValoTheme;
import db.histories.FlexEvent;
import db.news.FlexUser;
import services.histories.FlexEventService;
import ui.FlexBody;
import utils.ServiceLocator;


/**
 * Created by zua on 13/04/17.
 */
public class EventView extends GraphEntityView<FlexEvent>  {
    // Info components
    private Label title;
    private Label owner;
    private Label details;
    private Label when;
    private Label where;
    private GoogleMap map;
    
    private AbstractOrderedLayout authors;

    public EventView(FlexUser user, FlexEvent event) {
        super(user, event);
    }
    
    
    @Override
    public AbstractOrderedLayout createInfoHeader() {
        initOwner();
        initWhen();
        initTitle();
        VerticalLayout view = new VerticalLayout(owner, when, title);
        view.setSpacing(false);
        view.setMargin(false);
        return view;
    }

    @Override
    public AbstractOrderedLayout createInfoBody() {
        initWhere();
        initMap();
        initDetails();
        VerticalLayout view = new VerticalLayout(where, map, details);   
        view.setSpacing(false);
        view.setMargin(false);
        return view;
    }
    
    public Label getTitle() {
        return title;
    }

    public AbstractOrderedLayout getAuthors() {
        return authors;
    }

    public Label getContent() {
        return details;
    }

    private void initTitle() {
        this.title = new Label(getItem().getTitle());
        this.title.setStyleName("title");
        this.title.setSizeFull();
    }

    private void initDetails() {
        this.details = new Label(getItem().getDetails());
        this.details.setStyleName("content");
        this.details.setSizeFull();
    }

    private void initMap() {
        map = new GoogleMap(null, null, null);
        map.setWidth("100%");
        map.setHeight("100px");
        map.setCenter(new LatLon(getItem().getLatitude(), getItem().getLongitude()));
    }

    private void initOwner() {
        owner = new Label(getUser().getUsername());
        owner.setStyleName(ValoTheme.LABEL_LIGHT + " " + "source");
    }

    private void initWhen() {
        when = new DateLabel(getItem().getWhen());
        when.setSizeUndefined();
    }
    
    private void initWhere() {
        where = new Label(getItem().getWhere());
        where.setSizeUndefined();
    }
    

    @Override
    public FlexEvent getItem() {
        return (FlexEvent) super.getItem();
    }

    
    private FlexBody getFlexBody() {
        return getFlexBody(this);
    }

    private FlexBody getFlexBody(Component component) {
        if(component == null) {
            throw new IllegalArgumentException("Component cannot be null");
        }
        else if(component instanceof FlexBody) {
            return (FlexBody) component;
        }
        else {
            return getFlexBody(component.getParent());
        }
    }

    @Override
    public FlexEventService getService() {
        return ServiceLocator.getInstance().findEventService();
    }

    @Override
    public void maximize() {
        super.maximize();
    }

    @Override
    public void minimize() {
        super.minimize();
    }

    
    
}
