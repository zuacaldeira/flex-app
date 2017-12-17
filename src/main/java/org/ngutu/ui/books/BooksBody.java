/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.books;

import com.vaadin.ui.Component;
import com.vaadin.ui.Label;
import data.DataProviderType;
import org.ngutu.ui.news.AbstractBody;

/**
 *
 * @author zua
 */
public class BooksBody extends AbstractBody {

    private static final long serialVersionUID = 6273025631274336910L;

    public BooksBody() {
    }


    @Override
    public void populate(DataProviderType type, String value) {
        System.out.println("FlexBodyThread#run(): START");
        //new FlexBodyWorker(user, masterDetailView, type, value).start();
        System.out.println("FlexBodyThread#run(): DONE");
    }

    @Override
    protected Component createBodyContent() {
        return new Label("TODO");
    }
    
}
