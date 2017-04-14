package org.test;

import akka.actor.ActorRef;
import akka.actor.Props;
import akka.actor.UntypedAbstractActor;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * Created by zua on 13/04/17.
 */
public class NewsManager extends UntypedAbstractActor {

    private Map<ApiSource, ApiArticles> articles = new HashMap<>();


    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof LoadAllMessage) {
            System.out.println("Received Message " + message);
            loadMessages((LoadAllMessage) message);
        }
        else {
            unhandled(message);
        }
    }

    private void loadMessages(LoadAllMessage message) {
        ApiSources sources = NewsApiOrg.GET_ApiSources();
        for(ApiSource source: sources.getSources()) {
            ActorRef actorRef = getContext().actorOf(Props.create(NewsPublisher.class));
            actorRef.tell(new PublishNewsMessage(message.getUi(), source), getSelf());
        }
    }
}
