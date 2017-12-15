package factory;

import db.auth.FlexUser;
import db.news.NewsArticle;
import db.news.NewsSource;
import db.relationships.PublishedBy;

/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView<PublishedBy> {

    private static final long serialVersionUID = 7693645013887515529L;

    // Info components
    private ArticleViewHeader articleViewHeader;
    private ArticleViewBody articleViewBody;
    private ArticleViewActions articleViewActions;

    public ArticleView(FlexUser user, PublishedBy publishedBy) {
        super(user, publishedBy);
        this.initHeader();
        this.initBody();
        this.initActions();
        super.setExpandRatio(articleViewBody, 1f);
    }

    
    private void initHeader() {
        this.articleViewHeader = new ArticleViewHeader(getUser(), getItem());
        super.addComponent(articleViewHeader);
    }

    private void initBody() {
        this.articleViewBody = new ArticleViewBody(getUser(), getArticle());
        super.addComponent(articleViewBody);
    }

    private void initActions() {
        this.articleViewActions = new ArticleViewActions(getUser(), getArticle());
        super.addComponent(articleViewActions);
    }

    @Override
    public PublishedBy getItem() {
        return (PublishedBy) super.getItem();
    }

    /* TODO: Remove */
    public NewsArticle getArticle() {
        return getItem().getArticle();
    }

    public NewsSource getSource() {
        return getItem().getSource();
    }

    public ArticleViewHeader getArticleViewHeader() {
        return articleViewHeader;
    }

    public ArticleViewBody getArticleViewBody() {
        return articleViewBody;
    }

    public ArticleViewActions getArticleViewActions() {
        return articleViewActions;
    }

    @Override
    public void select() {
        super.select();
    }

    @Override
    public void unselect() {
        super.unselect();
    }

    public void full() {
        articleViewHeader.full();
        articleViewBody.full();
        articleViewActions.full();
    }

    public void imagesOnly() {
        articleViewHeader.imagesOnly();
        articleViewBody.imagesOnly();
        articleViewActions.imagesOnly();
    }

    public void titlesOnly() {
        articleViewHeader.titlesOnly();
        articleViewBody.titlesOnly();
        articleViewActions.titlesOnly();
    }
}
