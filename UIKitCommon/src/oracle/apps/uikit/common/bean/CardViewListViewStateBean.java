package oracle.apps.uikit.common.bean;

import java.io.Serializable;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

public class CardViewListViewStateBean implements Serializable {
    @SuppressWarnings("compatibility:2733846113378833988")
    private static final long serialVersionUID = 1L;

    private static ADFLogger _logger = ADFLogger.createADFLogger(CardViewListViewStateBean.class);
    private static String CARD_VIEW = "cardView";
    private static String LIST_VIEW = "listView";
    private String _viewMode;

    public CardViewListViewStateBean() {
        super();
    }

    public void setViewMode(String s) {
        _viewMode = s;
    }

    public String getViewMode() {
        if (_viewMode == null) {
                _viewMode = CARD_VIEW;
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
}

