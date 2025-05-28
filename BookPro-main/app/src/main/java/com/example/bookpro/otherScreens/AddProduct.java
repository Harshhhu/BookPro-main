package com.example.bookpro.otherScreens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookpro.R;
import com.example.bookpro.databinding.FragmentAddProductBinding;
import com.example.bookpro.databinding.FragmentHomeScreenBinding;


public class AddProduct extends Fragment {

    private FragmentAddProductBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentAddProductBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        clickOperation();
    }

    private void clickOperation() {
        binding.btnAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(),"Product Created Successfully",Toast.LENGTH_SHORT).show();
                assert getFragmentManager() != null;
                getParentFragmentManager().popBackStack();
            }
        });

        binding.imgBackFromAddProduct.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getParentFragmentManager().popBackStack();
            }
        });
    }

}