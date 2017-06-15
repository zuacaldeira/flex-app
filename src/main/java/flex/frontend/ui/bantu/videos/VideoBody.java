/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui.bantu.videos;

import flex.backend.bantus.db.FlexVideo;
import flex.backend.news.services.AbstractDBService;
import flex.frontend.ui.crud.CrudBody;
import org.utils.ServiceLocator;

/**
 *
 * @author zua
 */
public class VideoBody extends CrudBody<FlexVideo> {

    public VideoBody() {
    }

    @Override
    protected AbstractDBService<FlexVideo> getService() {
        return ServiceLocator.getInstance().findVideoService();
    }

    
}
