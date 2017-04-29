package com.sid.ithakademo;

import android.graphics.Color;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
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
    int markerClickCount =0;

    TextView startCity,destinationCity;
    Button searchButton;
    RelativeLayout searchLayout;

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
    }

    private void viewOnClicks(){
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getRoutes(startCity.getText().toString(),destinationCity.getText().toString());
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
    }
}
