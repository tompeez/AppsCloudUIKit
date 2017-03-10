package de.adfprojectsessions.hf.playlisten.view.beans;

import java.util.List;
import java.util.Map;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.apps.uikit.common.bean.CardViewListViewStateBean;
import oracle.apps.uikit.common.bean.UtilsBean;
import oracle.apps.uikit.common.declarativeComponents.CardViewListViewDCComponent;
import oracle.apps.uikit.memoryCache.SessionState;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class AllPlaylistenBean {

    private static ADFLogger _logger = ADFLogger.createADFLogger(AllPlaylistenBean.class);
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

    /**
     * C'tor
     */
    public AllPlaylistenBean() {
        currentViewState = new CardViewListViewStateBean();
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
     * handle search event.
     * @param actionEvent event which triggered the search
     */
    public void handleNameSearch(ActionEvent actionEvent) {
        OperationBinding method = getOperationFromCurrentBindings("searchDescriptionByUserId");
        if (method == null) {
            _utils.showMessage(ERROR, "Suchmethode nicht gefunden!");
            return;
        }
        ADFContext adfCtx = ADFContext.getCurrent();
        // Get the scope variables
        Long userId = (Long) _utils.getSessionScope().get("loggedInUserId");
        // if there are parameters to set...
        Map paramsMap = method.getParamsMap();
        paramsMap.put("searchTerm", this.getNameSearch());
        paramsMap.put("userId", userId);
        // execute the method
        method.execute();
        List<Exception> errors = method.getErrors();
        if (!errors.isEmpty()) {
            _utils.showMessage(ERROR, "Suche fehlgeschlagen!");
            errors.get(0).printStackTrace();
        }

        // PPR refresh a jsf component: we need to do this in java as we can't ppr a declarative comonent
        CardViewListViewDCComponent comp = getCardViewListView();
        AdfFacesContext.getCurrentInstance().addPartialTarget(comp);
    }

    /**
     * Action for click in the card title
     * @return next navigation target
     */
    public String handleCustomTitleAction() {
        String ret = null;
        //only navigate if a current row is set
        if (getSelectedPlayliste() != null) {
            ret = "edit";
            //Filmstrip abschalten
            _switchInlineMode("ON");
        }

        return ret;
    }

    /**
     * Prepare current selected kategorie for edit.
     * The current selected key of hte kategorie is red from a clientattribute named 'ID'
     * @param actionEvent event which triggers the action
     */
    public void handleEditPlayliste(ActionEvent actionEvent) {
        // beim click wurde ein Clien Attribute ID mit dem Key der geclickten Überschrift zur componente hinzugefügt
        Map<String, Object> attributes = actionEvent.getComponent().getAttributes();
        Long plId = (Long) attributes.get("ID");
        // diesen Wert erst mal lokal speichern
        setSelectedPlayliste(plId);
        _logger.info("Selected Playliste:" + plId);

        if (plId == null) {
            _utils.showMessage(ERROR, "ID of selcted row not found!");
            return;
        }

        // jetzt mit diesem Key dir Current Row setzten
        OperationBinding method = getOperationFromCurrentBindings("setCurrentRowWithKeyValue");
        if (method == null) {
            _utils.showMessage(ERROR, "Methode setCurrentRowWithKeyValue nicht gefunden!");
            //da die currentrow nicht geetzt werden konnte, den key wieder löschen
            setSelectedPlayliste(null);
            return;
        }
        // set method parameter
        Map map = method.getParamsMap();
        map.put("rowKey", plId);
        method.execute();
        
        //check for errors
        List<Exception> errors = method.getErrors();
        if (!errors.isEmpty()) {
            _utils.showMessage(ERROR, "Methode setCurrentRowWithKeyValue fehlgeschlagen!");
            errors.get(0).printStackTrace();
            //da die currentrow nicht geetzt werden konnte, den key wieder löschen
            setSelectedPlayliste(null);
            return;
        }
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
     * Action after click on Cancel.
     * @return next navigation target
     */
    public String onCreateEditCancel() {
        String ret = "doneCreateEdit";
        return ret;
    }
    
    /**
     * Handle cancel of Create new Card row event.
     * @param actionEvent event which triggered the cancel event 
     */
    public void handleCreateEditCancel(ActionEvent actionEvent) {
        //Filmstrip anschalten
        _switchInlineMode("OFF");

        OperationBinding method = getOperationFromCurrentBindings("Rollback");
        if (method == null) {
            _utils.showMessage(ERROR, "Rollback nicht gefunden!");
        } else {
            method.execute();
            // Check for errors
            List<Exception> errors = method.getErrors();
            if (!errors.isEmpty()) {
                _utils.showMessage(ERROR, "Rollback fehlgeschlagen!");
                _logger.severe("Error: "+ errors.get(0).getMessage());
                errors.get(0).printStackTrace();
            }
        }

        return;
    }

    /**
     * Action  on click on save and close.
     * @return next navigation target
     */
    public String onSaveAndClose() {
        String ret = "doneCreateEdit";
        return ret;
    }

    /**
     * Handle save and close event.
     * @param actionEvent which triggered the save event
     */
    public void handleSaveAndClose(ActionEvent actionEvent) {
        OperationBinding method = getOperationFromCurrentBindings("Commit");
        if (method == null) {
            _utils.showMessage(ERROR, "Commit nicht gefunden!");
        } else {

            method.execute();
            // Check for errors
            List<Exception> errors = method.getErrors();
            if (!errors.isEmpty()) {
                _utils.showMessage(ERROR, "Commit fehlgeschlagen!");
                errors.get(0).printStackTrace();
            }
        }
        //Filmstrip anschalten
        _switchInlineMode("OFF");
        return;
    }

    /**
     * Handle create playliste event.
     * @param actionEvent which triggered the create event
     */
    public void handleCreatePlayliste(ActionEvent actionEvent) {
        //Filmstrip abschalten
        _switchInlineMode("ON");
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
    
    /**
     * Helper mehtod to get an operation bindung from the current bindings.
     * @param methodName name of the method
     * @return OperationBinding of hte method or null if hte method is not found
     */
    private OperationBinding getOperationFromCurrentBindings(String methodName) {
        // GET A METHOD FROM PAGEDEF AND EXECUTE IT
        // get the binding container
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        // get an Action or MethodAction
        OperationBinding method = bindings.getOperationBinding(methodName);
        return method;
    }

    /**
     * Helper method to gen an attribute binding fro nthe current bindings.
     * @param attibuteName name of hte attribute 
     * @return AttributeBinding of hte attribute or null if hte attribute is not found
     */
    private AttributeBinding getAttributeFromCurrntBinding(String attibuteName) {
        // get the binding container
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        // get an ADF attributevalue from the ADF page definitions
        AttributeBinding attr = (AttributeBinding) bindings.getControlBinding("attibuteName");
        return attr;
    }    

    /**
     * Setter.
     * @param selectedPlayliste
     */
    public void setSelectedPlayliste(Long selectedPlayliste) {
        this._selectedPlayliste = selectedPlayliste;
    }

    /**
     * Getter.
     * @return
     */
    public Long getSelectedPlayliste() {
        return _selectedPlayliste;
    }
}
