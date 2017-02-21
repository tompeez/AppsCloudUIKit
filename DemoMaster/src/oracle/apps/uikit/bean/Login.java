package oracle.apps.uikit.bean;

import java.util.List;
import java.util.Map;

import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;

import oracle.apps.uikit.UserInfo;

import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

public class Login {

    private String lastName;
    private String password;
    private String loginmesssage;
    private String noteslable;

    public void setPassword(String password) {
        this.password = password;
    }

    public String getPassword() {
        return password;
    }


    public void setLoginmesssage(String loginmesssage) {
        this.loginmesssage = loginmesssage;
    }

    public String getLoginmesssage() {
        return loginmesssage;
    }

    public void setNoteslable(String noteslable) {
        this.noteslable = noteslable;
    }

    public String getNoteslable() {
        return noteslable;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getLastName() {
        return lastName;
    }

    public String Login_Action() {
        // GET A METHOD FROM PAGEDEF AND EXECUTE IT
        // get the binding container
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        // get an Action or MethodAction
        OperationBinding method = bindings.getOperationBinding("Login");
        if (method == null) {
            // handle method not found error...

        }

        // if there are parameters to set...
        Map paramsMap = method.getParamsMap();
        paramsMap.put("UserLastName", this.lastName);
        paramsMap.put("passwort", this.password);
        // execute the method
        method.execute();
        List<Exception> errors = (List<Exception>) method.getErrors();
        if (!errors.isEmpty()) {
            // handle errors here errors is a list of exceptions!
            errors.get(0).printStackTrace();
        }
        // no error resume normal work
        Object object = method.getResult();
        UserInfo user = (UserInfo) object;
        if (user != null) {
            FacesContext.getCurrentInstance().getExternalContext().getSessionMap().put("userKey", user);
            password = null;
            return "main1";
        }
        setLoginmesssage("Username und das Passwort, stimmen nicht �berein.");
        setNoteslable("");
        password = null;
        return null;
    }


}
