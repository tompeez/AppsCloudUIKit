package oracle.apps.uikit.common.bean;

/*
 * Copyright (c) 2016, 2013, Oracle and/or its affiliates. All rights reserved.
 *
**/

import java.io.Serializable;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

public class GlobalTemplatesBean implements Serializable {
    @SuppressWarnings("compatibility:-2078509379984336072")
    private static final long serialVersionUID = 1L;

    //==========================================================================
    // SimpleUIShell.jspx Routines
    //==========================================================================

    public void handleGoHome(ActionEvent ae) {
        FacesContext fctx = FacesContext.getCurrentInstance();
        UIViewRoot vr = fctx.getViewRoot();
        if (vr.getViewId().equals("/FilmStrip")){
            RichCommandButton actionTrigger = (RichCommandButton)vr.findComponent("pt1:pt_navigationTrigger");
            ActionEvent actionEvent = new ActionEvent(actionTrigger);
            actionEvent.queue();
        }//check if navigation required
    }//handleGoHome

}//GlobalTemplatesBean
