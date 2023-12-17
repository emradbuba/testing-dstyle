package com.git.emradbuba.learning.testing.dstyletesting.legacywrapper;

import org.springframework.stereotype.Service;

@Service
public class LegacyBusinessServiceWrapper {

    public boolean runLongBusinessLogic1() {
        return LegacyBusinessService.runLongBusinessLogic1();
    }

    public boolean runLongBusinessLogic2() {
        return LegacyBusinessService.runLongBusinessLogic2();
    }
}
