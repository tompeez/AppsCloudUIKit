package oracle.apps.uikit.common.bean;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.JboException;

public class UIUtilBean {
    public UIUtilBean() {
        super();
    }

    /**
     * Helper mehtod to get an operation bindung from the current bindings.
     * @param methodName name of the method
     * @return OperationBinding of hte method or null if hte method is not found
     */
    public static OperationBinding getOperationFromCurrentBindings(String methodName) {
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
    public static AttributeBinding getAttributeFromCurrntBinding(String attibuteName) {
        // get the binding container
        BindingContainer bindings = BindingContext.getCurrent().getCurrentBindingsEntry();
        // get an ADF attributevalue from the ADF page definitions
        AttributeBinding attr = (AttributeBinding) bindings.getControlBinding(attibuteName);
        return attr;
    }

    /**
     * Helper methos which added a JboException which will show on the UI
     * @param msg message to show
     */
    public static void reportMessageAsExceptiom(String msg) {
        JboException ex = new JboException(msg);
        BindingContext bctx = BindingContext.getCurrent();
        ((DCBindingContainer) bctx.getCurrentBindingsEntry()).reportException(ex);
    }
}
