/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package flex.frontend.ui;

import flex.frontend.ui.news.article.FlexPanel;

/**
 *
 * @author zua
 */
public abstract class FlexBody extends FlexPanel {
    public FlexBody() {
        super.addStyleName("flex-body");
        setSizeFull();
    }
}
