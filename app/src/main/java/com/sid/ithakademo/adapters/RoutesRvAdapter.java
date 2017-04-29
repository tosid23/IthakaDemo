package com.sid.ithakademo.adapters;

import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import com.sid.ithakademo.MainActivity;
import com.sid.ithakademo.R;
import com.sid.ithakademo.lists.RoutesLists;

import java.util.List;

/**
 * Created by sid on 29/4/17.
 */

public class RoutesRvAdapter extends RecyclerView.Adapter<RoutesRvAdapter.MyViewHolder> {

    public RoutesRvAdapter(List<RoutesLists> list,MainActivity mainActivity) {
        this.list = list;
        this.mainActivity=mainActivity;
    }

    List<RoutesLists> list;
    MainActivity mainActivity;

    @Override
    public MyViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.re_routes, parent, false);

        return new MyViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(MyViewHolder holder, int position) {
        RoutesLists routesLists = list.get(position);
        holder.cost.setText(routesLists.getCost());
        holder.duration.setText(routesLists.getTime());
        holder.type.setText(routesLists.getType());
        holder.cityA.setText(routesLists.getCityA());
        holder.cityB.setText(routesLists.getCityB());
        if(position==(getItemCount()-1)){
            mainActivity.showFab();
        }
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder{
        TextView cityA,cityB, duration,cost,type;
        public MyViewHolder(View itemView) {
            super(itemView);
            cityA = (TextView)itemView.findViewById(R.id.cityAText);
            cityB = (TextView)itemView.findViewById(R.id.cityBText);
            duration = (TextView)itemView.findViewById(R.id.timeText);
            type = (TextView)itemView.findViewById(R.id.type);
            cost = (TextView)itemView.findViewById(R.id.costText);
        }
    }
}
