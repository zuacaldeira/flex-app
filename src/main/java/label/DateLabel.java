/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package label;

import com.vaadin.ui.Label;
import java.util.Date;
import org.apache.commons.lang3.time.DateFormatUtils;

/**
 *
 * @author zua
 */
public class DateLabel extends Label {

    public DateLabel(Date date) {
        super(toText(date));
    }
    
    private static String toText(Date date) {
        if(date != null) {
            return DateFormatUtils.format(date, "dd MMM yyyy, HH:mm:ss");
        } 
        else {
            return DateFormatUtils.format(new Date(), "dd MMM yyyy, HH:mm:ss");
        }
    }
}
