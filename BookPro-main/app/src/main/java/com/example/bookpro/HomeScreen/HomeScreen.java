package com.example.bookpro.HomeScreen;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.os.Handler;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Toast;

import com.example.bookpro.Adapter.AdapterHomeList;
import com.example.bookpro.AuthenticationScreens.Login_SignupScreen;
import com.example.bookpro.AuthenticationScreens.SplashScreen;
import com.example.bookpro.R;
import com.example.bookpro.databinding.FragmentHomeScreenBinding;
import com.example.bookpro.otherScreens.AddProduct;
import com.example.bookpro.otherScreens.AdminDashboard;
import com.example.bookpro.otherScreens.SearchScreen;
import com.example.bookpro.otherScreens.UserProfile;

import java.util.ArrayList;
import java.util.Objects;


public class HomeScreen extends Fragment implements AdapterHomeList.ItemClickListener {
    AdapterHomeList adapter;
    private FragmentHomeScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentHomeScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

        setViewAsUser();
        clickOperations();
    }

    private void setViewAsUser(){
        if(getArguments() != null) {
            binding.signUpLogin.setVisibility(View.GONE);
            if (Objects.equals(getArguments().getString("user"), "supplier")) {
                binding.txt1.setVisibility(View.VISIBLE);
                binding.signUpLogin.setVisibility(View.GONE);
                binding.addProduct.setVisibility(View.VISIBLE);
                binding.txt1.setText(R.string.add_product_as_supplier);
                binding.addProduct.setText(R.string.add_product);
                callRecycler("supplier");

                binding.addProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {loadFragment(new AddProduct());}});
            } else if (Objects.equals(getArguments().getString("user"), "admin")) {
                binding.txt1.setVisibility(View.VISIBLE);
                binding.addProduct.setVisibility(View.VISIBLE);
                binding.signUpLogin.setVisibility(View.GONE);
                binding.txt1.setText(R.string.go_to_admin_dashboard);
                binding.addProduct.setText(R.string.admin_dashboard);

                binding.addProduct.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View view) {
                        loadFragment(new AdminDashboard());
                    }
                });
                callRecycler("admin");

            } else {//customer
                binding.signUpLogin.setVisibility(View.GONE);
                binding.txt1.setVisibility(View.GONE);
                binding.addProduct.setVisibility(View.GONE);
                callRecycler("customer");

            }
        }
        else{
            binding.signUpLogin.setVisibility(View.VISIBLE);
            binding.txt1.setVisibility(View.GONE);
            binding.addProduct.setVisibility(View.GONE);
            callRecycler("customer");
        }
    }

    private void clickOperations(){
        binding.imgProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new UserProfile());
            }
        });
        binding.imgSearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new SearchScreen());
            }
        });
        binding.signUpLogin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new SplashScreen());
            }
        });

    }
    private void callRecycler(String supplier){
        ArrayList<String> bookingNames = new ArrayList<>();
        ArrayList<String> bookingDescription = new ArrayList<>();
        ArrayList<Integer> bookingImages = new ArrayList<>();
        //DataForBooking dataForBooking = new DataForBooking();
        //dataForBooking.setLocationName("Auckland");
        //dataForBooking.setDescription("");

        //dataForBooking.setImage(R.drawable.background_bookpro);

        bookingNames.add("Barcelona");
        bookingNames.add("Rome");
        bookingNames.add("Paris");
        bookingNames.add("Tokyo");
        bookingNames.add("New York");
        bookingNames.add("Sydney");
        bookingNames.add("Bali");
        bookingNames.add("New Zealand");

        bookingDescription.add("Known for its diverse landscapes, including volcanic craters, lush rainforests, and pristine beaches, Maui offers activities like snorkeling at Molokini Crater, driving the scenic Road to Hana, and relaxing on Wailea Beach.");
        bookingDescription.add("Rome is a city where ancient history and modern life blend seamlessly. Explore the Colosseum, Roman Forum, and Pantheon, and enjoy the Renaissance art in the Vatican Museums and St. Peter's Basilica.");
        bookingDescription.add("The \"City of Light\" is celebrated for its world-class museums, like the Louvre, its romantic ambiance, and architectural marvels such as the Eiffel Tower and Notre-Dame Cathedral. Paris is also renowned for its vibrant café culture and haute cuisine.");
        bookingDescription.add("Renowned for its classical Buddhist temples, Shinto shrines, traditional wooden houses, and stunning gardens, Kyoto is the epitome of traditional Japanese culture. Key highlights include the Fushimi Inari Shrine with its thousands of red torii gates and the beautiful Arashiyama Bamboo Grove.");
        bookingDescription.add("Known as \"The Big Apple,\" New York City is a melting pot of cultures with landmarks like Times Square, Central Park, the Statue of Liberty, and Broadway. Its museums, shopping, and diverse food scene offer endless exploration.");
        bookingDescription.add("Sydney is famous for its iconic Sydney Opera House and Harbour Bridge. The city boasts beautiful beaches like Bondi and Manly, a bustling arts scene, and vibrant neighborhoods such as The Rocks and Darling Harbour.");
        bookingDescription.add("Often referred to as \"The City of Light,\" Paris is famous for its iconic landmarks such as the Eiffel Tower, Louvre Museum, and Notre-Dame Cathedral. Its charming streets, world-class cuisine, and rich history make it a must-visit destination.");
        bookingDescription.add("Queenstown is a mecca for adventure seekers, offering activities such as bungee jumping, skydiving, and skiing. Set against the backdrop of Lake Wakatipu and the Remarkables mountain range, it's also a gateway to exploring nearby Fiordland National Park.");

        bookingImages.add(R.drawable.image);
        bookingImages.add(R.drawable.image2);
        bookingImages.add(R.drawable.image3);
        bookingImages.add(R.drawable.image4);
        bookingImages.add(R.drawable.images2);
        bookingImages.add(R.drawable.images);
        bookingImages.add(R.drawable.download);
        bookingImages.add(R.drawable.download2);


        // set up the RecyclerView
        binding.recyclerview1.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false));
        adapter = new AdapterHomeList(requireContext(), bookingNames,bookingDescription,bookingImages,supplier);
        //adapter.setClickListener(requireContext());
        binding.recyclerview1.setAdapter(adapter);

        binding.recyclerview2.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerview2.setAdapter(adapter);

        binding.recyclerview3.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.HORIZONTAL, false));
        binding.recyclerview3.setAdapter(adapter);

    }

    @Override
    public void onItemClick(View view, int position) {

    }

    private void loadFragment(Fragment fragment) {

        FragmentManager fragmentManager = getFragmentManager();
        assert fragmentManager != null;
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        fragmentTransaction.replace(R.id.fragment_screen, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();
    }
}