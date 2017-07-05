/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import db.news.FlexUser;
import db.news.GraphEntity;
import ui.news.article.FlexPanel;

/**
 *
 * @author zua
 */
public class FlexBody extends FlexPanel {
    private FlexUser user;
    
    public FlexBody(FlexUser user) {
        super.addStyleName("flex-body");
        this.user = user;
        setSizeFull();
        setContent(new MasterDetailView());
    }

    public MasterDetailView getContent() {
        return(MasterDetailView) super.getContent();
    }
    public void addItemView(GraphEntity item) {
        if(getUI() != null && getUI().isAttached()) {
            getUI().access(() -> {
                getContent().addComponent(FlexViewFactory.getInstance().createView(user, item));
            });
        }
    }

    public FlexUser getUser() {
        return user;
    }
    
    
    


}
