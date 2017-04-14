package org.test;

/**
 * Created by zua on 13/04/17.
 */
public class PublishNewsMessage {
    private ApiSource source;
    private MyUI ui;

    public PublishNewsMessage(MyUI ui, ApiSource source) {
        this.ui = ui;
        this.source = source;
    }


    public ApiSource getSource() {
        return source;
    }

    public void setSource(ApiSource source) {
        this.source = source;
    }

    public MyUI getUi() {
        return ui;
    }

    public void setUi(MyUI ui) {
        this.ui = ui;
    }
}
