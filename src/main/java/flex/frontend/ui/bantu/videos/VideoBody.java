/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu.videos;

import com.vaadin.ui.Notification;
import com.vaadin.ui.Window;
import flex.backend.bantus.db.FlexVideo;
import flex.backend.bantus.services.FlexVideoService;
import flex.frontend.ui.FlexBody;
import flex.frontend.ui.SecuredUI;
import flex.frontend.ui.news.FlexWindow;
import java.util.Date;
import org.neo4j.ogm.exception.CypherException;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class VideoBody extends FlexBody {

    public VideoBody() {
    }

    private void showCreateForm() {
        VideoForm videoForm = new VideoForm();
        Window w = new FlexWindow("Add Video", videoForm);
        videoForm.getSaveButton().addClickListener(event -> {
            FlexVideo newVideo = new FlexVideo();
            newVideo.setUrl(videoForm.getUrlField().getValue());
            newVideo.setTitle(videoForm.getTitleField().getValue());
            newVideo.setPostedBy( ((SecuredUI)getUI()).getFlexUser());
            newVideo.setPostedAt(new Date());
            newVideo.setDescription(videoForm.getDescriptionField().getValue());
            newVideo.setAuthor(videoForm.getAuthorField().getValue());
            if (videoForm.getLanguageField().getSelectedItem().isPresent()) {
                newVideo.setLanguage(((String) videoForm.getLanguageField().getSelectedItem().get()));
            }
            try {
                FlexVideoService service = ServiceLocator.getInstance().findVideoService();
                service.save(newVideo);
                //((AbstractOrderedLayout) getContent()).addComponent(FlexViewFactory.getInstance().createView(newVideo));
            } catch (CypherException ce) {
                Notification.show("Video already exists", Notification.Type.WARNING_MESSAGE);
            } finally {
                w.close();
            }
        });
        getUI().addWindow(w);
    }

}
