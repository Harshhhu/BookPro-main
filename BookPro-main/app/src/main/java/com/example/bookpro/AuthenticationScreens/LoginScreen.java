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
import android.widget.Toast;

import com.example.bookpro.HomeScreen.HomeScreen;
import com.example.bookpro.R;
import com.example.bookpro.databinding.FragmentLoginScreenBinding;

public class LoginScreen extends Fragment {

    private FragmentLoginScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentLoginScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        clickOperations();
    }


    private boolean CheckValidation() {

        if (binding.edttxtEmailAddress.length() == 0 && !binding.edttxtEmailAddress.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            binding.edttxtEmailAddress.setError("Invalid Email Address");
            return false;
        }
        if (binding.edttxtPassword.length() == 0) {
            binding.edttxtPassword.setError("Password is required");
            return false;
        }

        // after all validation return true.
        return true;
    }

    private void clickOperations(){

        binding.imgBackFromLoginPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });

        binding.btnLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(CheckValidation()){
                    Toast.makeText(requireContext(),"Login Successfully",Toast.LENGTH_SHORT).show();
                    //open home screen
                    if(binding.edttxtEmailAddress.getText().toString().equals("customer@gmail.com"))
                    {
                        loadFragment(new HomeScreen(), "customer");
                    } else if (binding.edttxtEmailAddress.getText().toString().equals("supplier@gmail.com")) {
                        loadFragment(new HomeScreen(), "supplier");
                    }
                    else if (binding.edttxtEmailAddress.getText().toString().equals("admin@gmail.com")) {
                        loadFragment(new HomeScreen(),"admin");
                    }
                    else{
                        Toast.makeText(requireContext(), "User Not Found!",Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });
    }

    private void loadFragment(Fragment fragment, String user) {
        Bundle bundle = new Bundle();
        assert getArguments() != null;
        bundle.putString("user",user);

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_screen, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}