package pl.bobi.utils;

import pl.bobi.BobiSCB;

public class TranslateItemName {

    public static String translateString(String string) {
        return BobiSCB.fileConfiguration.getString(string);
    }
}
