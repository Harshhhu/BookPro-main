package com.example.bookpro.otherScreens;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.LinearLayoutManager;

import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.example.bookpro.Adapter.AdapterHomeList;
import com.example.bookpro.HomeScreen.BookingDescriptionScreen;
import com.example.bookpro.R;
import com.example.bookpro.databinding.FragmentSearchScreenBinding;

import java.util.ArrayList;


public class SearchScreen extends Fragment implements AdapterHomeList.ItemClickListener {
    AdapterHomeList adapter;
    private FragmentSearchScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        binding = FragmentSearchScreenBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        callRecycler();
        clickOperations();
        searchOperation();

    }

    private void clickOperations() {
        binding.imgBackFromSearchPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                assert getFragmentManager() != null;
                getFragmentManager().popBackStack();
            }
        });
    }

    private void callRecycler(){
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
        binding.recyclerSearch.setLayoutManager(new LinearLayoutManager(requireContext(),LinearLayoutManager.VERTICAL, false));
        adapter = new AdapterHomeList(requireContext(), bookingNames,bookingDescription,bookingImages, "customer");
        //adapter.setClickListener(requireContext());
        binding.recyclerSearch.setAdapter(adapter);

    }

    private void searchOperation(){
        binding.edttxtSearch.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence charSequence, int i, int i1, int i2) {
            }

            @Override
            public void onTextChanged(CharSequence charSequence, int i, int i1, int i2) {
                adapter.filter(charSequence);
            }

            @Override
            public void afterTextChanged(Editable editable) {

            }
        });
    }

    @Override
    public void onItemClick(View view, int position) {
        loadFragment(new BookingDescriptionScreen());
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