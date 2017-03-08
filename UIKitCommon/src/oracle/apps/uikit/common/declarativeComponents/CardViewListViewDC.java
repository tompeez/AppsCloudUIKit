package oracle.apps.uikit.common.declarativeComponents;

/*
 * Copyright (c) 2016, 2013, Oracle and/or its affiliates. All rights reserved.
 *
**/

import oracle.adf.view.rich.component.rich.fragment.RichDeclarativeComponent;

import javax.faces.event.ActionEvent;

import oracle.apps.uikit.common.bean.CardViewListViewStateBean;

public class CardViewListViewDC extends RichDeclarativeComponent {
    private static String CARD_VIEW = "cardView";
    private static String LIST_VIEW = "listView";

    private String _viewMode;


    /**
     * set viewMode
     * @param s mode to set [cardView|listView]
     */
    public void setViewMode(String s) {
        _viewMode = s;
        // check if we use an external stateBean to hold hte current viewMode
        Boolean b = (Boolean) this.getAttribute("useStateBean");
        CardViewListViewStateBean state = (CardViewListViewStateBean) this.getAttribute("stateBean");
        if (b.equals(Boolean.TRUE)) {
            // store viewMode in external bean
            state.setViewMode(_viewMode);
        }
    }

    /**
     * get current viewMode
     * @return current viewMode
     */
    public String getViewMode() {
        // check if we use an external stateBean to hold hte current viewMode
        Boolean b = (Boolean) this.getAttribute("useStateBean");
        CardViewListViewStateBean state = (CardViewListViewStateBean) this.getAttribute("stateBean");
        if (b.equals(Boolean.TRUE)) {
            //read viewMode from external bean: If might be null if no viewMode is explicit set in the bean. In this case
            // the defaultView is used
            _viewMode = state.getViewMode();
        }

        if (_viewMode == null) {
            String defaultView = (String) this.getAttribute("defaultView");
            if (defaultView.equalsIgnoreCase(LIST_VIEW))
                _viewMode = LIST_VIEW;
            else
                _viewMode = CARD_VIEW;

            if (b) {
                state.setViewMode(_viewMode);
            }
        } //null check
        return _viewMode;
    } //getViewMode

    //Called on click of card view icon in component
    public void showCardView(ActionEvent actionEvent) {
        if (getViewMode().equals(LIST_VIEW))
            setViewMode(CARD_VIEW);
    } //showCardView

    //Called on click of list view icon in component
    public void showListView(ActionEvent actionEvent) {
        if (getViewMode().equals(CARD_VIEW))
            setViewMode(LIST_VIEW);
    } //showListView

    /*
    public void toggleViewMode(ActionEvent actionEvent){
        if (getViewMode().equals(CARD_VIEW))
            setViewMode(LIST_VIEW);
        else
            setViewMode(CARD_VIEW);
    }//toggleViewMode
*/
}//CardViewListViewDC
