/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Props;
import com.vaadin.ui.AbsoluteLayout;
import com.vaadin.ui.Notification;
import flex.backend.news.db.FlexUser;
import flex.frontend.ui.crud.MVCActor;

/**
 *
 * @author zua
 */
public abstract class FlexMainView extends AbsoluteLayout {
    
    private FlexMenu menu;
    private FlexBody body;
    private FlexFooter footer;
    private ActorSystem actorSystem;
    private ActorRef actorRef;
    

    public FlexMainView() {
        initActorSystem();
        initMenu();
        initBody();
        initFooter();
        setStyleName("flex-view");
        setSizeFull();
        addComponent(body, getBodyStyle());
        addComponent(footer, getFooterStyle());
        addComponent(menu, getMenuStyle());
    }

    private void initActorSystem() {
        actorSystem = ActorSystem.create("TypeSystem_Flex");
        actorRef = actorSystem.actorOf(Props.create(MVCActor.class));
    }
    
    private void initMenu() {
        setMenu(createMenu());
        menu.setWidth("100%");
        menu.setHeight(getTopPosition());
    }

    private void initBody() {
        setBody(createBody());
        body.setSizeFull();
    }

    private void initFooter() {
        setFooter(createFooter());
        footer.setWidth("100%");
        footer.setHeight("1cm");
    }

    public void setBody(FlexBody flexBody) {
        body = flexBody;
    }
    
    private String getBodyStyle() {
        return "top:" + getTopPosition() + "; " + "bottom:" + getBottomPosition();
    }

    private String getTopPosition() {
        return "1.5cm";
    }
    
    private String getBottomPosition() {
        return "1cm";
    }
    
    private String getFooterStyle() {
        return "bottom:0%";
    }
    
    private String getMenuStyle() {
        return "top:0%";
    }

    public void setMenu(FlexMenu menu) {
        this.menu = menu;
    }
    
    
    public void setFooter(FlexFooter footer) {
        this.footer = footer;
    }

    public FlexMenu getMenu() {
        return menu;
    }

    public FlexBody getBody() {
        return body;
    }

    public FlexFooter getFooter() {
        return footer;
    }

    public void replaceBody(FlexBody flexBody) {
        replaceComponent(body, flexBody);
        setBody(flexBody);
        Notification.show("Body Set");
    }
    
    public FlexUser getFlexUser() {
        return ((SecuredUI)getUI()).getFlexUser();
    }

    public ActorRef getActorRef() {
        return actorRef;
    }
    
    


    protected abstract FlexMenu createMenu();
    protected abstract FlexBody createBody();
    protected abstract FlexFooter createFooter();

}
