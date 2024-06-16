package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

class ClientUtilsTest {

    @Test
    void isAdmin() {
        preparePhonePreference();
        Assertions.assertFalse(ClientUtils.isAdmin());
    }

    private void preparePhonePreference() {
        Preferences userPreferences = Preferences.userRoot();
        try {
            userPreferences.clear();
            userPreferences.put("phone", "126459786");
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }
}