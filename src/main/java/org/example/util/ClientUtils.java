package org.example.util;

import java.util.Optional;
import java.util.prefs.Preferences;

public class ClientUtils {

    public static boolean isAdmin() {
        String phoneNumber = getPhoneNumber();
        if (phoneNumber != null) {
            return phoneNumber.toLowerCase().contains("admin");
        }
        return false;
    }

    public static String getPhoneNumber() {
        return Preferences.userRoot().get("phone", null);
    }
}
