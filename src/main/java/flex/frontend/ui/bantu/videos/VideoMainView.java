/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu.videos;

import com.vaadin.ui.Button;
import com.vaadin.ui.Button.ClickListener;
import com.vaadin.ui.Notification;
import com.vaadin.ui.Notification.Type;
import com.vaadin.ui.Window;
import flex.backend.bantus.db.FlexVideo;
import flex.backend.bantus.services.FlexVideoService;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.FlexFooter;
import flex.frontend.ui.FlexMenu;
import flex.frontend.ui.FlexViewFactory;
import flex.frontend.ui.crud.CrudMainView;
import flex.frontend.ui.news.FlexWindow;
import java.util.Date;
import org.neo4j.ogm.exception.CypherException;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class VideoMainView extends CrudMainView<FlexVideo>  implements ClickListener {

    public VideoMainView() {
        listenMenuActions();
    }

    @Override
    protected FlexMenu createMenu() {
        return new VideoMenu();
    }

    @Override
    protected FlexBody createBody() {
        return new VideoBody();
    }

    @Override
    protected FlexFooter createFooter() {
        return new FlexFooter();
    }
    
    
    @Override
    public VideoMenu getMenu() {
        return (VideoMenu) super.getMenu();
    }
    
    
    public void listenMenuActions() {
        ((VideoMenu)getMenu()).getCreateButton().addClickListener(this);
        ((VideoMenu)getMenu()).getSearchButton().addClickListener(this);
    }

    @Override
    public void buttonClick(Button.ClickEvent event) {
        if(event.getButton() == getMenu().getCreateButton()) {
            Notification.show("Clicked CreateButton");
            showCreateForm();
        }
        else if(event.getButton() == getMenu().getSearchButton()) {
            Notification.show("Clicked SearchButton");
        }
    }

    private void showCreateForm() {
        VideoForm videoForm = new VideoForm();
        Window w = new FlexWindow("Add Video", videoForm);
        videoForm.getSaveButton().addClickListener(event -> {
            FlexVideo newVideo = new FlexVideo();
            newVideo.setUrl(videoForm.getUrlField().getValue());
            newVideo.setTitle(videoForm.getTitleField().getValue());
            newVideo.setPostedBy(super.getFlexUser());
            newVideo.setPostedAt(new Date());
            newVideo.setDescription(videoForm.getDescriptionField().getValue());
            newVideo.setAuthor(videoForm.getAuthorField().getValue());  
            if(videoForm.getLanguageField().getSelectedItem().isPresent()) {
                newVideo.setLanguage(((String)videoForm.getLanguageField().getSelectedItem().get()));
            }
            try{
                FlexVideoService service = ServiceLocator.getInstance().findVideoService();
                service.save(newVideo);
                getBody().getSummaries().addComponent(FlexViewFactory.getInstance().createView(newVideo));
            } catch(CypherException ce) {
                Notification.show("Video already exists", Type.WARNING_MESSAGE);
            } finally {
                w.close();
            }
        });
        getUI().addWindow(w);
    }
    
    
}
