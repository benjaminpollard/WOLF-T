package com.palringo.candidate.login.views;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.idea.group.iplato.services.LoginService;
import com.palringo.candidate.R;
import com.palringo.candidate.chat.views.MainActivity;
import com.palringo.candidate.login.repositories.EmailLoginRepo;
import com.palringo.candidate.login.repositories.FacebookLoginRepo;
import com.palringo.candidate.login.repositories.GoogleLoginRepo;
import com.palringo.candidate.login.repositories.LoginRepo;
import com.palringo.candidate.login.viewModel.LoginViewModel;

public class LoginActivity extends AppCompatActivity {

    private ProgressBar loadingView;
    private LoginViewModel loginViewModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        loadingView = findViewById(R.id.progressBar);
        setUpViewModel();
    }

    private void setUpViewModel() {
        loginViewModel = new LoginViewModel(new LoginRepo(new LoginService()),
                new FacebookLoginRepo(),
                new EmailLoginRepo(),
                new GoogleLoginRepo());

        loginViewModel.getLoggedIn().observe(this, unit -> {
            startActivity(new Intent(LoginActivity.this, MainActivity.class));
        });

        loginViewModel.getShowError().observe(this, error -> {
            Toast.makeText(LoginActivity.this, error, Toast.LENGTH_SHORT)
                    .show();
        });

        loginViewModel.getShowLoading().observe(this, loading -> {
            if (loading) {
                loadingView.setVisibility(View.VISIBLE);
            } else {
                loadingView.setVisibility(View.GONE);
            }
        });
    }

    public void facebookLogin(View view) {
        loginViewModel.loginWithFacebook();
    }

    public void googleLogin(View view) {
        loginViewModel.loginWithGoogle();
    }

    public void emailLogin(View view) {
        loginViewModel.loginWithEmail();
    }
}
