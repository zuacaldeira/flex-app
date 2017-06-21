/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.crud;

import com.vaadin.ui.AbstractComponentContainer;
import flex.backend.news.db.GraphEntity;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexViewFactory;

/**
 *
 * @author zua
 * @param <T> Any subtype of GraphEntity.
 */
public abstract class CrudBody<T extends GraphEntity> extends FlexBody {

    public CrudBody() {
    }

    protected abstract AbstractDBService<T> getService();

    public void forceSplit() {
        this.setContent(new MasterDetailView());
    }
    
    public void forceGrid() {
        this.setContent(new FlexGridLayout<>(4, 4));
    }
    
    private void setContent(AbstractComponentContainer c) {
        super.setContent(c);
    }
    
    public void addItems() {
        getUI().access(() -> {
            waitABit();
            loadItems().forEach(item -> {
                getContent().addComponent(FlexViewFactory.getInstance().createView(item));
            });
        });
    }
    
    public Iterable<T> loadItems() {
        return getService().findAll();
    }
    

    @Override
    public AbstractComponentContainer getContent() {
        return (AbstractComponentContainer) super.getContent();
    }
    
    public boolean isMasterDetailView() {
        return getContent() instanceof MasterDetailView;
    }
    
    public boolean isGridLayoutView() {
        return getContent() instanceof FlexGridLayout;
    }
    
    
    public MasterDetailView getMasterDetailView() {
        return (MasterDetailView) getContent();
    }
    
    public FlexGridLayout getGridLayoutView() {
        return (FlexGridLayout) getContent();
    }

    private void waitABit() {
        try {
            Thread.sleep(100);
        } catch(Exception e) {}
    }

}
