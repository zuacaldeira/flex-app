/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package ui.news.article;

import com.vaadin.ui.Label;
import com.vaadin.ui.themes.ValoTheme;
import java.util.Date;
import utils.FlexUtils;

/**
 *
 * @author zua
 */
public class DateLabel extends Label {

    public DateLabel(Date date) {
        super(FlexUtils.formatDate(date));
        setStyleName(ValoTheme.LABEL_TINY);
    }
    
}
