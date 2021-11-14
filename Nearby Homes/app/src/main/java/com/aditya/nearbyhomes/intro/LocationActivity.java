package com.aditya.nearbyhomes.intro;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.TextView;

import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.register.RegisterActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.SupportMapFragment;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;

import java.util.List;
import java.util.Locale;

public class LocationActivity extends AppCompatActivity {


    SupportMapFragment smf;
    FusedLocationProviderClient client;
    TextView localityTv,addressTv;
    Button conformBTN;

    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefs";




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_location);

        registrationPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();



        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.KITKAT) {
            Window w = getWindow();
            w.setFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS, WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);
        }

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.M) {
            View decor = getWindow().getDecorView();
            if (true) {
                decor.setSystemUiVisibility(View.SYSTEM_UI_FLAG_LIGHT_STATUS_BAR);
            } else {
                // We want to change tint color to white again.
                // You can also record the flags in advance so that you can turn UI back completely if
                // you have set other flags before, such as translucent or full screen.
                decor.setSystemUiVisibility(0);
            }
        }

        localityTv=findViewById(R.id.locality);
        addressTv=findViewById(R.id.address);


        smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);
        client = LocationServices.getFusedLocationProviderClient(this);


        Dexter.withContext(getApplicationContext())
                .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                .withListener(new PermissionListener() {
                    @Override
                    public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                        getMyLocation();
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

    private void getMyLocation() {

        if (ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(this, Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
            // TODO: Consider calling
            //    ActivityCompat#requestPermissions
            // here to request the missing permissions, and then overriding
            //   public void onRequestPermissionsResult(int requestCode, String[] permissions,
            //                                          int[] grantResults)
            // to handle the case where the user grants the permission. See the documentation
            // for ActivityCompat#requestPermissions for more details.
            return;
        }
        Task<Location> task = client.getLastLocation();
        task.addOnSuccessListener(new OnSuccessListener<Location>() {
            @Override
            public void onSuccess(Location location) {
                smf.getMapAsync(new OnMapReadyCallback() {
                    @Override
                    public void onMapReady(GoogleMap googleMap) {
                        LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                        MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Your Current Location..!!");

                        googleMap.addMarker(markerOptions);
                        googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));

                        try {
                            Geocoder geo = new Geocoder(LocationActivity.this.getApplicationContext(), Locale.getDefault());
                            List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
                            localityTv.setText("Loading...");
                            if (addresses != null && addresses.size() > 0) {
                                String AddressLine = addresses.get(0).getAddressLine(0);
                                String Country = addresses.get(0).getCountryName();
                                String State = addresses.get(0).getAdminArea();
                                String District = addresses.get(0).getSubAdminArea();
                                String Area = addresses.get(0).getFeatureName();
                                String Pincode = addresses.get(0).getPostalCode();
                                String City = addresses.get(0).getLocality();
                                String sub_localoty = addresses.get(0).getSubLocality();
                                String Premises = addresses.get(0).getPremises();
                                String Latitude = String.valueOf(addresses.get(0).getLatitude());
                                String Longitude = String.valueOf(addresses.get(0).getLongitude());



                                if (AddressLine != null && Country != null) {
                                    localityTv.setText(City);
                                    addressTv.setText(AddressLine);
                                    conformBTN=findViewById(R.id.conform);

                                    conformBTN.setOnClickListener(new View.OnClickListener() {
                                        @Override
                                        public void onClick(View view) {
                                            Intent intent=new Intent(LocationActivity.this, RegisterActivity.class);
                                            intent.putExtra("AddressLine",AddressLine);
                                            intent.putExtra("Country",Country);
                                            intent.putExtra("State",State);
                                            intent.putExtra("District",District);
                                            intent.putExtra("Area",Area);
                                            intent.putExtra("Pincode",Pincode);
                                            intent.putExtra("City",City);
                                            intent.putExtra("Sub_Localoty",sub_localoty);
                                            intent.putExtra("Premises",Premises);
                                            intent.putExtra("Latitude",Latitude);
                                            intent.putExtra("Longitude",Longitude);
                                            startActivity(intent);
                                        }
                                    });

//                                    addressTv.setText( ", " + (sub_localoty != null ? sub_localoty + ", " : "")  + (locality_city != null ? locality_city + ", " : "" ) + (city != null ? city + ", " : "")  + (sub_admin != null ? sub_admin + ", " : "") + (state != null ? state + ", " : "") + country + ", " + (pincode != null ? pincode : ""));
                                } else {
                                    addressTv.setText("Location could not be fetched...");
                                }
                            }
                        } catch (Exception e) {
                            addressTv.setText("Location could not be fetched...");
//                            e.printStackTrace(); // getFromLocation() may sometimes fail
                        }
                    }
                });
            }
        });
    }

    public void back(View view) {
        finish();
    }
}