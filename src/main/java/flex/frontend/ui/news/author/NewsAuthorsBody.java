/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.news.author;

import flex.backend.news.db.NewsAuthor;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.crud.CrudBody;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class NewsAuthorsBody extends CrudBody<NewsAuthor> {

    public NewsAuthorsBody() {
    }

    @Override
    protected AbstractDBService<NewsAuthor> getService() {
        return ServiceLocator.getInstance().findAuthorsService();
    }
    
}
