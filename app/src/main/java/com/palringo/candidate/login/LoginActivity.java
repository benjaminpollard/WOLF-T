package com.palringo.candidate.login;

import android.content.Intent;
import android.os.Handler;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Toast;

import com.palringo.candidate.MainActivity;
import com.palringo.candidate.R;

import org.json.JSONException;
import org.json.JSONObject;

import java.util.Random;

public class LoginActivity extends AppCompatActivity {

    public static final String TAG = "LoginActivity";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
    }

    public void emailLogin(View view) {
        login("myEmail@dummy.com", "myPassword", false, null, null,
                new OnLoginResultsListener() {
                    @Override
                    public void loginSuccess() {
                        Log.d(TAG, "logged in successfully. progressing to main");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void loginFailed() {
                        Log.d(TAG, "email login failed");
                        Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT)
                                .show();
                    }
                });

    }

    public void facebookLogin(View view) {
        login(null, null, true, "dummyAccessToken", "dummyFbId",
                new OnLoginResultsListener() {
                    @Override
                    public void loginSuccess() {
                        Log.d(TAG, "logged in successfully. progressing to main");
                        startActivity(new Intent(LoginActivity.this, MainActivity.class));
                    }

                    @Override
                    public void loginFailed() {
                        Log.d(TAG, "facebook login failed");
                        Toast.makeText(LoginActivity.this, R.string.login_failed, Toast.LENGTH_SHORT)
                                .show();
                    }
                });
    }

    /**
     * The server documentation for using it's login command has the following parameters <br>
     *     username (optional): the email of the user <br>
     *     password (optional): the password of the user <<br>
     *     isFacebook (optional): true if doing a facebook login. Defaults to false <br>
     *     fbAccessToken (optional): the accessToken for facebook login <br>
     *     fbUserId (optional): the userId for facebook login <br>
     *     <br>
     *     either username & password or isFacebook = true with fbAccessToken & fbUserId non-null
     *     values must be provided
     *
     *
     * @param username parameter for the server command
     * @param password parameter for the server command
     * @param isFacebook parameter for the server command
     * @param fbAccessToken parameter for the server command
     * @param fbUserId parameter for the server command
     * @param listener a callback for results of the login process
     */
    private void login(@Nullable final String username, @Nullable final String password,
                       boolean isFacebook, @Nullable String fbAccessToken, @Nullable String fbUserId,
                       @NonNull final OnLoginResultsListener listener) throws IllegalArgumentException{

        JSONObject json = new JSONObject();
        try {
            if (isFacebook) {
                if (fbAccessToken != null && fbUserId != null) {
                    json.put("isFacebook", true);
                    json.putOpt("fbUserId", fbUserId);
                    json.putOpt("fbAccessToken", fbAccessToken);
                } else {
                    throw new IllegalArgumentException("Invalid login parameters");
                }
            } else {
                if (username != null && password != null) {
                    json.putOpt("username", username);
                    json.putOpt("password", password);
                } else {
                    throw new IllegalArgumentException("Invalid login parameters");
                }
            }
        } catch (JSONException ex) {
            Log.d(TAG, "JSON error on constructing login command");
            listener.loginFailed();
            return;
        }

        //emulate a 5% failure to login
        if(new Random().nextFloat() > 0.05f) {
            listener.loginSuccess();
        } else {
            new Handler().postDelayed(
                    new Runnable() {
                        @Override
                        public void run() {
                            listener.loginFailed();
                        }
                    }, 5000);
        }
    }
}
