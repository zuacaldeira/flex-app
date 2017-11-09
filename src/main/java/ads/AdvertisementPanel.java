/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads;

import com.vaadin.server.ExternalResource;
import com.vaadin.ui.BrowserFrame;
import db.NewsArticle;
import panel.FlexPanel;

/**
 *
 * @author zua
 */
public class AdvertisementPanel extends FlexPanel {

    private static final long serialVersionUID = -3339604655688381228L;
    private final BrowserFrame browserFrame;

    public AdvertisementPanel() {
        browserFrame = new BrowserFrame();
        browserFrame.setSizeFull();
        super.setContent(browserFrame);
        super.setSizeFull();
    }

    public void refreshItems(NewsArticle newsArticle) {
        browserFrame.setSource(new ExternalResource("//ws-na.amazon-adsystem.com/widgets/q?ServiceVersion=20070822&OneJS=1&Operation=GetAdHtml&MarketPlace=US&source=ac&ref=tf_til&ad_type=product_link&tracking_id=ngutuland-20&marketplace=amazon&region=US&placement=B073DLZWX7&asins=B073DLZWX7&linkId=aabaee0e6a572ef7686fa12ec8fc8919&show_border=true&link_opens_in_new_window=false&price_color=333333&title_color=0066c0&bg_color=ffffff"));
    }

}
