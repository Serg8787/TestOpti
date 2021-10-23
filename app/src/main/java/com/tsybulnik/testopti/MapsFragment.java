package com.tsybulnik.testopti;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;

import android.Manifest;
import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationListener;
import android.location.LocationManager;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;

import java.io.IOException;
import java.util.List;
import java.util.Locale;

public class MapsFragment extends Fragment {
    FusedLocationProviderClient client;
    SupportMapFragment mapFragment;
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
        ivZoomMap = view.findViewById(R.id.ivZoomMap);


        mapFragment = (SupportMapFragment) getChildFragmentManager().findFragmentById(R.id.map);
        client = LocationServices.getFusedLocationProviderClient((Activity) getContext());
    }
    private void getCurrentLocation() {
        if (ActivityCompat.checkSelfPermission((Activity) getContext(),
                Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED
                && ActivityCompat.checkSelfPermission((Activity) getContext(),
                Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            getCurrentLocation();
        } else {
            ActivityCompat.requestPermissions((Activity) getContext(),
                    new String[]{Manifest.permission.ACCESS_FINE_LOCATION}, 100);

        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
           if(location!=null){
               mapFragment.getMapAsync(new OnMapReadyCallback() {
                   @Override
                   public void onMapReady(@NonNull GoogleMap googleMap) {
                       LatLng latLng = new LatLng(location.getLatitude(),location.getLongitude());
                       MarkerOptions markerOptions = new MarkerOptions().position(latLng)
                               .title("MY_GPS");
                       googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,10));
                       googleMap.addMarker(markerOptions);
                   }
               });
           }
            }
        });
    }

    @Override
    public void onRequestPermissionsResult(int requestCode, @NonNull String[] permissions, @NonNull int[] grantResults) {
      if(requestCode==100){
          if(grantResults.length>0 && grantResults[0]==
          PackageManager.PERMISSION_GRANTED){
              getCurrentLocation();
          }
      }
    }
}