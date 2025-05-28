package com.example.bookpro.HomeScreen;

import android.os.Bundle;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookpro.databinding.FragmentBookingDescriptionScreenBinding;


public class BookingDescriptionScreen extends Fragment {

    private FragmentBookingDescriptionScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        binding = FragmentBookingDescriptionScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setDataInView();
        clickOperations();

    }

    private void clickOperations() {
        binding.imgBackFromDescriptionPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });
    }

    private void setDataInView(){
        assert getArguments() != null;
        binding.imgHoliday.setImageResource(getArguments().getInt("Image"));
        binding.txtHolidayDescription.setText(getArguments().getString("description"));
        binding.txtHolidayName.setText(getArguments().getString("Name"));
        binding.txtHolidayPrice.setText("$ 200");
        binding.txtBuy.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Toast.makeText(requireContext(),"Order Successful",Toast.LENGTH_SHORT).show();
            }
        });

    }


}