/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import com.vaadin.ui.Component;
import data.DataProviderType;

/**
 *
 * @author zua
 */
public class NewsBody extends AbstractBody {

    private static final long serialVersionUID = 6273025631274336910L;

    public NewsBody() {
    }

    @Override
    public void populate(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        getMasterDetail().refresh(type, value);
        System.out.println("FlexBodyThread#run(): DONE");
    }

    @Override
    protected Component createBodyContent() {
        return new MasterDetailView();
    }

    public MasterDetailView getMasterDetail() {
        return (MasterDetailView) getContent();
    }


}
