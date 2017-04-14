package org.test;

/**
 * Created by zua on 13/04/17.
 */
public class LoadAllMessage {
    private MyUI ui;

    public LoadAllMessage(MyUI ui) {
        this.ui = ui;
    }

    public MyUI getUi() {
        return ui;
    }

    public void setUi(MyUI ui) {
        this.ui = ui;
    }
}
