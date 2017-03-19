package de.adfprojectsessions.hf.playlisten.playlistebearbeiten;

import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;

import oracle.apps.uikit.common.bean.CardViewListViewStateBean;
import oracle.apps.uikit.common.bean.UtilsBean;
import oracle.apps.uikit.common.declarativeComponents.CardViewListViewDCComponent;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class PlaylisteBearbeitenBean {
    private static ADFLogger logger = ADFLogger.createADFLogger(PlaylisteBearbeitenBean.class);
    private static final String ERROR = "ERROR";

    // search term
    private String nameSearch;

    // reference to the CardViewListViewDC component
    private ComponentReference cardViewListView;
    // utility bean
    private UtilsBean _utils = new UtilsBean();
    // state of hte CardViewListViewDC component
    private CardViewListViewStateBean currentViewState;
    // current select kategorie. If null none is selected
    private Long _selectedPlayliste = null;    
    public PlaylisteBearbeitenBean() {
            currentViewState = new CardViewListViewStateBean();
            }

    public void handleNameSearch(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getNameSearch() {
        return nameSearch;
    }
    
    public String handleExceptionOnSearch() {
        String ret = "showtitle";
        return ret;
    }

    /**
     * Setter.
     * @param cardViewListView
     */
    public void setCardViewListView(CardViewListViewDCComponent cardViewListView) {
        if (cardViewListView != null) {
            this.cardViewListView = ComponentReference.newUIComponentReference(cardViewListView);
        } else {
            this.cardViewListView = null;
        }

    }

    /**
     * Getter.
     * @return
     */
    public CardViewListViewDCComponent getCardViewListView() {
        if (this.cardViewListView != null) {
            return (CardViewListViewDCComponent) cardViewListView.getComponent();
        }

        return null;
    }

    public void setCurrentViewState(CardViewListViewStateBean currentViewState) {
        this.currentViewState = currentViewState;
    }

    public CardViewListViewStateBean getCurrentViewState() {
        return currentViewState;
    }

    public void setSelectedPlayliste(Long _selectedPlayliste) {
        this._selectedPlayliste = _selectedPlayliste;
    }

    public Long getSelectedPlayliste() {
        return _selectedPlayliste;
    }
}
