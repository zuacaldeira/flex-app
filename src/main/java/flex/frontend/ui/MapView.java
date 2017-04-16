package flex.frontend.ui;

import com.vaadin.tapio.googlemaps.GoogleMap;
import com.vaadin.tapio.googlemaps.client.LatLon;
import com.vaadin.tapio.googlemaps.client.events.*;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapInfoWindow;
import com.vaadin.tapio.googlemaps.client.overlays.GoogleMapMarker;
import com.vaadin.ui.Label;
import com.vaadin.ui.Notification;
import com.vaadin.ui.VerticalLayout;

/**
 * Created by zua on 13/04/17.
 */
public class MapView extends VerticalLayout {

    private GoogleMap googleMap;

    public MapView() {
        setSizeFull();
        createGoogleMap();
        /* A vertical layout to hold the google map component */
        addComponent(googleMap);
        setExpandRatio(googleMap, 1.0f);
    }

    private GoogleMap createGoogleMap() {
        /* Google Map */
        googleMap = new GoogleMap(null
                , null, null);
        // uncomment to enable Chinese API.
        //googleMap.setApiUrl("maps.google.cn");
        googleMap.setCenter(new LatLon(0, 0));
        googleMap.setSizeFull();
        googleMap.setZoom(2);

        /* Configure initial behaviour configuration */
        googleMap.addMarkerClickListener(new MarkerClickListener() {
            @Override
            public void markerClicked(GoogleMapMarker clickedMarker) {

                Label consoleEntry = new Label("Marker \""
                        + clickedMarker.getCaption() + "\" at ("
                        + clickedMarker.getPosition().getLat() + ", "
                        + clickedMarker.getPosition().getLon() + ") clicked.");
            }
        });

        googleMap.addMapMoveListener(new MapMoveListener() {
            @Override
            public void mapMoved(int zoomLevel, LatLon center, LatLon boundsNE,
                                 LatLon boundsSW) {
                String message = "Map moved to ("
                        + center.getLat() + ", " + center.getLon() + "), zoom "
                        + zoomLevel + ", boundsNE: (" + boundsNE.getLat()
                        + ", " + boundsNE.getLon() + "), boundsSW: ("
                        + boundsSW.getLat() + ", " + boundsSW.getLon() + ")";

                Notification.show(message, Notification.Type.TRAY_NOTIFICATION);
            }
        });

        googleMap.addMapClickListener(new MapClickListener() {
            @Override
            public void mapClicked(LatLon position) {
                String message = "Map click to (" + position.getLat() + ", " + position.getLon() + ")";
                Notification.show(message, Notification.Type.TRAY_NOTIFICATION);
            }
        });

        googleMap.addMarkerDragListener(new MarkerDragListener() {
            @Override
            public void markerDragged(GoogleMapMarker draggedMarker,
                                      LatLon oldPosition) {
                String message = "Marker \""
                        + draggedMarker.getCaption() + "\" dragged from ("
                        + oldPosition.getLat() + ", " + oldPosition.getLon()
                        + ") to (" + draggedMarker.getPosition().getLat()
                        + ", " + draggedMarker.getPosition().getLon() + ")";
                Notification.show(message, Notification.Type.TRAY_NOTIFICATION);
            }
        });

        googleMap.addInfoWindowClosedListener(new InfoWindowClosedListener() {

            @Override
            public void infoWindowClosed(GoogleMapInfoWindow window) {
                String message = "InfoWindow \""
                        + window.getContent() + "\" closed";
                Notification.show(message, Notification.Type.TRAY_NOTIFICATION);
            }
        });
        return googleMap;
    }}
