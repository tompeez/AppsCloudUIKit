package oracle.apps.uikit.bean;

import java.util.List;
import java.util.Map;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;
import oracle.adf.share.ADFContext;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.Row;

public class LoginBean {
    public LoginBean() {
        super();
    }

    public String Login_Action() {
        // GET A METHOD FROM PAGEDEF AND EXECUTE IT
        // get the binding container
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();


        // get an ADF attributevalue from the ADF page definitions
        AttributeBinding attr = (AttributeBinding) bindings.getControlBinding("userPassword1");
        String pwd = (String) attr.getInputValue();
        ADFContext adfCtx = ADFContext.getCurrent();
        // Get the scope variables
        Map<String, Object> sessionParamsVar2 = adfCtx.getSessionScope();
        String user = (String) sessionParamsVar2.get("loggedInUserName");

        // get an Action or MethodAction
        OperationBinding method = bindings.getOperationBinding("ExecuteWithParams");
        if (method == null) {
            // handle method not found error...

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
            sessionParamsVar2.put("loggedInUserName", userLongName);
            return "loggedIn";
        }
        //        setLoginmesssage("Username und das Passwort, stimmen nicht überein.");
        //        setNoteslable("");
        //        password = null;
        return null;
    }
}
