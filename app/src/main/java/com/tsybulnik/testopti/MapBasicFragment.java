package com.tsybulnik.testopti;

import android.os.Bundle;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;

import com.google.android.gms.maps.CameraUpdate;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapView;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;

/**
 * A simple {@link Fragment} subclass.
 * Use the {@link MapBasicFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class MapBasicFragment extends Fragment implements OnMapReadyCallback {
    ImageView ivMap;
    GoogleMap map;
    Fragment mapFrag;

    public MapBasicFragment() {
        // Required empty public constructor
    }

    public static MapBasicFragment newInstance(String param1, String param2) {
        MapBasicFragment fragment = new MapBasicFragment();
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_map_basic, container, false);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        ivMap = view.findViewById(R.id.ivGPS);
        mapFrag = new Fragment();

        ivMap.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onMapReady(map);
            }
        });



    }

    @Override
    public void onMapReady(@NonNull GoogleMap googleMap) {
        map = googleMap;
        LatLng latLng = new LatLng(50.501930055383056, 30.24779258464651);
        googleMap.addMarker(new MarkerOptions().position(latLng).title("GPS"));
        googleMap.moveCamera(CameraUpdateFactory.newLatLng(latLng));
    }
}