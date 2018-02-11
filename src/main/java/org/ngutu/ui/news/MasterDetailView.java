/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import factory.GraphEntityView;
import com.vaadin.ui.Component;
import com.vaadin.ui.HorizontalLayout;
import factory.ArticleView;

/**
 *
 * @author zua
 */
public abstract class MasterDetailView extends NewsBody {

    private static final long serialVersionUID = 4092595001827313256L;

    private HorizontalLayout masterDetail;
    private SummariesPanel master;
    private Component detail;
    private ArticleView selected;

    public MasterDetailView() {
    }

    public HorizontalLayout getMasterDetail() {
        return masterDetail;
    }

    public SummariesPanel getMaster() {
        return master;
    }

    public Component getDetail() {
        return detail;
    }

    public ArticleView getSelected() {
        return selected;
    }

    public SummariesPanel getSummaries() {
        return master;
    }

    protected void updateSelected(ArticleView itemView) {
        if (selected != null) {
            selected.unselect();
        }
        selected = itemView;
        selected.select();

        String url = selected.getArticle().getUrl();
        updateTarget(url);
    }

    @Override
    public void addSingleSummary(GraphEntityView component) {
        master.addItemView(component);
        if (selected == null) {
            updateSelected((ArticleView) component);
        }
        ((GraphEntityView) component).addLayoutClickListener(event -> {
            updateSelected((ArticleView) component);
        });
    }

    @Override
    protected Component createBodyContent() {
        masterDetail = new HorizontalLayout();
        masterDetail.setSizeFull();
        masterDetail.setStyleName("master-detail");

        master = createSummariesPanel();
        if (master != null) {
            master.setStyleName("master");
            masterDetail.addComponent(master);
            masterDetail.setExpandRatio(master, .25f);
        }

        detail = createTargetView();
        if (detail != null) {
            detail.setStyleName("detail");
            masterDetail.addComponent(detail);
            masterDetail.setExpandRatio(detail, .75f);
        }
        super.setSizeFull();
        
        super.setContent(masterDetail);
        return masterDetail;
    }

    protected abstract SummariesPanel createSummariesPanel();

    protected abstract Component createTargetView();

    protected abstract void updateTarget(String url);
}
