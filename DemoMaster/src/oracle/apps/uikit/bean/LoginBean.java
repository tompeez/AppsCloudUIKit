package oracle.apps.uikit.bean;

import java.io.Serializable;

import java.util.List;
import java.util.Map;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.event.ActionEvent;

import oracle.adf.controller.ControllerContext;
import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.apps.uikit.common.bean.UIUtilBean;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class LoginBean implements Serializable {
    @SuppressWarnings("compatibility:2174888454500577010")
    private static final long serialVersionUID = 1L;
    private static final String VIEWID_WELCOMEPAGE = "Welcome";
    private static final String MSG_METHODNOTFOUND =
        "Die Anmelderoutine konnte aktuell nicht ausgeführt werden! Bitte versuchen Sie es zu einem späteren Zeitpunkt noch einmal.";
    private static final String MSG_ERROR = "Fehler";
    private static final String MSG_NAMEPWDWRONG =
        "Es konnte keine Anmeldung mit den von Ihnen angegebenen Werten gefunden werden";
    private static final String PWD_NOT_MATCH = "Die Passwörter stimmen nicht überein!";
    private static final String REGISTER_FAILED = "Der Datensatz konnte nciht gespeichert werden!";

    public LoginBean() {
        super();
    }


    public void onAnmelden(ActionEvent actionEvent) {
        // GET A METHOD FROM PAGEDEF AND EXECUTE IT
        // get the binding container
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        FacesContext facesContext = FacesContext.getCurrentInstance();
        UIComponent ui = (UIComponent) actionEvent.getSource();

        // get an ADF attributevalue from the ADF page definitions
        AttributeBinding attr = UIUtilBean.getAttributeFromCurrntBinding("userPassword1");
        String pwd = (String) attr.getInputValue();
        ADFContext adfCtx = ADFContext.getCurrent();
        // Get the scope variables
        Map<String, Object> sessionParamsVar2 = adfCtx.getSessionScope();
        String user = (String) sessionParamsVar2.get("loggedInUserName");

        // get an Action or MethodAction
        OperationBinding method = UIUtilBean.getOperationFromCurrentBindings("ExecuteWithParams");
        if (method == null) {
            // handle method not found error...
            UIUtilBean.reportMessageAsExceptiom(MSG_METHODNOTFOUND);
        }
        // if there are parameters to set...
        Map paramsMap = method.getParamsMap();
        paramsMap.put("bindUsername", user);
        paramsMap.put("bindPassword", pwd);
        // execute the method
        method.execute();
        List<Exception> errors = (List<Exception>) method.getErrors();
        if (!errors.isEmpty()) {
            // handle errors here errors is a list of exceptions!
            errors.get(0).printStackTrace();
        }

        //remove password
        attr.setInputValue("");

        // no error resume normal work
        // Get the data from an ADF tree or table
        DCBindingContainer dcBindings = (DCBindingContainer) bindings;
        // Get a attribute value of the current row of iterator
        DCIteratorBinding iterBind = (DCIteratorBinding) dcBindings.get("HfUserLoginViewIterator");

        Row currentRow = iterBind.getCurrentRow();
        if (currentRow != null) {
            //            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userKey", user);
            String userLongName = (String) currentRow.getAttribute("Longname");
            String userName = (String) currentRow.getAttribute("Username");
            Long userId = (Long) currentRow.getAttribute("IntKey");
            sessionParamsVar2.put("loggedInUserName", userLongName);
            sessionParamsVar2.put("loggedInUserId", userId);
            // Navigation zur Welcome Seite
            ControllerContext ccontext = ControllerContext.getInstance();
            //set the viewId – the name of the view activity to
            //go to - to display
            String viewId = VIEWID_WELCOMEPAGE;
            ccontext.getCurrentViewPort().setViewId(viewId);
            return;
        }

        // Keinen passenden Login gefunden -> Fehlermeldung anzeigen
        FacesMessage msg = new FacesMessage(FacesMessage.SEVERITY_ERROR, MSG_ERROR, MSG_NAMEPWDWRONG);
        facesContext.addMessage(ui.getClientId(facesContext), msg);
    }

    public void handleRegisterUserSave(ActionEvent actionEvent) {
        FacesContext facesContext = FacesContext.getCurrentInstance();

        AttributeBinding ab1 = UIUtilBean.getAttributeFromCurrntBinding("Password");
        String pwd = (String) ab1.getInputValue();
        AttributeBinding ab2 = UIUtilBean.getAttributeFromCurrntBinding("passwd21");
        String pwd2 = (String) ab2.getInputValue();
        if (!pwd.equals(pwd2)) {
            // Keinen passenden Login gefunden -> Fehlermeldung anzeigen
            UIUtilBean.reportMessageAsExceptiom(PWD_NOT_MATCH);
            return;
        }

        OperationBinding method = UIUtilBean.getOperationFromCurrentBindings("Commit");
        if (method == null) {
            // handle method not found error...
            UIUtilBean.reportMessageAsExceptiom(MSG_METHODNOTFOUND);
            return;
        }

        method.execute();
        List<Exception> errors = (List<Exception>) method.getErrors();
        if (!errors.isEmpty()) {
            // handle errors here errors is a list of exceptions!
            errors.get(0).printStackTrace();
            UIUtilBean.reportMessageAsExceptiom(REGISTER_FAILED);
            return;
        }
    }


}
