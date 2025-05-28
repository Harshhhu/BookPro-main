package com.example.bookpro.Adapter;

import android.content.Context;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;
import com.example.bookpro.HomeScreen.BookingDescriptionScreen;
import com.example.bookpro.R;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class AdapterHomeList extends RecyclerView.Adapter<AdapterHomeList.ViewHolder> {

    private List<String> mData;
    private List<String> descriptionData;
    private List<Integer> imagesData;
    private List<String> mDataCopy;
    private LayoutInflater mInflater;
    private String customer;
    private ItemClickListener mClickListener;

    // data is passed into the constructor
    public AdapterHomeList(Context context, List<String> data, List<String> dataDescription, List<Integer> dataImages, String customer) {
        this.mInflater = LayoutInflater.from(context);
        this.mData = data;
        this.descriptionData = dataDescription;
        this.imagesData = dataImages;
        this.mDataCopy = new ArrayList<>();
        this.customer = customer;
        mDataCopy.addAll(mData);
    }

    // inflates the row layout from xml when needed
    @Override
    public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View view = mInflater.inflate(R.layout.home_recycler_adapter, parent, false);
        return new ViewHolder(view);
    }

    // binds the data to the TextView in each row
    @Override
    public void onBindViewHolder(ViewHolder holder, int position) {
        holder.buy.setVisibility(View.GONE);
        if(Objects.equals(customer, "customer"))
        {
            holder.buy.setVisibility(View.VISIBLE);
        }
        else{
            holder.buy.setVisibility(View.GONE);
        }
        holder.myTextView.setText(mData.get(position));
        holder.imageDestination.setImageResource(imagesData.get(position));
        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                loadFragment(new BookingDescriptionScreen(),view,mData.get(position),descriptionData.get(position)
                        ,imagesData.get(position));
            }
        });
    }

    // total number of rows
    @Override
    public int getItemCount() {
        return mData.size();
    }


    // stores and recycles views as they are scrolled off screen
    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {
        TextView myTextView;
        ImageView imageDestination;
        TextView buy;


        ViewHolder(View itemView) {
            super(itemView);
            myTextView = itemView.findViewById(R.id.txt_holiday_name);
            imageDestination = itemView.findViewById(R.id.img_destination);
            buy = itemView.findViewById(R.id.buy);
            itemView.setOnClickListener(this);
        }

        @Override
        public void onClick(View view) {
            if (mClickListener != null) mClickListener.onItemClick(view, getAdapterPosition());
        }
    }

    // convenience method for getting data at click position
    String getItem(int id) {
        return mData.get(id);
    }

    // allows clicks events to be caught

    public void setClickListener(Context itemClickListener) {
        this.mClickListener = (ItemClickListener) itemClickListener;
    }

    // parent activity will implement this method to respond to click events
    public interface ItemClickListener {
        void onItemClick(View view, int position);
    }

    public void filter(CharSequence sequence) {
        List<String> temp = new ArrayList<>();
        if (!TextUtils.isEmpty(sequence)) {
            for (String s : mData) {
                if (s.toLowerCase().contains(sequence)) {
                    temp.add(s);
                }
            }
        } else {
            temp.addAll(mDataCopy);
        }
        mData.clear();
        mData.addAll(temp);
        notifyDataSetChanged();
        temp.clear();
    }

    private void loadFragment(Fragment fragment, View v, String Name, String Description, Integer Image) {

        AppCompatActivity appCompatActivity = (AppCompatActivity) v.getContext();
        FragmentManager fragmentManager = appCompatActivity.getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = fragmentManager.beginTransaction();
        Bundle bundle = new Bundle();
        bundle.putString("Name",Name);
        bundle.putString("description",Description);
        bundle.putInt("Image",Image);
        fragment.setArguments(bundle);
        fragmentTransaction.replace(R.id.fragment_screen, fragment);
        fragmentTransaction.addToBackStack(null);
        fragmentTransaction.commit();

    }
}