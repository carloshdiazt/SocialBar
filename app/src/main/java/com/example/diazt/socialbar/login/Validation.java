package com.example.diazt.socialbar.login;

import com.google.firebase.auth.FirebaseAuth;

/**
 * Created by diazt on 02-04-2017.
 */

public class Validation {

    private LoginCallback callback;

    public Validation(LoginCallback callback) {
        this.callback = callback;
    }

    public void begin() {
        if (FirebaseAuth.getInstance().getCurrentUser() != null) {
            callback.logged();
        } else {
            callback.sign();
        }
    }
}
