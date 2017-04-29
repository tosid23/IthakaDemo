package com.sid.ithakademo.transport.detail;


import android.media.Image;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;

import com.sid.ithakademo.MainActivity;
import com.sid.ithakademo.R;

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


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Bundle bundle = this.getArguments();
        if(bundle!=null){
            cityA = bundle.getString("cityA");
            cityB = bundle.getString("cityB");
            flag=true;
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
    }

    private void viewOnClick() {
        back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ((MainActivity)getActivity()).onBackPressed();
            }
        });
    }

}
