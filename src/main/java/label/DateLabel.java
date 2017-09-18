/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package label;

import com.vaadin.ui.Label;
import java.util.Date;
import utils.FlexUIUtils;

/**
 *
 * @author zua
 */
public class DateLabel extends Label {

    public DateLabel(Date date) {
        super(FlexUIUtils.formatDate(date));
    }
    
}
