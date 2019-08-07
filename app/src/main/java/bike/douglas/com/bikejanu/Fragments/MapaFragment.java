package bike.douglas.com.bikejanu.Fragments;


import android.os.Bundle;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.HeaderViewListAdapter;
import android.widget.Toast;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.MapFragment;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.maps.model.TileOverlay;
import com.google.android.gms.maps.model.TileOverlayOptions;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.maps.android.heatmaps.HeatmapTileProvider;


import java.util.ArrayList;

import bike.douglas.com.bikejanu.Model.LocalBikesMaps;
import bike.douglas.com.bikejanu.R;


public class MapaFragment extends Fragment implements OnMapReadyCallback, GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private ChildEventListener childEventListener;
    private DatabaseReference databaseReference;
    Marker marker;

   // HeatmapTileProvider heatmapTileProvider;
   // TileOverlay tileOverlay;


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
        googleMap.setMapType(GoogleMap.MAP_TYPE_HYBRID);

        databaseReference.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {


                for(DataSnapshot s:dataSnapshot.getChildren()){
                    LocalBikesMaps  map = s.getValue(LocalBikesMaps.class);


                    if(map.latitude.equals("") ||map.longitude.equals("")){



                    }else {

                        String txtLatitude  = map.latitude;
                        String txtLongitude = map.longitude;


                      //  Toast.makeText(MapaFragment.super.getContext(), "Latitude  " + txtLatitude, Toast.LENGTH_LONG).show();


                         double db  =  Double.parseDouble(txtLatitude.replace(",","."));
                         double db2 = Double.parseDouble(txtLongitude.replace(",","."));


                          LatLng location = new LatLng(db,db2);
                         ArrayList<LatLng> list = new ArrayList();
                       //  list.add(new LatLng(db,db2));

                          mMap.addMarker(new MarkerOptions().position(location)).setIcon(BitmapDescriptorFactory.fromResource(R.mipmap.ic_launcher_ladrao));

                        //Move to new location
                          CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).target(location).build();
                          mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));



                          // calor mapa
                         // heatmapTileProvider = new  HeatmapTileProvider.Builder()
                          //        .data(list)
                           //       .build();

                        //  tileOverlay = mMap.addTileOverlay(new TileOverlayOptions().tileProvider(heatmapTileProvider));


                    }

                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });



    }
        }
