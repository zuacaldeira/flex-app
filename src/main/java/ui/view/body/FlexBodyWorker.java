/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.view.body;

import data.ArticlesRepository;
import data.DataProviderType;
import db.FlexUser;
import db.NewsArticle;
import java.util.Collection;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author zua
 */
public class FlexBodyWorker extends Thread {

    private final FlexUser user;
    private final FlexBody flexBody;
    private final DataProviderType type;
    private final String value;

    public FlexBodyWorker(FlexUser user, FlexBody flexBody, DataProviderType type, String value) {
        this.user = user;
        this.flexBody = flexBody;
        this.type = type;
        this.value = value;
    }

    @Override
    public void run() {
        Collection<NewsArticle> nodes = new ArticlesRepository().loadNodes(type, value, user);
        for(NewsArticle article: nodes) {
            flexBody.addItemView(article);
            try {
                Thread.sleep(1000);
            } catch (InterruptedException ex) {
                Logger.getLogger(FlexBodyWorker.class.getName()).log(Level.SEVERE, null, ex);
                break;
            }
        }
    }

}
