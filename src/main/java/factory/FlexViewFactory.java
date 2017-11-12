package factory;

import com.ECS.client.jax.Item;
import com.ECS.client.jax.Items;
import com.vaadin.ui.Component;
import db.AmazonBook;
import db.FlexUser;
import db.GraphEntity;
import db.NewsArticle;
import db.NewsSource;
import db.NewsAuthor;


/**
 * Created by zua on 12/04/17.
 */
public class FlexViewFactory {

    private static FlexViewFactory instance;
    
    
    private FlexViewFactory() {}
    
    public  SourceView createSourceView(FlexUser user, NewsSource source) {
        return new SourceView(user, source);
    }

    public  ArticleView createArticleView(FlexUser user, NewsArticle article) {
        return new ArticleView(user, article);
    }

    public  AuthorView createAuthorView(FlexUser user, NewsAuthor author) {
        return new AuthorView(user, author);
    }
    
    public  GraphEntityView createView(FlexUser user, GraphEntity entity) {
        if(entity instanceof NewsArticle) {
            return createArticleView(user, (NewsArticle) entity);
        }
        else if(entity instanceof NewsSource) {
            return createSourceView(user, (NewsSource) entity);
        }
        else if(entity instanceof NewsAuthor) {
            return createAuthorView(user, (NewsAuthor) entity);
        }
        return null;
    }

    public static  FlexViewFactory getInstance() {
        if(instance == null) {
            instance = new FlexViewFactory();
        }
        
        return instance;
    }

    
    public Component[] createItemsView(Items items) {
        if(items.getItem() != null) {
            Component[] layout = new Component[items.getItem().size()];
            for(int j = 0; j < items.getItem().size(); j++) {
                Item item = items.getItem().get(j);
                layout[j] = createAmazonItemView(item);
            }
            return layout;
        }
        return null;
    }

    private AmazonItemView createAmazonItemView(Item item) {
        return new AmazonItemView(item);
    }

    public AmazonBookView createAmazonBookView(AmazonBook book) {
        AmazonBookView amazonBookView = new AmazonBookView(book);
        return amazonBookView;
    }
}
