package org.test;

import akka.actor.UntypedAbstractActor;

/**
 * Created by zua on 13/04/17.
 */
public class NewsPublisher extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable {
        if(message instanceof PublishNewsMessage) {
            System.out.println("Received Message " + message);
            publishNews((PublishNewsMessage) message);
        }
        else {
            unhandled(message);
        }
    }

    private void publishNews(PublishNewsMessage message) {
        ApiArticles articlesFromSource = NewsApiOrg.GET_Articles(message.getSource());
        for(ApiArticle article: articlesFromSource.getArticles()) {
            message.getUi().addArticleMarker(message.getSource(), article);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
