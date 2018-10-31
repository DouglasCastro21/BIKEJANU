package bike.douglas.com.bikejanu.Fragments;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import bike.douglas.com.bikejanu.Entidades.LocalBikesMaps;
import bike.douglas.com.bikejanu.R;


public class MapaFragment extends Fragment  implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ChildEventListener childEventListener;
    private DatabaseReference databaseReference;
    Marker marker;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_mapa, container, false);


        SupportMapFragment mapFragment = (SupportMapFragment) getChildFragmentManager()
                .findFragmentById(R.id.mapRouboFragmento);
        mapFragment.getMapAsync(this);


        ChildEventListener childEventListener;

        databaseReference = FirebaseDatabase.getInstance().getReference("LocalMaps");
        databaseReference.push().setValue(marker);





        return rootView;
    }

    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }

    @Override
    public void onMapReady(GoogleMap googleMap) {

        mMap = googleMap;

        googleMap.setOnMarkerClickListener(MapaFragment.this);
        googleMap.setMapType(GoogleMap.MAP_TYPE_NORMAL);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot s:dataSnapshot.getChildren()){
                    LocalBikesMaps  map = s.getValue(LocalBikesMaps.class);




                    String txtLatitude = map.latitude;
                    String txtLongitude= map.longitude;



                    double db =  Double.parseDouble(txtLatitude.replace(",", "."));
                    double db2 = Double.parseDouble(txtLongitude.replace(",", "."));



                    LatLng location = new LatLng(db,db2);
                    mMap.addMarker(new MarkerOptions().position(location)).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_ladrao));


                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
        }
