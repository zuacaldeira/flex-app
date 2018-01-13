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

    private HorizontalLayout baseLayout;
    private SummariesPanel summariesPanel;
    private Component target;
    private ArticleView selected;

    public MasterDetailView() {
    }

    public HorizontalLayout getBaseLayout() {
        return baseLayout;
    }

    public SummariesPanel getSummariesPanel() {
        return summariesPanel;
    }

    public Component getTarget() {
        return target;
    }

    public ArticleView getSelected() {
        return selected;
    }

    public SummariesPanel getSummaries() {
        return summariesPanel;
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

    public void addSingleSummary(GraphEntityView component) {
        if (getUI() != null && getSession() != null) {
            summariesPanel.addItemView(component);
            if (selected == null) {
                updateSelected((ArticleView) component);
            }
            ((GraphEntityView) component).addLayoutClickListener(event -> {
                updateSelected((ArticleView) component);
            });
        }
    }

    @Override
    protected Component createBodyContent() {
        summariesPanel = createSummariesPanel();
        target = createTargetView();

        baseLayout = new HorizontalLayout();
        if (summariesPanel != null) {
            baseLayout.addComponent(summariesPanel);
            baseLayout.setExpandRatio(summariesPanel, .25f);
        }
        if (target != null) {
            baseLayout.addComponent(target);
            baseLayout.setExpandRatio(target, 1f);
        }
        baseLayout.setSizeFull();
        baseLayout.setSpacing(true);
        baseLayout.setMargin(false);
        super.setSizeFull();
        super.setContent(baseLayout);
        return baseLayout;
    }

    protected abstract SummariesPanel createSummariesPanel();

    protected abstract Component createTargetView();

    protected abstract void updateTarget(String url);
}
