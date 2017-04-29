package com.sid.ithakademo.transport.detail;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;

import com.sid.ithakademo.MainActivity;
import com.sid.ithakademo.R;
import com.sid.ithakademo.adapters.RoutesRvAdapter;
import com.sid.ithakademo.adapters.TransportRvAdapter;
import com.sid.ithakademo.lists.RoutesLists;
import com.sid.ithakademo.lists.TransportList;
import com.sid.ithakademo.networking.RoutesService;
import com.sid.ithakademo.pojo.lists.Routes;
import com.sid.ithakademo.pojo.lists.Trips;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

/**
 * A simple {@link Fragment} subclass.
 */
public class TrandportDetailFrag extends Fragment {


    public TrandportDetailFrag() {
        // Required empty public constructor
    }

    String cityA;
    String cityB;
    String TAG = "IthakaDemo";

    Boolean flag=false;

    ImageView back;
    Button sortButton;
    RecyclerView recyclerView;
    ProgressBar progressBar;

    TransportRvAdapter adapter;

    private List<TransportList> transList = new ArrayList<>();



    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            cityA = bundle.getString("cityA");
            cityB = bundle.getString("cityB");
            getRoutes(cityA,cityB);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_trandport_detail, container, false);
        viewInit(view);
        viewOnClick();
        return view;
    }

    private void viewInit(View view) {
        back = (ImageView)view.findViewById(R.id.back);
        sortButton = (Button) view.findViewById(R.id.sortButton);
        recyclerView = (RecyclerView)view.findViewById(R.id.recyclerView);
        progressBar = (ProgressBar)view.findViewById(R.id.progressBar);

        adapter = new TransportRvAdapter(transList);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getActivity());
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void viewOnClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onBackPressed();
            }
        });
    }

    private void getRoutes(String cityA,String cityB){
        RoutesService routesService = RoutesService.retrofit.create(RoutesService.class);
        Call<List<Trips>> call = routesService.tripsResponse(cityA,cityB);
        call.enqueue(new Callback<List<Trips>>() {
            @Override
            public void onResponse(Call<List<Trips>> call, Response<List<Trips>> response) {
                List<Trips> tripsList = response.body();
                for(Trips trips : tripsList){
                    String cost = trips.getTotalCost() + " THB";
                    String duration = trips.getTotalDuration() + " hours";
                    String type = trips.getType();
                    Routes[] routes = trips.getRoutes();
                    int size = routes.length;
                    for(int i=0;i<routes.length;i++){
                        String cityA = routes[i].getFrom();
                        String cityB = routes[i].getTo();
                        String time = routes[i].getTime();
                        TransportList t = new TransportList(cityA,cityB,time,duration,type,cost,size);
                        transList.add(t);
                    }
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
            }

            @Override
            public void onFailure(Call<List<Trips>> call, Throwable t) {
                Log.e(TAG,"Networking failed: "+ t.getMessage());
            }
        });

    }

}
