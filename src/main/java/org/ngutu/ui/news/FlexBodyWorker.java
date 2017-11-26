/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

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
    private final MasterDetailView masterDetailView;
    private final DataProviderType type;
    private final String value;

    public FlexBodyWorker(FlexUser user, MasterDetailView masterDetailView, DataProviderType type, String value) {
        this.user = user;
        this.masterDetailView = masterDetailView;
        this.type = type;
        this.value = value;
    }

    @Override
    public void run() {
        try {
            Thread.sleep(500);
        } catch (InterruptedException ex) {
            Logger.getLogger(FlexBodyWorker.class.getName()).log(Level.SEVERE, null, ex);
            return;
        }
        Collection<NewsArticle> nodes = new ArticlesRepository().loadNodes(type, value, user);
        System.out.println("Loaded " + nodes.size() + " articles");

        int i = 1;
        for (NewsArticle article : nodes) {
            masterDetailView.getUI().access(() -> {
                masterDetailView.addItemView(article);
            });
            System.out.println("Article #" + i);
            i++;
            try {
                Thread.sleep(500);
            } catch (InterruptedException ex) {
                Logger.getLogger(FlexBodyWorker.class.getName()).log(Level.SEVERE, null, ex);
                return;
            }
        }
    }

}
