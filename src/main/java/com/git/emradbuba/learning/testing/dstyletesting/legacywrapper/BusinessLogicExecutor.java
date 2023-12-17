package com.git.emradbuba.learning.testing.dstyletesting.legacywrapper;

import org.springframework.stereotype.Component;

@Component
public class BusinessLogicExecutor {

    private LegacyBusinessServiceWrapper legacyBusinessServiceWrapper;

    public BusinessLogicExecutor(LegacyBusinessServiceWrapper legacyBusinessServiceWrapper) {
        this.legacyBusinessServiceWrapper = legacyBusinessServiceWrapper;
    }

    public boolean performLongRunningBusinessLogics() {
        //  return LegacyStaticBusinessMethods.runLongBusinessLogic1()
        //          && LegacyStaticBusinessMethods.runLongBusinessLogic2();

        return legacyBusinessServiceWrapper.runLongBusinessLogic1()
                && legacyBusinessServiceWrapper.runLongBusinessLogic2();
    }
}
