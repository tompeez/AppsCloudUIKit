package oracle.apps.uikit.common;

import oracle.apps.uikit.UserInfo;

import oracle.jbo.ApplicationModule;
// ---------------------------------------------------------------------
// ---    File generated by Oracle ADF Business Components Design Time.
// ---    Tue Feb 21 20:58:41 CET 2017
// ---------------------------------------------------------------------
public interface AppModule extends ApplicationModule {
    UserInfo Login(String UserLastName, String password);
}

