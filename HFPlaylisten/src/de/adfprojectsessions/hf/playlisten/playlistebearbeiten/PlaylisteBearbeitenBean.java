package de.adfprojectsessions.hf.playlisten.playlistebearbeiten;

import de.adfprojectsesions.hf.model.adfbc.views.common.HfPlaylistPosViewRow;

import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Set;
import java.util.logging.Level;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.bean.DCDataRow;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.data.RichListView;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.apps.uikit.common.bean.CardViewListViewStateBean;
import oracle.apps.uikit.common.bean.UIUtilBean;
import oracle.apps.uikit.common.bean.UtilsBean;
import oracle.apps.uikit.common.declarativeComponents.CardViewListViewDCComponent;
import oracle.apps.uikit.memoryCache.SessionState;

import oracle.binding.OperationBinding;

import oracle.jbo.NavigatableRowIterator;
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
    private ComponentReference trackViewList;

    public PlaylisteBearbeitenBean() {
        currentViewState = new CardViewListViewStateBean();
    }

    public void handleNameSearch(ActionEvent actionEvent) {
        // Add event code here...
    }

    /**
     * Setter.
     * @param nameSearch
     */
    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    /**
     * Getter.
     * @return
     */
    public String getNameSearch() {
        return nameSearch;
    }

    /**
     * Handle taskflow exceptions.
     * @return
     */
    public String handleExceptionOnSearch() {
        logger.severe("Error: Exceptionhandler called!");

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

    /**
     * Setter.
     * @param currentViewState
     */
    public void setCurrentViewState(CardViewListViewStateBean currentViewState) {
        this.currentViewState = currentViewState;
    }

    /**
     * Getter.
     * @return
     */
    public CardViewListViewStateBean getCurrentViewState() {
        return currentViewState;
    }

    /**
     * Setter.
     * @param _selectedPlayliste
     */
    public void setSelectedPlayliste(Long _selectedPlayliste) {
        this._selectedPlayliste = _selectedPlayliste;
    }

    /**
     * Getter.
     * @return
     */
    public Long getSelectedPlayliste() {
        return _selectedPlayliste;
    }

    /**
     * Handle add title to playlist
     * @param actionEvent event which triggered the method
     */
    public void handleAddSelectedTracks(ActionEvent actionEvent) {
        logger.info("Information");
        RichListView t = getTrackViewList();
        //RichTable t = getTrackTable();
        int order = 10;
        for (Object key : t.getSelectedRowKeys()) {
            t.setRowKey(key);
            Object o = t.getRowData();
            JUCtrlHierNodeBinding rowData = (JUCtrlHierNodeBinding) o;
            Row row = rowData.getRow();
            logger.info("row: " + row);
            DCDataRow r = (DCDataRow) row;
            String[] attributeNames = r.getAttributeNames();
            Object dataProvider = r.getDataProvider();
            LinkedHashMap m = (LinkedHashMap) dataProvider;
            Object attrTrackName = r.getAttribute("name");
            Object track_number = r.getAttribute("track_number");
            Set keySet = m.keySet();
            ArrayList<LinkedHashMap> artists = (ArrayList<LinkedHashMap>) m.getOrDefault("artists", null);
            String nameArtist = "";
            for (LinkedHashMap malb : artists) {
                if (!nameArtist.isEmpty()) {
                    nameArtist += "; ";
                }
                nameArtist += (String) malb.getOrDefault("name", "-");
            }
            LinkedHashMap album = (LinkedHashMap) m.getOrDefault("album", null);
            Object attrAlbumName = album.getOrDefault("name", "--");
            Object attrAlbumId = album.getOrDefault("id", "--");
            Object attrAlbumImages = album.getOrDefault("images", "--");

            logger.info("Information: Trackname: " + attrTrackName + " Artist: " + nameArtist + " Album: " +
                        attrAlbumName);

            HfPlaylistPosViewRow title = createTitle();
            title.setAlbum(attrAlbumName.toString());
            title.setAlbumId(attrAlbumId.toString());
            title.setArtist(nameArtist);
            title.setArtistId("");
            title.setImageUrl("folgt");
            title.setOrderNum(order);
            order = order + 10;
            if (track_number != null) {
                title.setTrackId(track_number.toString());
            }
            if (attrTrackName != null) {
                title.setTrackName(attrTrackName.toString());
            }
            logger.info("Title angelegt: " + title.getHfPlaylistIk());
        }
        
        //don't commit here as there might be other titles to add
    }

    /**
     * Create a new Title row
     * @return
     */
    private HfPlaylistPosViewRow createTitle() {
        DCIteratorBinding iter = UIUtilBean.getIterator("HfPlaylistPosOfPlaylistViewIterator");
        NavigatableRowIterator naviter = iter.getNavigatableRowIterator();

        Row currentRow = naviter.createRow();
        naviter.insertRow(currentRow);

        return (HfPlaylistPosViewRow) currentRow;
        //        }
    }

    /**
     * Handle selection event inside the listview
     * @param selectionEvent
     */
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

    /**
     * Setter.
     * @param trackViewList
     */
    public void setTrackViewList(RichListView trackViewList) {
        if (trackViewList != null) {
            this.trackViewList = ComponentReference.newUIComponentReference(trackViewList);
        } else {
            this.trackViewList = null;
        }
    }

    /**
     * Getter.
     * @return
     */
    public RichListView getTrackViewList() {
        if (this.trackViewList != null) {
            return (RichListView) trackViewList.getComponent();
        }

        return null;
    }

    public void handleCancelAction(ActionEvent actionEvent) {
        //Filmstrip anschalten
        //        _switchInlineMode("OFF");

        OperationBinding method = UIUtilBean.getOperationFromCurrentBindings("Rollback");
        if (method == null) {
            _utils.showMessage(ERROR, "Rollback nicht gefunden!");
        } else {
            method.execute();
            // Check for errors
            List<Exception> errors = method.getErrors();
            if (!errors.isEmpty()) {
                _utils.showMessage(ERROR, "Rollback fehlgeschlagen!");
                logger.severe("Error: " + errors.get(0).getMessage());
                errors.get(0).printStackTrace();
            }
        }

        return;
    }

    public String onCancel() {
        String ret = "back";
        return ret;
    }

    public void handleSaveAndClose(ActionEvent actionEvent) {
        commit();
        //Filmstrip anschalten
        //        _switchInlineMode("OFF");
        return;
    }

    private Boolean commit() {
        OperationBinding method = UIUtilBean.getOperationFromCurrentBindings("Commit");
        if (method == null) {
            _utils.showMessage(ERROR, "Commit nicht gefunden!");
            return Boolean.FALSE;
        } else {

            method.execute();
            // Check for errors
            List<Exception> errors = method.getErrors();
            if (!errors.isEmpty()) {
                logger.log(Level.SEVERE, "Commit fehlgeschlagen!", errors.get(0));
                return Boolean.FALSE;
            }
        }

        return Boolean.TRUE;
    }

    public String onSaveAndClose() {
        String ret = "back";
        return ret;
    }

    /**
     * Helper method to switch hte filmstrip on an off
     * @param mode mode to set [ON|OFF]
     */
    private void _switchInlineMode(String mode) {
        SessionState sessionState = (SessionState) _utils.getSessionScope().get("SessionState");
        if (mode.equals("ON")) {
            sessionState.setFilmStripShowHandle(false);
            sessionState.setFilmStripShowStrip("noShow");
        } else {
            sessionState.setFilmStripShowHandle(true);
            sessionState.setFilmStripShowStrip("");
        } //mode check
        //Initiate Refresh
        FacesContext fctx = FacesContext.getCurrentInstance();
        UIViewRoot vr = fctx.getViewRoot();
        RichCommandButton actionTrigger = (RichCommandButton) vr.findComponent("pt1:pt_refreshTrigger");
        ActionEvent actionEvent = new ActionEvent(actionTrigger);
        actionEvent.queue();
    } //_switchInlineMode

}
