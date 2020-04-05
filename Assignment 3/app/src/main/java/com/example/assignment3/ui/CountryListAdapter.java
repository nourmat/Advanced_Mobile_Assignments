package com.example.assignment3.ui;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.assignment3.R;
import com.example.assignment3.model.room.Country;

import java.util.List;

public class CountryListAdapter extends RecyclerView.Adapter<CountryListAdapter.CountryViewHolder> {

    private final LayoutInflater mInflater;
    private List<Country> mCountries; // Cached copy of words

    CountryListAdapter(Context context) { mInflater = LayoutInflater.from(context); }

    @Override
    public CountryViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = mInflater.inflate(R.layout.recycleview_item, parent, false);
        return new CountryViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(CountryViewHolder holder, int position) {
        if (mCountries != null) {
            Country current = mCountries.get(position);
            holder.name.setText(current.country_name + "");
            holder.temp.setText(current.temp + "");
            holder.min_temp.setText(current.min_temp + "");
            holder.max_temp.setText(current.max_temp + "");
            holder.humidity.setText(current.humidity + "");
            holder.pressure.setText(current.pressure + "");
        } else {
            // Covers the case of data not being ready yet.
            holder.name.setText("NAN");
            holder.temp.setText("NAN");
            holder.min_temp.setText("NAN");
            holder.max_temp.setText("NAN");
            holder.humidity.setText("NAN");
            holder.pressure.setText("NAN");
        }
    }

    void setCountries(List<Country> countries){
        mCountries = countries;
        notifyDataSetChanged();
    }

    // getItemCount() is called many times, and when it is first called,
    // mWords has not been updated (means initially, it's null, and we can't return null).
    @Override
    public int getItemCount() {
        if (mCountries != null)
            return mCountries.size();
        else return 0;
    }

    class CountryViewHolder extends RecyclerView.ViewHolder {
        private final TextView name, temp, min_temp, max_temp, humidity, pressure;

        private CountryViewHolder(View itemView) {
            super(itemView);
            name = itemView.findViewById(R.id.name_text_view);
            temp = itemView.findViewById(R.id.temp_text_view);
            min_temp = itemView.findViewById(R.id.min_temp_text_view);
            max_temp = itemView.findViewById(R.id.max_temp_text_view);
            humidity = itemView.findViewById(R.id.humidity_text_view);
            pressure = itemView.findViewById(R.id.pressure_text_view);
        }
    }
}