package flex.frontend.ui.news;

import com.vaadin.ui.*;
import flex.backend.news.db.NewsAuthor;


/**
 * Created by zua on 13/04/17.
 */
public class AuthorView extends VerticalLayout {
    private final NewsAuthor author;
    private Label name;
    private VerticalLayout info;
    private HorizontalLayout controls;

    public AuthorView(NewsAuthor author) {
        this.author = author;
        initName();
        initInfo();
        initControls();
        setSizeFull();
        addComponents(info);
        setStyleName("article");
        setMargin(false);
    }

    private void initName() {
        if(author.getName() != null) {
            name = new Label(author.getName());
        }
    }


    private void initInfo() {
        info = new VerticalLayout();
        if(name != null) {
            info.addComponent(name);
        }
    }

    private void initControls() {
        controls = new HorizontalLayout();
    }

    public Label getName() {
        return name;
    }

    public void setName(Label name) {
        this.name = name;
    }

    public VerticalLayout getInfo() {
        return info;
    }

    public void setInfo(VerticalLayout info) {
        this.info = info;
    }

    public HorizontalLayout getControls() {
        return controls;
    }

    public void setControls(HorizontalLayout controls) {
        this.controls = controls;
    }

    public NewsAuthor getAuthor() {
        return author;
    }
    
    
    
    

}
