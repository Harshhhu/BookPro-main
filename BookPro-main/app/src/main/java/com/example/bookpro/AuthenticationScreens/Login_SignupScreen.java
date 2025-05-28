package com.example.bookpro.AuthenticationScreens;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import com.example.bookpro.R;
import com.example.bookpro.databinding.FragmentLoginSignupScreenBinding;

import java.util.Objects;


public class Login_SignupScreen extends Fragment {


    private FragmentLoginSignupScreenBinding binding;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginSignupScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        binding.layoutForLoginSignupOption.setVisibility(View.VISIBLE);
        binding.layoutForRegisterUsersOptions.setVisibility(View.GONE);
        clickOperation();

    }

    private void clickOperation(){

        binding.register.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                showRegisterScreen();
            }
        });

        binding.logIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new LoginScreen(), "");
            }
        });
    }

    private void loadFragment(Fragment fragment, String user) {
        Bundle bundle = new Bundle();

        if(Objects.equals(user, "customer")){
            bundle.putString("user","customer");
        } else if (Objects.equals(user, "supplier")) {
            bundle.putString("user","supplier");
        } else if (Objects.equals(user, "admin")) {
            bundle.putString("user","admin");}

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_screen, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }

    private void showRegisterScreen(){
        binding.layoutForLoginSignupOption.setVisibility(View.GONE);
        binding.layoutForRegisterUsersOptions.setVisibility(View.VISIBLE);

        binding.registerAsCustomer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new RegisterScreen(),"customer");
            }
        });

        binding.registerAsSuppliers.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new RegisterScreen(),"supplier");
            }
        });

        /*binding.registerAsAdmin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new RegisterScreen(),"admin");
            }
        });*/
    }

}