/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package org.ngutu.ui.news;

import org.ngutu.ui.common.AbstractBody;

/**
 *
 * @author zua
 */
public class NewsBody extends AbstractBody {

    private static final long serialVersionUID = 6273025631274336910L;

    public NewsBody() {
    }

    @Override
    protected MasterDetailView createBodyContent() {
        return new MasterDetailView();
    }

    public MasterDetailView getMasterDetail() {
        return (MasterDetailView) getContent();
    }

}
