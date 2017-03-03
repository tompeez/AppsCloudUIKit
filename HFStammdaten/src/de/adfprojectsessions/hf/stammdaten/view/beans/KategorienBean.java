package de.adfprojectsessions.hf.stammdaten.view.beans;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.model.BindingContext;
import oracle.adf.share.ADFContext;
import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.context.AdfFacesContext;

import oracle.apps.uikit.common.declarativeComponents.CardViewListViewDCComponent;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import org.apache.myfaces.trinidad.util.ComponentReference;

public class KategorienBean {
    private static ADFLogger _logger = ADFLogger.createADFLogger(KategorienBean.class);

    String nameSearch;
    private ComponentReference cardViewListView;

    public void setNameSearch(String nameSearch) {
        this.nameSearch = nameSearch;
    }

    public String getNameSearch() {
        return nameSearch;
    }

    public KategorienBean() {
    }

    public void handleNameSearch(ActionEvent actionEvent) {
        // GET A METHOD FROM PAGEDEF AND EXECUTE IT
        // get the binding container
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        // get an Action or MethodAction
        OperationBinding method = bindings.getOperationBinding("searchDescriptionByUserId");
        if (method == null) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, "Suchmethode nicht gefunden!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
        }
        ADFContext adfCtx = ADFContext.getCurrent();
        // Get the scope variables
        Map<String, Object> sessionParamsVar2 = adfCtx.getSessionScope();
        Map<String, Object> pageflowScopeVar2 = adfCtx.getPageFlowScope();
        //#{pageFlowScope.KategorienBean.nameSearch}
        // if there are parameters to set...
        Map paramsMap = method.getParamsMap();
        paramsMap.put("searchTerm", this.getNameSearch());
        paramsMap.put("userId", sessionParamsVar2.get("loggedInUserId"));
        // execute the method
        method.execute();
        List<Exception> errors = method.getErrors();
        if (!errors.isEmpty()) {
            FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_WARN, "Suche fehlgeschlagen!", "");
            FacesContext.getCurrentInstance().addMessage(null, msg);
            errors.get(0).printStackTrace();
        }
        // PPR refresh a jsf component
        AdfFacesContext.getCurrentInstance().addPartialTarget(getCardViewListView());
    }

    public void cancelSelectionMode(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void handleCreateKategorie(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void handleKategorieSelection(ActionEvent actionEvent) {
        // Add event code here...
    }

    public String handleCustomTitleAction() {
        // Add event code here...
        return null;
    }

    public void handleEditKategorie(ActionEvent actionEvent) {
        // Add event code here...
    }

    public void setCardViewListView(CardViewListViewDCComponent cardViewListView) {
        if (cardViewListView != null) {
            this.cardViewListView = ComponentReference.newUIComponentReference(cardViewListView);
        } else {
            this.cardViewListView = null;
        }
    }

    public CardViewListViewDCComponent getCardViewListView() {
        if (this.cardViewListView != null) {
            return (CardViewListViewDCComponent) cardViewListView.getComponent();
        }

        return null;
    }
}
