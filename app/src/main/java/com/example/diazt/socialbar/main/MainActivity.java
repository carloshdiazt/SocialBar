package com.example.diazt.socialbar.main;

import android.content.Intent;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;

import android.os.Bundle;

import com.example.diazt.socialbar.R;
import com.example.diazt.socialbar.adapter.ActivityLoged;
import com.example.diazt.socialbar.login.LoginCallback;
import com.example.diazt.socialbar.login.Validation;
import com.firebase.ui.auth.AuthUI;
import com.firebase.ui.auth.ResultCodes;

import java.util.Arrays;

public class MainActivity extends AppCompatActivity implements LoginCallback {


    private static final int RC_SIGN_IN = 555;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.opening_login);
        new Validation(this).begin();

    }

    @Override
    public void logged() {
        startActivity(new Intent(this, ActivityLoged.class));

    }

    @Override
    public void sign() {
        startActivityForResult(
                AuthUI.getInstance()
                        .createSignInIntentBuilder()
                        .setProviders(Arrays.asList(new AuthUI.IdpConfig.Builder(AuthUI.EMAIL_PROVIDER).build()))
                        .build(),
                RC_SIGN_IN);

    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (RC_SIGN_IN == requestCode) {
            if (ResultCodes.OK == resultCode) {
                logged();
            }
        }
    }


}
