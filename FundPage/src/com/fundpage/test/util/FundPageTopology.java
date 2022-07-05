package com.fundpage.test.util;

public final class FundPageTopology {
    
    private static final String HOME_URL = "https://fundpage.com";
    
    private static final String WEALTH_FORMULA_PAGE_URL = "/Home/WealthFormula";

    public static String getHomeUrl() {
        return HOME_URL;
    }

    public static String getWealthFormulaPageUrl() {
        return getHomeUrl() + WEALTH_FORMULA_PAGE_URL;
    }

}
