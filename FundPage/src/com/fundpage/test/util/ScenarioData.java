package com.fundpage.test.util;

public final class ScenarioData {
    
    private static final String ARTIFACT_DIR = "src\\com\\fundpage\\test\\util\\";
    private static final String WF_SEARCH_PROPRITIES_FILE = "WealthFormulaSearchCondition.properties";

    public static String getArtifactDir() {
        return ARTIFACT_DIR;
    }

    public static String getWealthFormulaSearchPropritiesFile() {
        return getArtifactDir() + WF_SEARCH_PROPRITIES_FILE;
    }

}
