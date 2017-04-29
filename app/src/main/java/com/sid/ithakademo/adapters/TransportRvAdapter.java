package com.sid.ithakademo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sid.ithakademo.R;
import com.sid.ithakademo.lists.TransportList;

import java.util.List;

/**
 * Created by sid on 29/4/17.
 */

public class TransportRvAdapter extends RecyclerView.Adapter<TransportRvAdapter.MyViewHolder> {

    public TransportRvAdapter(List<TransportList> lists) {
        this.lists = lists;
    }

    List<TransportList> lists;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.re_transport_routes, parent, false);

        return new TransportRvAdapter.MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        TransportList transportList = lists.get(position);
        holder.type.setText(transportList.getType());
        holder.cost.setText(transportList.getCost());
        holder.cityB.setText(transportList.getCityB());
        holder.cityA.setText(transportList.getCityA());
        holder.time.setText(transportList.getTime());
        holder.duration.setText(transportList.getDuration());

        if(transportList.getSize()>1){
            holder.type.setText(" ");
            holder.view.setVisibility(View.GONE);
        }
    }

    @Override
    public int getItemCount() {
        return lists.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cost,time, duration, cityA,cityB, type;
        View view;
        public MyViewHolder(View itemView) {
            super(itemView);
            cost = (TextView)itemView.findViewById(R.id.costText);
            time = (TextView)itemView.findViewById(R.id.timeText);
            duration = (TextView)itemView.findViewById(R.id.durationText);
            cityA = (TextView)itemView.findViewById(R.id.cityAText);
            cityB = (TextView)itemView.findViewById(R.id.cityBText);
            type = (TextView)itemView.findViewById(R.id.type);
            view = (View)itemView.findViewById(R.id.v1);

        }
    }
}
