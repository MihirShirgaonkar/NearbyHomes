 package com.aditya.nearbyhomes.detailhome;

import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.core.app.ActivityCompat;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.nearbyhomes.CustomProgressDialogue;
import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInstance;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInterface;
import com.aditya.nearbyhomes.intro.LocationActivity;
import com.aditya.nearbyhomes.register.RegisterActivity;
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
import com.smarteist.autoimageslider.IndicatorView.animation.type.IndicatorAnimationType;
import com.smarteist.autoimageslider.SliderAnimations;
import com.smarteist.autoimageslider.SliderView;

import java.util.ArrayList;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

 public class DetailHomeActivity extends AppCompatActivity {

     Bundle bundle;
     SupportMapFragment smf;
     TextView cityTv,line1Tv,line2Tv,line3Tv,ownerTv;
     LinearLayout callBtn,chatBtn;
     CardView directionBtn;
     private SharedPreferences registrationPreferences;
     private SharedPreferences.Editor registerationPrefsEditor;
     public static final String MyPREFERENCES = "MyPrefs";

     @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_home);


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
         registrationPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
         registerationPrefsEditor = registrationPreferences.edit();


        List<String> sliderImageList = new ArrayList<>();
        sliderImageList.add("http://adityajaitpal22-001-site1.etempurl.com/room_images/sample.jpg");
        sliderImageList.add("http://adityajaitpal22-001-site1.etempurl.com/room_images/sample2.jpg");
        sliderImageList.add("http://adityajaitpal22-001-site1.etempurl.com/room_images/sample3.jpg");

        SliderView sliderView = findViewById(R.id.imageSlider);
        sliderView.setSliderAdapter(new SliderAdapter(sliderImageList, this));

        sliderView.setIndicatorAnimation(IndicatorAnimationType.WORM);
        sliderView.setSliderTransformAnimation(SliderAnimations.SIMPLETRANSFORMATION);
        sliderView.startAutoCycle();

        bundle=getIntent().getExtras();

        String Home_Id = bundle.getString("Home_Id","");
        String User_Id = bundle.getString("User_Id","");
        String Owner_Name = bundle.getString("Owner_Name","");
        String Owner_Phone = bundle.getString("Owner_Phone","");
        String House_Number = bundle.getString("House_Number","");
        String Landmark = bundle.getString("Landmark","");
        String Area = bundle.getString("Area","");
        String City = bundle.getString("City","");
        String District = bundle.getString("District","");
        String State = bundle.getString("State","");
        String Country = bundle.getString("Country","");
        String Pincode = bundle.getString("Pincode","");
        String AddressLine = bundle.getString("AddressLine","");
        String Latitude = bundle.getString("Latitude","");
        String Longitude = bundle.getString("Longitude","");
        String Is_by_location = bundle.getString("Is_by_location","");
        String Room_Type = bundle.getString("Room_Type","");
        String Photo1 = bundle.getString("Photo1","");
        String Photo2 = bundle.getString("Photo2","");
        String Photo3 = bundle.getString("Photo3","");
        String Floor = bundle.getString("Floor","");
        String Building_Name = bundle.getString("Building_Name","");
        String Description = bundle.getString("Description","");
        String Sale_Type = bundle.getString("Sale_Type","");
        String Amount = bundle.getString("Amount","");
        String Sq_foot = bundle.getString("Sq_foot","");


        line1Tv=findViewById(R.id.line1);
        line2Tv=findViewById(R.id.line2);
        line3Tv=findViewById(R.id.line3);
        cityTv=findViewById(R.id.city);
        ownerTv=findViewById(R.id.owner);
        callBtn=findViewById(R.id.call);
        chatBtn=findViewById(R.id.chat);
        directionBtn=findViewById(R.id.direction);

        cityTv.setText(City);
        line1Tv.setText(Sale_Type+" : "+Amount+"/-" );
        line2Tv.setText(Room_Type+" ("+Sq_foot+"sq ft)");
        line3Tv.setText("Address : Floor- "+Floor+", "+Building_Name+", "+Area+", "+City+", "+District+", "+Pincode+", "+State+"\nLandmark : "+Landmark+"\n"+Description);
ownerTv.setText("Owner Name : "+Owner_Name+"\nOwner Phone : "+Owner_Phone);

callBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent = new Intent(Intent.ACTION_DIAL);
        intent.setData(Uri.parse("tel:" + Owner_Phone));
        startActivity(intent);
    }
});

chatBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {

        String User_Name = registrationPreferences.getString("User_Name","NULL");
        String User_Id2 = registrationPreferences.getString("User_Id","NULL");
        CustomProgressDialogue progressDialogue=new CustomProgressDialogue(DetailHomeActivity.this);
        progressDialogue.show();

        RetrofitInterface retrofitInterface =RetrofitInstance.getSeviceData();

        retrofitInterface.addMSG_Connection(User_Id,User_Id2,Owner_Name,User_Name,Owner_Phone,"send",Home_Id)
                .enqueue(new Callback<Map<String, String>>() {
                    @Override
                    public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                        if (response.isSuccessful()){
                            if (response.body().get("POST_REQUEST_STATUS").equals("Success")){



wssappMsg(Owner_Phone,"Hi");
                                progressDialogue.dismiss();
                            }else {
                                errorDialog(DetailHomeActivity.this);
                                progressDialogue.dismiss();
                            }
                        }else {
                            errorDialog(DetailHomeActivity.this);
                            progressDialogue.dismiss();
                        }
                    }

                    @Override
                    public void onFailure(Call<Map<String, String>> call, Throwable t) {

                    }
                });



    }
});



directionBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        if (Is_by_location.equals("Yes")){
try {
    Uri gmmIntentUri = Uri.parse("google.navigation:q="+Latitude+","+Longitude);
    Intent mapIntent = new Intent(Intent.ACTION_VIEW, gmmIntentUri);
    mapIntent.setPackage("com.google.android.apps.maps");
    startActivity(mapIntent);
}catch (Exception e){
    Toast.makeText(DetailHomeActivity.this, "Unable to find Location...", Toast.LENGTH_SHORT).show();
}
        }else {
            Toast.makeText(DetailHomeActivity.this, "Location Not Entered...", Toast.LENGTH_SHORT).show();

        }
    }
});





        if (Is_by_location.equals("Yes")){


            smf = (SupportMapFragment) getSupportFragmentManager().findFragmentById(R.id.google_maps);
            Dexter.withContext(getApplicationContext())
                    .withPermission(Manifest.permission.ACCESS_FINE_LOCATION)
                    .withListener(new PermissionListener() {
                        @Override
                        public void onPermissionGranted(PermissionGrantedResponse permissionGrantedResponse) {
                            getMyLocation(Longitude,Latitude);
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


     private void getMyLocation(String longitude,String latitude) {

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
         Double lo =Double.parseDouble(longitude);
         Double la =Double.parseDouble(latitude);
         smf.getMapAsync(new OnMapReadyCallback() {
             @Override
             public void onMapReady(GoogleMap googleMap) {
                 LatLng latLng=new LatLng(la,lo);
                 MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Your Current Location..!!");

                 googleMap.addMarker(markerOptions);
                 googleMap.animateCamera(CameraUpdateFactory.newLatLngZoom(latLng,17));


             }
         });
     }

     public void back(View view) {
        finish();
     }


     public void errorDialog(Context context) {

         final Dialog dialog = new Dialog(context);
         dialog.setContentView(R.layout.add_user_dialogue);
         dialog.getWindow().setLayout(ViewGroup.LayoutParams.MATCH_PARENT, ViewGroup.LayoutParams.WRAP_CONTENT);
         dialog.setCancelable(false);
         dialog.getWindow().setBackgroundDrawable(context.getDrawable(R.drawable.popup_bg_trans));

         final Button cancle = dialog.findViewById(R.id.btn_cancel);
         Button submit = dialog.findViewById(R.id.btn_submit);

         cancle.setVisibility(View.GONE);
         submit.setText("OK");

         TextView textTv = dialog.findViewById(R.id.text);
         textTv.setText("Failed to send MSG");
         textTv.setTextColor(getResources().getColor(R.color.black));
         ImageView logoIv = dialog.findViewById(R.id.logo);
         logoIv.setImageResource(R.drawable.rejected);


         submit.setOnClickListener(new View.OnClickListener() {
             @Override
             public void onClick(View view) {
                 dialog.dismiss();
             }
         });

         dialog.show();

     }

     public void wssappMsg(String phone,String msg){
         try {

             String url = "https://api.whatsapp.com/send?phone=+91"+phone;
             Intent i = new Intent(Intent.ACTION_VIEW);
             i.setData(Uri.parse(url));
             startActivity(i);
         }catch (Exception e){
             Toast.makeText(DetailHomeActivity.this, "Error", Toast.LENGTH_SHORT).show();
         }
     }
     private boolean whatsappInstalledOrNot(String uri) {
         PackageManager pm = getPackageManager();
         boolean app_installed = false;
         try {
             pm.getPackageInfo(uri, PackageManager.GET_ACTIVITIES);
             app_installed = true;
         } catch (PackageManager.NameNotFoundException e) {
             app_installed = false;
         }
         return app_installed;
     }


 }