package com.sid.ithakademo;

import android.graphics.Color;
import android.support.design.widget.FloatingActionButton;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.support.v4.app.FragmentTransaction;
import android.support.v7.widget.DefaultItemAnimator;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.LatLngBounds;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.gson.Gson;
import com.sid.ithakademo.adapters.RoutesRvAdapter;
import com.sid.ithakademo.lists.RoutesLists;
import com.sid.ithakademo.networking.RoutesService;
import com.sid.ithakademo.pojo.lists.Routes;
import com.sid.ithakademo.pojo.lists.Trips;
import com.sid.ithakademo.transport.detail.TrandportDetailFrag;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;

public class MainActivity extends FragmentActivity implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener{

    private GoogleMap mMap;

    private Marker mBangkok;
    private Marker mPhuket;
    private Marker mKrabi;
    private Marker mKohPhiPhi;
    private Marker mKohSamui;
    private Marker mSuratThani;

    private static final LatLngBounds THAILAND = new LatLngBounds(new LatLng(7.21, 98.43),new LatLng(14.24, 102.33));

    private static final LatLng bangkok = new LatLng(13.724561, 100.4930249);
    private static final LatLng phuket = new LatLng(7.8833604, 98.3744039);
    private static final LatLng krabi = new LatLng(8.0306534, 98.8174321);
    private static final LatLng kohPhiPhi = new LatLng(7.7526506, 98.7390835);
    private static final LatLng kohSamui = new LatLng(9.50108, 99.931372);
    private static final LatLng suratThani = new LatLng(9.1546336, 99.2639922);

    String TAG = "IthakaDemo";
    String cityA,cityB;
    int markerClickCount =0;

    TextView startCity,destinationCity;
    Button searchButton;
    RelativeLayout searchLayout;
    RecyclerView recyclerView;
    RoutesRvAdapter adapter;
    ProgressBar progressBar;
    FloatingActionButton fab;

    private List<RoutesLists> routesListses = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        // Obtain the SupportMapFragment and get notified when the map is ready to be used.
        SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
                .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);

        initializeViews();
        viewOnClicks();
    }

    private void initializeViews() {
        searchLayout    = (RelativeLayout)findViewById(R.id.searchLayout);
        startCity       = (TextView)findViewById(R.id.startCity);
        destinationCity = (TextView)findViewById(R.id.destinationCity);
        searchButton    = (Button)findViewById(R.id.searchButton);
        searchButton.setEnabled(false);

        progressBar = (ProgressBar)findViewById(R.id.progressBar);

        recyclerView = (RecyclerView)findViewById(R.id.recyclerView);
        fab = (FloatingActionButton)findViewById(R.id.fab);

        adapter = new RoutesRvAdapter(routesListses,this);
        RecyclerView.LayoutManager mLayoutManager = new LinearLayoutManager(getApplicationContext()
                ,LinearLayoutManager.HORIZONTAL, false);
        recyclerView.setLayoutManager(mLayoutManager);
        recyclerView.setItemAnimator(new DefaultItemAnimator());
        recyclerView.setAdapter(adapter);
    }

    private void viewOnClicks(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                cityA = startCity.getText().toString();
                cityB = destinationCity.getText().toString();
                getRoutes(cityA,cityB);

            }
        });

        fab.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Bundle bundle = new Bundle();
                bundle.putString("cityA", cityA);
                bundle.putString("cityB", cityB);
                Fragment fragment = new TrandportDetailFrag();
                fragment.setArguments(bundle);
                FragmentTransaction ft = getSupportFragmentManager().beginTransaction();
                ft.replace(android.R.id.content, fragment);
                ft.addToBackStack(null);
                ft.commit();
            }
        });
    }

    /**
     * Manipulates the map once available.
     * This callback is triggered when the map is ready to be used.
     * This is where we can add markers or lines, add listeners or move the camera. In this case,
     * we just add a marker near Sydney, Australia.
     * If Google Play services is not installed on the device, the user will be prompted to install
     * it inside the SupportMapFragment. This method will only be triggered once the user has
     * installed Google Play services and returned to the app.
     */
    @Override
    public void onMapReady(GoogleMap googleMap) {
        mMap = googleMap;

        // Add a marker in Bangkok
        mBangkok = mMap.addMarker(new MarkerOptions()
                .position(bangkok)
                .title("Bangkok"));

        mBangkok.setTag(0);

        // Add a marker in Phuket
        mPhuket =  mMap.addMarker(new MarkerOptions()
                .position(phuket)
                .title("Phuket"));
        mPhuket.setTag(0);

        // Add a marker in Krabi
        mKrabi =  mMap.addMarker(new MarkerOptions()
                .position(krabi)
                .title("Krabi"));
        mKrabi.setTag(0);

        // Add a marker in Koh Phi Phi
        mKohPhiPhi =  mMap.addMarker(new MarkerOptions()
                .position(kohPhiPhi)
                .title("Koh Phi Phi"));
        mKohPhiPhi.setTag(0);

        // Add a marker in Koh Samui
        mKohSamui =  mMap.addMarker(new MarkerOptions()
                .position(kohSamui)
                .title("Koh Samui"));
        mKohSamui.setTag(0);

        // Add a marker in Surat Thani
        mSuratThani =  mMap.addMarker(new MarkerOptions()
                .position(suratThani)
                .title("Surat Thani"));
        mSuratThani.setTag(0);

        int width = getResources().getDisplayMetrics().widthPixels;
        int height = getResources().getDisplayMetrics().heightPixels;
        int padding = (int) (width * 0.12);

        mMap.moveCamera(CameraUpdateFactory.newLatLngBounds(THAILAND, width,height,padding));
        mMap.setLatLngBoundsForCameraTarget(THAILAND);

        mMap.setOnMarkerClickListener(this);

    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        // Retrieve the data from the marker.
        if(markerClickCount<2){
            marker.setIcon(BitmapDescriptorFactory.defaultMarker((260)));
        }
        markerClickCount++;
        if(markerClickCount==1){
            searchLayout.setVisibility(View.VISIBLE);
            startCity.setText(marker.getTitle());
            startCity.setTextColor(Color.RED);
        }
        if(markerClickCount==2){
            destinationCity.setText(marker.getTitle());
            destinationCity.setTextColor(Color.RED);
            searchButton.setEnabled(true);
            searchButton.setTextColor(Color.RED);
        }

        return true;
    }

    private void getRoutes(String cityA,String cityB){
        Log.e(TAG,"CityA: "+cityA+"\nCityB: "+cityB);
        searchLayout.setVisibility(View.INVISIBLE);
        progressBar.setVisibility(View.VISIBLE);
        RoutesService routesService = RoutesService.retrofit.create(RoutesService.class);
        Call<List<Trips>> call = routesService.tripsResponse(cityA,cityB);
        call.enqueue(new Callback<List<Trips>>() {
            @Override
            public void onResponse(Call<List<Trips>> call, Response<List<Trips>> response) {
                List<Trips> tripsList = response.body();
                int count= 0;
                for(Trips trips : tripsList){
                    Log.e(TAG,""+trips.getTotalCost());
                    String cost = trips.getTotalCost() + " THB";
                    String duration = trips.getTotalDuration() + " hours";
                    String type = trips.getType();
                    Routes[] routes = trips.getRoutes();
                    if(routes.length==1){
                        String cityA = routes[0].getFrom();
                        String cityB = routes[0].getTo();
                        RoutesLists rL = new RoutesLists(cityA,cityB,type,duration,cost);
                        routesListses.add(rL);
                        count++;
                        if(count==2){
                            break;
                        }
                    }
                }
                adapter.notifyDataSetChanged();
                progressBar.setVisibility(View.INVISIBLE);
                recyclerView.setVisibility(View.VISIBLE);
            }

            @Override
            public void onFailure(Call<List<Trips>> call, Throwable t) {
                Log.e(TAG,"Networking failed: "+ t.getMessage());
            }
        });

    }

    public void showFab(){
        fab.setVisibility(View.VISIBLE);
    }

    public void hideFab(){
        fab.setVisibility(View.INVISIBLE);
    }


}
