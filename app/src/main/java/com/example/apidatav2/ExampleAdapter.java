package com.example.apidatav2;

import android.annotation.SuppressLint;
import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class ExampleAdapter  extends RecyclerView.Adapter<ExampleAdapter.ExampleViewHolder> {

    private final Context mContext;
    private final ArrayList<Example> newList;

    public ExampleAdapter(Context context,ArrayList<Example> exampleList){
        newList = exampleList;
        mContext = context;


    }

    @NonNull
    @Override
    public ExampleViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(mContext).inflate(R.layout.example_item, parent, false);
        return new ExampleViewHolder(v);
    }

    @SuppressLint("SetTextI18n")
    @Override
    public void onBindViewHolder(@NonNull ExampleAdapter.ExampleViewHolder holder, int position) {
        Example current = newList.get(position);
        String cpID = current.getCpId();
        String area = current.getArea();
        String dev = current.getDevelop();
        String loc = current.getLocation();
        int avLots = current.getLotAvail();
        String lotT = current.getLotType();
        String age = current.getAgency();

        holder.textView1.setText("CarParkID: "+cpID);
        holder.textView2.setText("Area: "+area);
        holder.textView3.setText("Address: "+dev);
        holder.textView4.setText("Coordinates: "+loc);
        holder.textView5.setText("Available Lots: "+avLots);
        holder.textView6.setText("Lot Types: "+lotT);
        holder.textView7.setText("Agency: "+age);

    }

    @Override
    public int getItemCount() {
        return newList.size();
    }

    public static class ExampleViewHolder extends  RecyclerView.ViewHolder{

        public TextView textView1;
        public TextView textView2;
        public TextView textView3;
        public TextView textView4;
        public TextView textView5;
        public TextView textView6;
        public TextView textView7;

        public ExampleViewHolder(@NonNull View itemView) {
            super(itemView);
            textView1 = itemView.findViewById(R.id.cpId);
            textView2 = itemView.findViewById(R.id.area);
            textView3 = itemView.findViewById(R.id.development);
            textView4 = itemView.findViewById(R.id.location);
            textView5 = itemView.findViewById(R.id.lotsAvail);
            textView6 = itemView.findViewById(R.id.type_lot);
            textView7 = itemView.findViewById(R.id.agency);
        }
    }
}
