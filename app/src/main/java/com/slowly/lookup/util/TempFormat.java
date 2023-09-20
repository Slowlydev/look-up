package com.slowly.lookup.util;

import java.util.Locale;

public class TempFormat {
    public static String format(Double temperature) {
        return String.format(Locale.getDefault(),"%.0fº", temperature);
    }
}
