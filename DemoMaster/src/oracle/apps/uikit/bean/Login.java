package oracle.apps.uikit.bean;

import javax.faces.application.Application;

import javax.faces.context.FacesContext;
import javax.faces.el.ValueBinding;

import oracle.adf.view.rich.component.rich.RichDocument;

import oracle.apps.uikit.UserInfo;
import oracle.apps.uikit.AppModuleImpl;

import javax.faces.component.html.HtmlCommandButton;
import javax.faces.component.html.HtmlOutputText;

import javax.faces.event.ActionEvent;

import javax.servlet.http.HttpServletRequest;

import javax.servlet.http.HttpServletResponse;

import oracle.adf.share.logging.ADFLogger;
import oracle.adf.view.rich.component.rich.RichForm;
import oracle.adf.view.rich.component.rich.input.RichInputText;
import oracle.adf.view.rich.component.rich.layout.RichDecorativeBox;
import oracle.adf.view.rich.component.rich.layout.RichGridCell;
import oracle.adf.view.rich.component.rich.layout.RichGridRow;
import oracle.adf.view.rich.component.rich.layout.RichPanelBox;
import oracle.adf.view.rich.component.rich.layout.RichPanelFormLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGridLayout;
import oracle.adf.view.rich.component.rich.layout.RichPanelGroupLayout;
import oracle.adf.view.rich.component.rich.output.RichOutputFormatted;
import oracle.adf.view.rich.component.rich.output.RichOutputText;

import oracle.jbo.ViewCriteria;
import oracle.jbo.ViewCriteriaManager;

import oracle.security.jps.internal.api.servlet.ServletAuthentication;

import org.apache.commons.lang3.StringUtils;



public class Login {

    private String lastName;
    private String password;
    private String loginmesssage;
    private String noteslable;

    /*=====================================*/

    private RichDocument d1;
    private RichPanelGroupLayout pgl2;
    private RichPanelBox pb1;
    private RichDecorativeBox db1;
    private RichPanelGridLayout pgl6;
    private RichGridRow gr1;
    private RichGridCell gc1;
    private RichPanelFormLayout pfl1;
    private RichForm f1;
    private RichForm f2;
    private RichInputText it1;
    private RichInputText it2;
    private HtmlOutputText ot1;
    private HtmlCommandButton cb1;
    private RichPanelFormLayout pfl2;
    private RichOutputText ot2;
    //private RichPanelGridLayout pgl6;
    private RichGridRow gr2;
    private RichGridCell gc2;
    private RichOutputFormatted of1;


    public void setD1(RichDocument d1) {
        this.d1 = d1;
    }

    public RichDocument getD1() {
        return d1;
    }


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
        /* 
         * definiere Faces context
         * definiere Application context
         * erstelle ein Value Binding für App context
         * Application module definieren mit dem Value Binding und den Faces Context
         * 
         **/
        FacesContext context = FacesContext.getCurrentInstance();
        try {
            Application app;
            app = context.getApplication();
            
            ValueBinding bind;
            bind = app.createValueBinding("#{data.AppModuleDataControl.dataProvider}");
            AppModuleImpl am = (AppModuleImpl) bind.getValue(context);
            
            // definiere User über die Methode Login in AppModule und die Klasse UserInfo 
            UserInfo user;
            user = am.Login(lastName, password);
            
                if (user != null) {
                  if (StringUtils.isNotBlank(lastName) && StringUtils.isNotBlank(password)) {
                
                    context.getExternalContext().getSessionMap().put("userKay", user);
                    password = null;
                    return "main1";
                }
            }
        } catch (Exception e) {
            // TODO: Add catch code
            e.printStackTrace();
        }

        setLoginmesssage("Username und das Passwort, stimmen nicht überein.");
        setNoteslable("");
        password = null;
        return null;
    }

    public void setPgl2(RichPanelGroupLayout pgl2) {
        this.pgl2 = pgl2;
    }

    public RichPanelGroupLayout getPgl2() {
        return pgl2;
    }

    public void setPb1(RichPanelBox pb1) {
        this.pb1 = pb1;
    }

    public RichPanelBox getPb1() {
        return pb1;
    }


    public void setDb1(RichDecorativeBox db1) {
        this.db1 = db1;
    }

    public RichDecorativeBox getDb1() {
        return db1;
    }

    public void setPgl6(RichPanelGridLayout pgl6) {
        this.pgl6 = pgl6;
    }

    public RichPanelGridLayout getPgl6() {
        return pgl6;
    }

    public void setGr1(RichGridRow gr1) {
        this.gr1 = gr1;
    }

    public RichGridRow getGr1() {
        return gr1;
    }

    public void setGc1(RichGridCell gc1) {
        this.gc1 = gc1;
    }

    public RichGridCell getGc1() {
        return gc1;
    }

    public void setPfl1(RichPanelFormLayout pfl1) {
        this.pfl1 = pfl1;
    }

    public RichPanelFormLayout getPfl1() {
        return pfl1;
    }

    public void setF1(RichForm f1) {
        this.f1 = f1;
    }

    public RichForm getF1() {
        return f1;
    }

    public void setF2(RichForm f2) {
        this.f2 = f2;
    }

    public RichForm getF2() {
        return f2;
    }

    public void setIt1(RichInputText it1) {
        this.it1 = it1;
    }

    public RichInputText getIt1() {
        return it1;
    }

    public void setIt2(RichInputText it2) {
        this.it2 = it2;
    }

    public RichInputText getIt2() {
        return it2;
    }

    public void setOt1(HtmlOutputText ot1) {
        this.ot1 = ot1;
    }

    public HtmlOutputText getOt1() {
        return ot1;
    }


    public void setCb1(HtmlCommandButton cb1) {
        this.cb1 = cb1;
    }

    public HtmlCommandButton getCb1() {
        return cb1;
    }

    public void setPfl2(RichPanelFormLayout pfl2) {
        this.pfl2 = pfl2;
    }

    public RichPanelFormLayout getPfl2() {
        return pfl2;
    }


    public void setOt2(RichOutputText ot2) {
        this.ot2 = ot2;
    }

    public RichOutputText getOt2() {
        return ot2;
    }

   
       public void setGr2(RichGridRow gr2) {
        this.gr2 = gr2;
    }

    public RichGridRow getGr2() {
        return gr2;
    }

    public void setGc2(RichGridCell gc2) {
        this.gc2 = gc2;
    }

    public RichGridCell getGc2() {
        return gc2;
    }


    public void setOf1(RichOutputFormatted of1) {
        this.of1 = of1;
    }

    public RichOutputFormatted getOf1() {
        return of1;
    }
}
