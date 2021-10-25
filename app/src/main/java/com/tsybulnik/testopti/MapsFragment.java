package com.tsybulnik.testopti;

import android.Manifest;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

public class MapsFragment extends Fragment implements OnMapReadyCallback {

    GoogleMap mMap;
    LocationManager locationManager;
    android.location.LocationListener locationListener;
    LatLng mylatLng;
    ImageView ivZoomMap;


    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater,
                             @Nullable ViewGroup container,
                             @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.fragment_maps, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        SupportMapFragment supportMapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        supportMapFragment.getMapAsync(this::onMapReady);
        ivZoomMap = view.findViewById(R.id.ivZoomMap);
        ivZoomMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                askLocationPermissin();
            }
        });


    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        mMap = googleMap;
        locationManager = (LocationManager) getContext().getSystemService(Context.LOCATION_SERVICE);
        locationListener = new LocationListener() {
            @Override
            public void onLocationChanged(@NonNull Location location) {
//                mylatLng = new LatLng(-34, 151);
//                mMap.clear();
//                mMap.addMarker(new MarkerOptions().position(mylatLng).title("My_GPS"));
//                mMap.moveCamera(CameraUpdateFactory.newLatLng(mylatLng));
            }
        };
        askLocationPermissin();
    }

    private void askLocationPermissin() {
        Dexter.withContext(getContext()).withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        if (ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                                && ActivityCompat.checkSelfPermission(getContext(),
                                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {

                            return;
                        }
                        locationManager.requestLocationUpdates(LocationManager.GPS_PROVIDER,
                                0, 0,
                                (android.location.LocationListener) locationListener);


                        Location lastLocation = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
                        mylatLng = new LatLng(lastLocation.getLatitude(), lastLocation.getLongitude());
                        mMap.clear();
                        mMap.addMarker(new MarkerOptions().position(mylatLng).title("My_GPS"));
                        mMap.moveCamera(CameraUpdateFactory.newLatLng(mylatLng));
                    }
                    @Override
                    public void onPermissionDenied(PermissionDeniedResponse permissionDeniedResponse) {

                    }
                    @Override
                    public void onPermissionRationaleShouldBeShown(PermissionRequest permissionRequest, PermissionToken permissionToken) {
                        permissionToken.continuePermissionRequest();
                    }
                }).check();
    }

}