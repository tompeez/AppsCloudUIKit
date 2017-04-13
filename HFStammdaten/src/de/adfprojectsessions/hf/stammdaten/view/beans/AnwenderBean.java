package de.adfprojectsessions.hf.stammdaten.view.beans;

import java.io.Serializable;

import java.util.List;

import javax.faces.component.UIViewRoot;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.nav.RichCommandButton;

import oracle.apps.uikit.common.bean.UIUtilBean;
import oracle.apps.uikit.common.bean.UtilsBean;
import oracle.apps.uikit.memoryCache.SessionState;

import oracle.binding.OperationBinding;

public class AnwenderBean implements Serializable {
    @SuppressWarnings("compatibility:-7083841733801711934")
    private static final long serialVersionUID = 1L;
    private static ADFLogger _logger = ADFLogger.createADFLogger(AnwenderBean.class);
    private static final String MSG_METHODNOTFOUND =
        "Die Änderungen konnte aktuell nicht ausgeführt werden! Bitte versuchen Sie es zu einem späteren Zeitpunkt noch einmal.";
    private static final String MSG_ERROR = "Fehler";
    private static final String PWD_NOT_MATCH = "Die Passwörter stimmen nicht überein!";
    private static final String REGISTER_FAILED = "Der Datensatz konnte nciht gespeichert werden!";
    private static final String ERROR = "ERROR";
    // utility bean
    private UtilsBean _utils = new UtilsBean();

    public AnwenderBean() {
    }

    public String onEditCancel() {
        String ret = "cancel";
        return ret;
    }

    public void handleCancelEdit(ActionEvent actionEvent) {
        //Filmstrip anschalten
        _switchInlineMode("OFF");

        OperationBinding method = UIUtilBean.getOperationFromCurrentBindings("Rollback");
        if (method == null) {
            _utils.showMessage(ERROR, "Rollback nicht gefunden!");
        } else {
            method.execute();
            // Check for errors
            List<Exception> errors = method.getErrors();
            if (!errors.isEmpty()) {
                _utils.showMessage(ERROR, "Rollback fehlgeschlagen!");
                errors.get(0).printStackTrace();
            }
        }
    }

    public String onSave() {
        String ret = "commit";
        return ret;
    }

    public void handleSave(ActionEvent actionEvent) {
        OperationBinding method = UIUtilBean.getOperationFromCurrentBindings("Commit");
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

    public void handleEditAnwender(ActionEvent actionEvent) {
        _switchInlineMode("ON");
    }
}
