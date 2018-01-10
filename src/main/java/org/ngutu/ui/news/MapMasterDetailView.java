/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.neovisionaries.i18n.CountryCode;
import com.vaadin.ui.Component;
import factory.ArticleView;
import factory.GraphEntityView;
import java.util.LinkedList;
import java.util.List;
import java.util.Optional;
import org.vaadin.alump.maplayout.WorldMap;

/**
 *
 * @author zua
 */
public class MapMasterDetailView extends ExternalMasterDetailView {

    private static final long serialVersionUID = 9138819737414819751L;
    private WorldMap worldMap;
    private List<CountryCode> codes;

    public MapMasterDetailView() {
        codes = new LinkedList<>();
    }

    @Override
    protected SummariesPanel createSummariesPanel() {
        return new SummariesPanel(1);
    }

    @Override
    protected Component createTargetView() {
        worldMap = new WorldMap();
        worldMap.setSizeFull();
        worldMap.addMapLayoutClickListener(mapClick -> {
            System.out.printf("Map element ids: %s\n", mapClick.getMapElementIds());
            System.out.printf("Map item: %s\n", mapClick.getMapItem());
            System.out.printf("Map items: %s\n", mapClick.getMapItems());
            System.out.printf("ViewBox x: %s\n", mapClick.getViewBoxX());
            System.out.printf("ViewBox y: %s\n", mapClick.getViewBoxY());
            if (mapClick.getMapItem().isPresent()) {
                CountryCode code = (CountryCode) mapClick.getMapItem().get();
                System.out.printf("Country Code: %s\n", code.getName());
                // Navigate to country news
                // Add/remove (Visible/invisible) articles from country from the summaries layout
                if (codes.contains(code)) {
                    // Make articles from this country NOT VISIBLE
                    getSummariesPanel().setVisibleIf(code, false);
                    worldMap.setStyleNameOfItem("black", code, true);
                    codes.remove(code);
                } else {
                    // Load Articles from this country mark them VISIBLE
                    getSummariesPanel().setVisibleIf(code, true);
                    worldMap.setStyleNameOfItem("yellow", code, true);
                    codes.add(code);
                }
            }
        });
        return worldMap;
    }

    @Override
    public void addSingleSummary(GraphEntityView component) {
        System.out.println("TODO: Add single summary" + component);
        if (component instanceof ArticleView) {
            ArticleView articleView = (ArticleView) component;
            String articleCountryCode = articleView.getArticle().getCountry();
            Optional<CountryCode> optional = worldMap.getItemForMapId(articleCountryCode);
            CountryCode code = null;
            if (optional.isPresent()) {
                code = optional.get();
                System.out.println("\tTODO: What to add to map. A clickable marker? " + code.getName());
                if (!codes.contains(code)) {
                    worldMap.setStyleNameOfItem("yellow", code, true);
                    codes.add(code);
                }
            }
        }
        super.addSingleSummary(component);
    }

}
