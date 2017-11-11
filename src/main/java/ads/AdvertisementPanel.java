/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ads;

import com.vaadin.ui.TabSheet;
import panel.FlexPanel;

/**
 *
 * @author zua
 */
public class AdvertisementPanel extends FlexPanel {

    private static final long serialVersionUID = -3339604655688381228L;
    private final TabSheet adContent;

    public AdvertisementPanel() {
        adContent = new TabSheet();
        adContent.setSizeFull();
        initCampaigns();
        super.setContent(adContent);
        super.setSizeFull();
    }
    
    private void initCampaigns() {
        addFavoriteBookOfTab();
    }

    private void addFavoriteBookOfTab() {
        adContent.addTab(new AmazonProductsGrid(AmazonCampaign.FAVORITE_BOOK_OF), "Favorite Books");
    }

}
