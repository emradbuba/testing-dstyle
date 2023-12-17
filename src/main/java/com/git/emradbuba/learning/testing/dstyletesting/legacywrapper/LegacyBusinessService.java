package com.git.emradbuba.learning.testing.dstyletesting.legacywrapper;

public class LegacyBusinessService {
    public static boolean runLongBusinessLogic1() {
        try {
            Thread.sleep(5000L);
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }

    public static boolean runLongBusinessLogic2() {
        try {
            Thread.sleep(5000L);
            return true;
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
