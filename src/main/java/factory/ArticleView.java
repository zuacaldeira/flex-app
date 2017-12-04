package factory;

import db.FlexUser;
import db.NewsArticle;

/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView<NewsArticle> {

    private static final long serialVersionUID = 7693645013887515529L;

    // Info components
    private ArticleViewHeader articleViewHeader;
    private ArticleViewBody articleViewBody;
    private ArticleViewActions articleViewActions;

    public ArticleView(FlexUser user, NewsArticle article) {
        super(user, article);
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
        this.articleViewBody = new ArticleViewBody(getUser(), getItem());
        super.addComponent(articleViewBody);
    }

    private void initActions() {
        this.articleViewActions = new ArticleViewActions(getUser(), getItem());
        super.addComponent(articleViewBody);
    }

    @Override
    public NewsArticle getItem() {
        return (NewsArticle) super.getItem();
    }

    /* TODO: Remove */
    public NewsArticle getArticle() {
        return getItem();
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
