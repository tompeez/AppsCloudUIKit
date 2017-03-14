package de.adfprojectsesions.hf.model.adfbc.views.utils;

import oracle.adf.share.logging.ADFLogger;

import oracle.jbo.Variable;
import oracle.jbo.VariableValueManager;
import oracle.jbo.server.ViewDefImpl;
import oracle.jbo.server.ViewObjectImpl;

public class AdfProjectSessionsBaseViewImpl extends ViewObjectImpl {
    private static ADFLogger _logger = ADFLogger.createADFLogger(AdfProjectSessionsBaseViewImpl.class);
    
    public AdfProjectSessionsBaseViewImpl(String string, ViewDefImpl viewDefImpl) {
        super(string, viewDefImpl);
    }

    public AdfProjectSessionsBaseViewImpl() {
        super();
    }
    
    protected void dumpQueryAndParameters()
    {
        // get the query in it's current state
        String lQuery = getQuery();
        //get Valriables
        VariableValueManager lEnsureVariableManager = ensureVariableManager();
        Variable[] lVariables = lEnsureVariableManager.getVariables();
        int lCount = lEnsureVariableManager.getVariableCount();
        // Dump query
        _logger.info("---query--- " + lQuery);
        // if variables found dump them
        if (lCount > 0)
        {
            _logger.info("---Variables:");
            for (int ii = 0; ii < lCount; ii++)
            {
                Object lObject = lEnsureVariableManager.getVariableValue(lVariables[ii]);
                _logger.info("  --- Name: " + lVariables[ii].getName() + " Value: " +
                                   (lObject != null ?  lObject.toString() : "null"));
            }
        }
    }
}
