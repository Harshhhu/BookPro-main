package com.example.bookpro.AuthenticationScreens;

import android.annotation.SuppressLint;
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
import com.example.bookpro.databinding.FragmentRegisterScreenBinding;

import java.util.Objects;


public class RegisterScreen extends Fragment {

    private FragmentRegisterScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        binding = FragmentRegisterScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();

    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewAsUser();
        clickOperations();

    }

    @SuppressLint("SetTextI18n")
    private void setViewAsUser() {

        assert getArguments() != null;
        if(Objects.equals(getArguments().getString("user"), "supplier")){
           binding.edttxtUserName.setVisibility(View.VISIBLE);
           binding.edttxtEmailAddress.setVisibility(View.VISIBLE);
           binding.edttxtPassword.setVisibility(View.VISIBLE);
           binding.edttxtCode.setVisibility(View.GONE);
           binding.btnRegister.setText("Register As Supplier");
        } else if (Objects.equals(getArguments().getString("user"), "admin")) {
            binding.edttxtUserName.setVisibility(View.VISIBLE);
            binding.edttxtEmailAddress.setVisibility(View.VISIBLE);
            binding.edttxtPassword.setVisibility(View.VISIBLE);
            binding.edttxtCode.setVisibility(View.VISIBLE);
            binding.btnRegister.setText("Register As Admin");
        } else{//customer
            binding.edttxtUserName.setVisibility(View.VISIBLE);
            binding.edttxtEmailAddress.setVisibility(View.VISIBLE);
            binding.edttxtPassword.setVisibility(View.VISIBLE);
            binding.edttxtCode.setVisibility(View.GONE);
            binding.btnRegister.setText("Register As Customer");
        }
    }

    private boolean CheckValidation() {

        if (binding.edttxtUserName.length() == 0) {
            binding.edttxtUserName.setError("Name is required");
            return false;
        }
        if (binding.edttxtEmailAddress.length() == 0 && !binding.edttxtEmailAddress.getText().toString().trim().matches("[a-zA-Z0-9._-]+@[a-z]+\\.+[a-z]+")) {
            binding.edttxtEmailAddress.setError("Invalid Email Address");
            return false;
        }
        if (binding.edttxtPassword.length() == 0) {
            binding.edttxtPassword.setError("Password is required");
            return false;
        } else if (!binding.edttxtPassword.getText().toString().matches("^(?=.*[0-9])(?=.*[A-Z])(?=.*[@#$%^&+=!])(?=\\S+$).{4,}$")) {
            binding.edttxtPassword.setError("Too easy Make a good one...");
            return false;
        }
        else if (binding.edttxtCode.length() == 0) {
            binding.edttxtPassword.setError("Code is required");
            return true;
        }

        // after all validation return true.
        return true;
    }

    private void clickOperations(){

        binding.imgBackFromRegisterPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });

        binding.btnRegister.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if (CheckValidation()){
                    Toast.makeText(requireContext(),"Registration Successful",Toast.LENGTH_SHORT).show();
                    loadFragment(new HomeScreen());
                }
            }
        });
    }

    private void loadFragment(Fragment fragment) {
        Bundle bundle = new Bundle();
        assert getArguments() != null;
        bundle.putString("user",getArguments().getString("user"));

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_screen, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}