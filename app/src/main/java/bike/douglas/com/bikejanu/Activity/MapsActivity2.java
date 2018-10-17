package bike.douglas.com.bikejanu.Activity;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.location.LocationManager;
import android.os.Build;
import android.provider.Settings;
import android.support.v4.app.ActivityCompat;
import android.support.v4.app.FragmentActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.BitmapDescriptorFactory;
import com.google.android.gms.maps.model.CameraPosition;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.Marker;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.firebase.database.DatabaseReference;

import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

import bike.douglas.com.bikejanu.Fragments.Tab2Cadastrar;
import bike.douglas.com.bikejanu.R;

public class MapsActivity2 extends FragmentActivity implements OnMapReadyCallback,android.location.LocationListener,GoogleMap.OnMapClickListener,
        GoogleMap.OnMarkerClickListener {

    private GoogleMap mMap;
    private Marker currentLocationMaker;
    private LatLng currentLocationLatLong;
    private DatabaseReference mDatabase;
    private Location local;
    private Address endereco;
    private LocationManager localManager;
    double latitude  = 0.0;
    double longitude = 0.0;



    private TextView campoEstado;

    private TextView campoCidade;

    private TextView campoBairro;
    private TextView campoRUA;
    private TextView buscando;
    private ProgressBar progressBarBuscando;


    private Button btnLocalizacao;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maps2);
   //    Obtain the SupportMapFragment and gee notified when the map is ready to be used.
       SupportMapFragment mapFragment = (SupportMapFragment) getSupportFragmentManager()
              .findFragmentById(R.id.map);
        mapFragment.getMapAsync(this);
        startGettingLocations();




        campoCidade =(TextView) findViewById(R.id.campoCidadeID);

        campoBairro =(TextView) findViewById(R.id.campoBairroID);

        campoRUA =(TextView) findViewById(R.id.campoRuaID);
        buscando =(TextView) findViewById(R.id.buscandoID);
        progressBarBuscando =(ProgressBar) findViewById(R.id.progressBuscandoID);



        btnLocalizacao = (Button)findViewById(R.id.btnLocalizacaoID);





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
        mMap.setOnMapClickListener(this);

    }
    @Override
    public void onLocationChanged(Location location) {
        //Add marker
        currentLocationLatLong = new LatLng(location.getLatitude(), location.getLongitude());
        MarkerOptions markerOptions = new MarkerOptions();
        markerOptions.position(currentLocationLatLong);
        markerOptions.title("Localização atual");
        markerOptions.icon(BitmapDescriptorFactory.defaultMarker(BitmapDescriptorFactory.HUE_RED));
        currentLocationMaker = mMap.addMarker(markerOptions);


        if (currentLocationMaker!=null){

            buscando.setVisibility(View.GONE);
            progressBarBuscando.setVisibility(View.GONE);


        }

        //Move to new location
        CameraPosition cameraPosition = new CameraPosition.Builder().zoom(15).target(currentLocationLatLong).build();
        mMap.animateCamera(CameraUpdateFactory.newCameraPosition(cameraPosition));


       // Toast.makeText(this, "Localização atualizada", Toast.LENGTH_SHORT).show();




         latitude=location.getLatitude();
         longitude=location.getLongitude();
             try {
                 endereco = localizacaoCorrente(latitude,longitude);



          } catch (IOException e) {
             e.printStackTrace();
         }




        campoCidade.setText(endereco.getSubAdminArea());
        campoBairro.setText(endereco.getSubLocality());
        campoRUA.setText(endereco.getThoroughfare());





        // enviar localizaçao par a tela Alerta_Furto_Roubo  e Cadastro Bike
        btnLocalizacao.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {



                if(AlertarFurtoRoubo.ativa.equals("positiva")){

                    //seta os campos na tela AlertaFUrtosRoubos
                    AlertarFurtoRoubo.alertaEstado.setText(endereco.getAdminArea());
                    AlertarFurtoRoubo.alertaCidade.setText(endereco.getSubAdminArea());
                    AlertarFurtoRoubo.alertaBairro.setText(endereco.getSubLocality());
                    AlertarFurtoRoubo.alertaRua.setText(endereco.getThoroughfare());

                    Intent intent = new Intent(MapsActivity2.this, AlertarFurtoRoubo.class);
                    intent.putExtras(intent);

                    AlertarFurtoRoubo.ativa="negativa";


                    MapsActivity2.this.finish();


                }else{


                    //seta os campos na tela CadastroBike
                    CadastroBike.cadastroEstado.setText(endereco.getAdminArea());
                    CadastroBike.cadastroCidade.setText(endereco.getSubAdminArea());
                    CadastroBike.cadastroBairro.setText(endereco.getSubLocality());
                    CadastroBike.cadastroRua.setText(endereco.getThoroughfare());


                    Intent intent = new Intent(MapsActivity2.this, CadastroBike.class);
                    intent.putExtras(intent);

                    MapsActivity2.this.finish();
                }




            }
        });






    }
    private ArrayList findUnAskedPermissions (ArrayList < String > wanted) {
        ArrayList result = new ArrayList();
        for (String perm : wanted) {
            if (!hasPermission(perm)) {
                result.add(perm);
            }
        }
        return result;
    }
    private boolean hasPermission (String permission){
        if (canAskPermission()) {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                return (checkSelfPermission(permission) == PackageManager.PERMISSION_GRANTED);
            }
        }
        return true;
    }
    private boolean canAskPermission () {
        return (Build.VERSION.SDK_INT > Build.VERSION_CODES.LOLLIPOP_MR1);
    }
    public void showSettingsAlert () {
        AlertDialog.Builder alertDialog = new AlertDialog.Builder(this);
        alertDialog.setTitle("GPS desativado!");
        alertDialog.setMessage("Ativar GPS?");
        alertDialog.setPositiveButton("Sim", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {

                MapsActivity2.this.finish();
                Intent intent = new Intent(Settings.ACTION_LOCATION_SOURCE_SETTINGS);
                startActivity(intent);

            }
        });
        alertDialog.setNegativeButton("Não", new DialogInterface.OnClickListener() {
            public void onClick(DialogInterface dialog, int which) {
                dialog.cancel();
            }
        });
        alertDialog.show();
    }
    private void startGettingLocations () {
        LocationManager lm = (LocationManager) getSystemService(Context.LOCATION_SERVICE);
        boolean isGPS = lm.isProviderEnabled(LocationManager.GPS_PROVIDER);
        boolean isNetwork = lm.isProviderEnabled(LocationManager.NETWORK_PROVIDER);
        boolean canGetLocation = true;
        int ALL_PERMISSIONS_RESULT = 101;
        long MIN_DISTANCE_CHANGE_FOR_UPDATES = 10;// Distance in meters
        long MIN_TIME_BW_UPDATES = 1000 * 10;// Time in milliseconds
        ArrayList<String> permissions = new ArrayList<>();
        ArrayList<String> permissionsToRequest;
        permissions.add(android.Manifest.permission.ACCESS_FINE_LOCATION);
        permissions.add(android.Manifest.permission.ACCESS_COARSE_LOCATION);
        permissionsToRequest = findUnAskedPermissions(permissions);
        //Check if GPS and Network are on, if not asks the user to turn on
        if (!isGPS && !isNetwork) {
            showSettingsAlert();

        } else {
            // check permissions
            // check permissions for later versions
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
                if (permissionsToRequest.size() > 0) {
                    requestPermissions(permissionsToRequest.toArray(new String[permissionsToRequest.size()]),
                            ALL_PERMISSIONS_RESULT);
                    canGetLocation = false;
                }
            }
        }
        //Checks if FINE LOCATION and COARSE Location were granted
        if (ActivityCompat.checkSelfPermission(this,
                android.Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED &&
                ActivityCompat.checkSelfPermission(this, android.Manifest.permission.ACCESS_COARSE_LOCATION)
                        != PackageManager.PERMISSION_GRANTED) {
            Toast.makeText(this, "Permissão negada", Toast.LENGTH_SHORT).show();
            return;
        }
        //Starts requesting location updates
        if (canGetLocation) {
            if (isGPS) {
                lm.requestLocationUpdates(
                        LocationManager.GPS_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            } else if (isNetwork) {
                // from Network Provider
                lm.requestLocationUpdates(
                        LocationManager.NETWORK_PROVIDER,
                        MIN_TIME_BW_UPDATES,
                        MIN_DISTANCE_CHANGE_FOR_UPDATES, this);
            }
        } else {
            Toast.makeText(this, "Não é possível obter a localização", Toast.LENGTH_SHORT).show();
        }
    }
    @Override
    public void onStatusChanged (String provider,int status, Bundle extras){
    }
    @Override
    public void onProviderEnabled (String provider){
    }
    @Override
    public void onProviderDisabled (String provider){
    }
    @Override
    public void onMapClick(LatLng latLng) {
        //limpa a localição inicial
        if(currentLocationMaker !=null){
            currentLocationMaker.remove();
        }
        //limpa as demais localiçaes
        mMap.clear();
        LatLng novaLocalizacao = new LatLng(latLng.latitude, latLng.longitude);
        mMap.addMarker(new MarkerOptions().position(novaLocalizacao).title("Nova Localização"));
        latitude= novaLocalizacao.latitude;
        longitude=novaLocalizacao.longitude;
        // Toast.makeText(this, "LATITUDE"+latLng.toString(), Toast.LENGTH_SHORT).show();
        try {

            endereco = novoEndereco(latitude,longitude);

            // seta os locais nos campos

            campoCidade.setText(endereco.getSubAdminArea());
            campoBairro.setText(endereco.getSubLocality());
            campoRUA.setText(endereco.getThoroughfare());



            //// até aq




            // enviar localizaçao par a tela Alerta_Furto_Roubo  e Cadastro Bike
            btnLocalizacao.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {



                    if(AlertarFurtoRoubo.ativa.equals("positiva")){

                        //seta os campos na tela AlertaFUrtosRoubos
                        AlertarFurtoRoubo.alertaEstado.setText(endereco.getAdminArea());
                        AlertarFurtoRoubo.alertaCidade.setText(endereco.getSubAdminArea());
                        AlertarFurtoRoubo.alertaBairro.setText(endereco.getSubLocality());
                        AlertarFurtoRoubo.alertaRua.setText(endereco.getThoroughfare());

                        Intent intent = new Intent(MapsActivity2.this, AlertarFurtoRoubo.class);
                        intent.putExtras(intent);

                        AlertarFurtoRoubo.ativa="negativa";


                        MapsActivity2.this.finish();


                    }else{


                        //seta os campos na tela CadastroBike
                        CadastroBike.cadastroEstado.setText(endereco.getAdminArea());
                        CadastroBike.cadastroCidade.setText(endereco.getSubAdminArea());
                        CadastroBike.cadastroBairro.setText(endereco.getSubLocality());
                        CadastroBike.cadastroRua.setText(endereco.getThoroughfare());


                        Intent intent = new Intent(MapsActivity2.this, CadastroBike.class);
                        intent.putExtras(intent);

                        MapsActivity2.this.finish();
                    }




                }
            });


            //// até aq




        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public Address localizacaoCorrente(double latitude,double longitude)throws IOException{
        Geocoder geocoder;
        Address address =null;
        List<Address> addresses;
        geocoder = new Geocoder(getApplicationContext());
        addresses =  geocoder.getFromLocation(latitude,longitude,1);
        if(addresses.size()>0){
            address=addresses.get(0);
        }
        return address;
    }


    public Address novoEndereco (double latitude, double longitude)throws IOException{
        Geocoder geocoder;
        Address address =null;
        List<Address> addresses;
        geocoder = new Geocoder(getApplicationContext());
        addresses =  geocoder.getFromLocation(latitude,longitude,1);
        if(addresses.size()>0){
            address=addresses.get(0);
        }
        return address;
    }
    @Override
    public boolean onMarkerClick(Marker marker) {
        return false;
    }


}
