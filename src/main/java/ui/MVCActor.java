/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui;

import akka.actor.AbstractActor;
import akka.actor.Props;
import db.news.NewsArticle;
import java.util.Iterator;

/**
 *new 
 * @author zua
 */
public class MVCActor extends AbstractActor {
    
    public static Props props() {
        return Props.create(MVCActor.class);
    }

    public MVCActor() {
        receive();
    }

    @Override
    public Receive createReceive() {
        return receiveBuilder()
                .match(UpdateBodyMessage.class, msg -> {
                    updateBody((UpdateBodyMessage) msg);
                }).build();
    }

    private void updateBody(UpdateBodyMessage updateBodyMessage) {
        Iterator<NewsArticle> it = updateBodyMessage.getNodes().iterator();
        int i = 0;
        NewsArticle next = null;
        while(it.hasNext()) {
            if(i % 10 == 0) {
                try {Thread.sleep(1000);}catch(InterruptedException e){ return ;}
            }
            next = it.next();
            updateBodyMessage.getBody().addItemView(next);
        }               
    }
    
}
