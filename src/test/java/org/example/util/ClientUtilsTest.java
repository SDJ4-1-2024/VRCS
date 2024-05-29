package org.example.util;

import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.util.prefs.BackingStoreException;
import java.util.prefs.Preferences;

import static org.junit.jupiter.api.Assertions.*;

class ClientUtilsTest {

    @Test
    void isAdmin() {
        clearPhonePreference();
        Assertions.assertFalse(ClientUtils.isAdmin());
    }

    private void clearPhonePreference() {
        Preferences userPreferences = Preferences.userRoot();
        try {
            userPreferences.clear();
        } catch (BackingStoreException e) {
            throw new RuntimeException(e);
        }
    }
}