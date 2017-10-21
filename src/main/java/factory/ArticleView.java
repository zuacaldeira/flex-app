package factory;

import db.FlexUser;
import db.NewsArticle;

/**
 * Created by zua on 13/04/17.
 */
public class ArticleView extends GraphEntityView<NewsArticle> {

    // Info components
    private ArticleViewHeader articleViewHeader;
    private ArticleViewBody articleViewBody;
    private ArticleViewActions articleViewActions;
    private SourceInfoView sourceInfo;

    public ArticleView(FlexUser user, NewsArticle article) {
        super(user, article);
        this.initSourceInfo();        
        this.initHeader();
        this.initBody();
        this.initActions();
        super.addComponents(articleViewHeader, articleViewBody, articleViewActions);
        super.setExpandRatio(articleViewBody, 1f);
    }
    
    private void initSourceInfo() {
        sourceInfo = new SourceInfoView(getArticle());
    }
    
    private void initHeader() {
        this.articleViewHeader = new ArticleViewHeader(getUser(), getItem());
    }
    
    private void initBody() {
        this.articleViewBody = new ArticleViewBody(getUser(), getItem());
    }
    
    private void initActions() {
        this.articleViewActions = new ArticleViewActions(getUser(), getItem());
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

    public SourceInfoView getSourceInfo() {
        return sourceInfo;
    }


    @Override
    public void maximize() {
        super.maximize();
    }

    @Override
    public void minimize() {
        super.minimize();
    }


}
