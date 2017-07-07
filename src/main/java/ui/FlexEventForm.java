/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.ui.Alignment;
import com.vaadin.ui.DateField;
import com.vaadin.ui.FormLayout;
import com.vaadin.ui.Notification;
import com.vaadin.ui.TextArea;
import com.vaadin.ui.VerticalLayout;
import com.vaadin.ui.Window;
import db.histories.FlexEvent;
import db.news.FlexUser;
import java.time.LocalDate;
import java.util.Calendar;
import org.vaadin.addons.locationtextfield.GeocodedLocation;
import org.vaadin.addons.locationtextfield.LocationTextField;
import org.vaadin.addons.locationtextfield.OpenStreetMapGeocoder;
import utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class FlexEventForm extends VerticalLayout {

    private final FlexEvent event;
    private final FlexUser user;
    private FormLayout formLayout;
    private FlexTextField title;
    private LocationTextField<GeocodedLocation> where;
    private DateField when;
    private TextArea details;
    private SaveButton saveButton;
    private GoogleMap smallMap;

    public FlexEventForm(FlexUser user, FlexEvent event) {
        this.user = user;
        this.event = event;
        event.setOwner(user);
        initForm();
        addComponent(formLayout);
        setSpacing(true);
        setMargin(true);
        setHeightUndefined();
    }

    private void initTitle() {
        title = new FlexTextField("Title", null);
        title.setSizeFull();
        title.addValueChangeListener(e -> {
            event.setTitle(title.getValue());
        });
    }
    
    private void initWhere() {
        where = LocationTextField.newBuilder()
            .withCaption("Location:")
            .withDelayMillis(1200)
            .withLocationProvider(OpenStreetMapGeocoder.getInstance())
            .withMinimumQueryCharacters(3)
            .withWidth("100%")
            .withHeight("40px")
            .build();
        where.addLocationValueChangeListener(e -> {
            GeocodedLocation location = e.getValue();
            event.setWhere(location.getGeocodedAddress());
            event.setLatitude(location.getLat());
            event.setLongitude(location.getLon());
            LatLon latLon = new LatLon(location.getLat(), location.getLon());
            if(smallMap != null  && latLon != null) {
                Notification.show("Map re-centered to " + latLon.getLat() + ", " + latLon.getLon());
                smallMap.setCenter(latLon);
            }
        });
    }
    
    private void initWhen() {
        when = new DateField("When");
        when.setSizeFull();
        when.addValueChangeListener(e -> {
            LocalDate localDate = e.getValue();
            Calendar calendar = Calendar.getInstance();
            calendar.set(Calendar.ERA, localDate.getEra().getValue());
            calendar.set(Calendar.YEAR, localDate.getYear());
            calendar.set(Calendar.MONTH, localDate.getMonthValue());
            calendar.set(Calendar.DAY_OF_MONTH, localDate.getDayOfMonth());            
            event.setWhen(calendar.getTime());
        });
    }
    
    private void initMap() {
        smallMap = new GoogleMap(null, null, null);
        smallMap.setWidth("100%");
        smallMap.setHeight("100px");
    }
    
    private void initDetails() {
        details = new TextArea();
        details.setSizeFull();
    }
    
    private void initSaveButton() {
        saveButton = new SaveButton();
        saveButton.addClickListener(e -> {
            ServiceLocator.getInstance().findEventService().save(event);
            ((Window)getParent()).close();
        });
    }
    
    private void initForm() {
        initTitle();
        initWhen();
        initMap();
        initWhere();
        initDetails();
        initSaveButton();
        formLayout = new FormLayout();
        formLayout.addComponents(title, when, where, smallMap, details, saveButton);
        formLayout.setComponentAlignment(saveButton, Alignment.MIDDLE_RIGHT);
        formLayout.setSpacing(true);
        formLayout.setMargin(true);
    }
   
}
