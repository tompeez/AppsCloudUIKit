package de.adfprojectsessions.hf.playlisten.playlistebearbeiten;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.Set;

import javax.faces.event.ActionEvent;

import oracle.adf.model.bean.DCDataRow;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.data.RichTable;

import oracle.apps.uikit.common.bean.CardViewListViewStateBean;
import oracle.apps.uikit.common.bean.UtilsBean;
import oracle.apps.uikit.common.declarativeComponents.CardViewListViewDCComponent;

import oracle.jbo.Row;
import oracle.jbo.uicli.binding.JUCtrlHierNodeBinding;

import org.apache.myfaces.trinidad.event.SelectionEvent;
import org.apache.myfaces.trinidad.model.RowKeySet;
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
    private RichTable trackTable;
    private RichListView trackViewList;

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

    public void handleAddSelectedTracks(ActionEvent actionEvent) {
        logger.info("Information");
        RichListView t = getTrackViewList();
        //RichTable t = getTrackTable();
        for (Object key : t.getSelectedRowKeys()) {
            t.setRowKey(key);
            Object o = t.getRowData();
            JUCtrlHierNodeBinding rowData = (JUCtrlHierNodeBinding) o;
            Row row = rowData.getRow();
            logger.info("row: " + row);
            DCDataRow r = (DCDataRow)row;
            String[] attributeNames = r.getAttributeNames();
            Object dataProvider = r.getDataProvider();
            LinkedHashMap m = (LinkedHashMap)dataProvider;
            Set keySet = m.keySet();
            ArrayList<LinkedHashMap> artists = (ArrayList<LinkedHashMap>)m.getOrDefault("artists", null);
            String nameArtist = "";
            for (LinkedHashMap malb : artists){
                if (!nameArtist.isEmpty()){
                    nameArtist += "; ";
                }
                nameArtist += (String)malb.getOrDefault("name", "-");
            }
            Object attrTrackName = r.getAttribute("name");
            LinkedHashMap album = (LinkedHashMap)m.getOrDefault("album", null);
            Object attrAlbumName = album.getOrDefault("name","--");
            
            logger.info("Information: Trackname: " + attrTrackName + " Artist: "+nameArtist+ " Album: "+attrAlbumName);
        }
    }

    public void setTrackTable(RichTable trackTable) {
        this.trackTable = trackTable;
    }

    public RichTable getTrackTable() {
        return trackTable;
    }

    public void trackSelectionListener(SelectionEvent selectionEvent) {
        RowKeySet addedSet = selectionEvent.getAddedSet();
        RowKeySet removedSet = selectionEvent.getRemovedSet();
        logger.info("Information: added:" + addedSet.getSize() + " removed: " + removedSet.getSize());
        if (removedSet.getSize() > 0) {
            logger.info("Removed:" + removedSet.toArray());
        }
        if (addedSet.getSize() > 0) {
            logger.info("Added:" + addedSet.toArray());
        }
    }

    public void setTrackViewList(RichListView trackViewList) {
        this.trackViewList = trackViewList;
    }

    public RichListView getTrackViewList() {
        return trackViewList;
    }
}
