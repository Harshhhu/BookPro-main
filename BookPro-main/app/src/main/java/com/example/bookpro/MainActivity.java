package com.example.bookpro;

import android.os.Bundle;
import android.os.Handler;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentTransaction;

import com.example.bookpro.AuthenticationScreens.Login_SignupScreen;
import com.example.bookpro.AuthenticationScreens.SplashScreen;
import com.example.bookpro.HomeScreen.HomeScreen;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main), (v, insets) -> {
            Insets systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars());
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom);
            return insets;
        });

        showHomeScreen();

    }

    private void showHomeScreen(){

        loadFragment(new HomeScreen());
        //new Handler().postDelayed(() -> loadFragment(new Login_SignupScreen()), 2000);
    }

    private void loadFragment(Fragment fragment) {
        FragmentTransaction transaction = getSupportFragmentManager().beginTransaction();
        transaction.replace(R.id.fragment_screen, fragment);
        transaction.addToBackStack(null);
        transaction.addToBackStack(null);
        transaction.commit();
    }
}