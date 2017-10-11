/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import data.ArticlesRepository;
import data.DataProviderType;
import db.FlexUser;
import db.GraphEntity;
import db.NewsArticle;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zua
 */
public class BodyWorker extends Thread {

    private final FlexBody body;
    private final DataProviderType type;
    private final String value;
    private final FlexUser user;

    public BodyWorker(FlexBody body, DataProviderType type, String value, FlexUser user) {
        this.body = body;
        this.type = type;
        this.value = value;
        this.user = user;

    }

    @Override
    public void run() {
        bodyCleanUp();
        Collection<NewsArticle> nodes = new ArticlesRepository().loadNodes(type, value, user);
        bodyUpdate(nodes);
    }

    private void bodyCleanUp() {
        if (body.getUI() != null && body.getUI().isAttached()) {
            body.getUI().access(() -> {
                body.cleanUp();
            });
        }
    }

    private void bodyUpdate(Collection<NewsArticle> nodes) {
        int i = 0;
        try {
            for (GraphEntity item : nodes) {
                if (body.getUI() != null && body.getUI().isAttached()) {
                    body.getUI().access(() -> {
                        body.addItemView(item);
                    });
                }
                if (i % 10 == 0) {
                    Thread.sleep(1000);
                }
                i++;
            }
        } catch (InterruptedException ex) {
            Logger.getLogger(FlexBody.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

}
