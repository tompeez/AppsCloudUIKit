package oracle.apps.uikit.common.bean;

import java.io.Serializable;

import javax.el.ELContext;
import javax.el.ExpressionFactory;
import javax.el.ValueExpression;

import javax.faces.application.Application;
import javax.faces.context.FacesContext;

import oracle.adf.model.BindingContext;
import oracle.adf.model.binding.DCBindingContainer;
import oracle.adf.model.binding.DCIteratorBinding;

import oracle.binding.AttributeBinding;
import oracle.binding.BindingContainer;
import oracle.binding.OperationBinding;

import oracle.jbo.JboException;

public class UIUtilBean implements Serializable {

    private static final long serialVersionUID = 1L;

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

    //Evaluate EL expression like "#{xxx}"
    public static Object evaluateEL(String elString) {
        FacesContext _facesCtx = FacesContext.getCurrentInstance();
        Application app = _facesCtx.getApplication();
        ExpressionFactory elFactory = app.getExpressionFactory();
        ELContext elContext = _facesCtx.getELContext();
        ValueExpression valExp = elFactory.createValueExpression(elContext, elString, Object.class);
        return valExp.getValue(elContext);
    } //evaluateEL

    /**
     * Gets an iterator binding from the current binding context.
     * @param iteratorName
     * @return iterator binding
     */
    public static DCIteratorBinding getIterator(String iteratorName) {
        if (iteratorName == null) {
            return null;
        }
        // Get the data from an ADF tree or table
        DCBindingContainer dcBindings = (DCBindingContainer) BindingContext.getCurrent().getCurrentBindingsEntry();
        // Get a attribute value of the current row of iterator
        DCIteratorBinding iterBind = (DCIteratorBinding) dcBindings.get(iteratorName);
        return iterBind;
    }
}
