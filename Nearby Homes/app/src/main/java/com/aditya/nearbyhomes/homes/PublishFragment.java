package com.aditya.nearbyhomes.homes;

import static android.app.Activity.RESULT_OK;

import android.Manifest;
import android.app.Dialog;
import android.content.Context;
import android.content.CursorLoader;
import android.content.Intent;
import android.content.SharedPreferences;
import android.content.pm.PackageManager;
import android.database.Cursor;
import android.graphics.Bitmap;
import android.location.Address;
import android.location.Geocoder;
import android.location.Location;
import android.net.Uri;
import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.core.app.ActivityCompat;
import androidx.fragment.app.Fragment;


import android.provider.MediaStore;
import android.provider.OpenableColumns;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.nearbyhomes.CustomProgressDialogue;
import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInstance;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInterface;
import com.aditya.nearbyhomes.intro.LocationActivity;
import com.aditya.nearbyhomes.register.RegisterActivity;
import com.google.android.gms.location.FusedLocationProviderClient;
import com.google.android.gms.location.LocationServices;
import com.google.android.gms.maps.CameraUpdateFactory;
import com.google.android.gms.maps.GoogleMap;
import com.google.android.gms.maps.OnMapReadyCallback;
import com.google.android.gms.maps.model.LatLng;
import com.google.android.gms.maps.model.MarkerOptions;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.textfield.TextInputEditText;
import com.karumi.dexter.Dexter;
import com.karumi.dexter.PermissionToken;
import com.karumi.dexter.listener.PermissionDeniedResponse;
import com.karumi.dexter.listener.PermissionGrantedResponse;
import com.karumi.dexter.listener.PermissionRequest;
import com.karumi.dexter.listener.single.PermissionListener;
import com.makeramen.roundedimageview.RoundedImageView;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Base64;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;


public class PublishFragment extends Fragment {


    Button imgBtn1,imgBtn2,imgBtn3,uploadBtn;
//    String Image1Path ="";
    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefs";
    Spinner dealType;
    private final int IMAGE_REQUEST_1 = 1;
    private final int IMAGE_REQUEST_2 = 2;
    private final int IMAGE_REQUEST_3 = 3;
    TextView imgText1,imgText2,imgText3;
    RoundedImageView img1,img2,img3;
    Bitmap bitmap;
    String ImageName1 ="NULL";
    String ImageName2 ="NULL";
    String ImageName3 ="NULL";
    TextView locationBtn;
    FusedLocationProviderClient client;
    String AddressLine,Latitude,Longitude,Is_by_gps;

    TextInputEditText buildingEt,squarefootEt,areaEt,floorEt,landmarkEt,localityEt,pincodeEt,cityEt,districtEt,stateEt,amountEt
            ,descEt;

    Button submitBtn;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View v= inflater.inflate(R.layout.fragment_publish, container, false);


        AddressLine="NULL";
        Latitude="NULL";
        Longitude="NULL";
        Is_by_gps="No";


dealType=v.findViewById(R.id.spinner);
        ArrayList<String> list2=new ArrayList<>();
        list2.add("--Select Deal--");
        list2.add("Rent");
        list2.add("Sale");
        ArrayAdapter<String> adapter2=new ArrayAdapter<>(getActivity(),R.layout.spinner_layout,list2);
        adapter2.setDropDownViewResource(R.layout.spinner_dropdown_first);
        dealType.setAdapter(adapter2);


        locationBtn=v.findViewById(R.id.location);
        uploadBtn=v.findViewById(R.id.upload);
        buildingEt=v.findViewById(R.id.building);
        squarefootEt=v.findViewById(R.id.sqft);
        areaEt=v.findViewById(R.id.area);
        floorEt=v.findViewById(R.id.floor);
        landmarkEt=v.findViewById(R.id.landmark);
        localityEt=v.findViewById(R.id.locality);
        pincodeEt=v.findViewById(R.id.pincode);
        cityEt=v.findViewById(R.id.city);
        districtEt=v.findViewById(R.id.dist);
        stateEt=v.findViewById(R.id.state);
        amountEt=v.findViewById(R.id.amount);
        descEt=v.findViewById(R.id.desc);


        img1=v.findViewById(R.id.img1);
        img2=v.findViewById(R.id.img2);
        img3=v.findViewById(R.id.img3);

        imgText1=v.findViewById(R.id.imgtext1);
        imgText2=v.findViewById(R.id.imgtext2);
        imgText3=v.findViewById(R.id.imgtext3);

        imgBtn1=v.findViewById(R.id.imgbtn1);
        imgBtn1.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMAGE_REQUEST_1);
            }
        });
         imgBtn2=v.findViewById(R.id.imgbtn2);
        imgBtn2.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMAGE_REQUEST_2);
            }
        });
         imgBtn3=v.findViewById(R.id.imgbtn3);
        imgBtn3.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent();
                intent.setType("image/*");
                intent.setAction(Intent.ACTION_GET_CONTENT);
                startActivityForResult(intent,IMAGE_REQUEST_3);
            }
        });
        registrationPreferences = getContext().getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();


        locationBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                client = LocationServices.getFusedLocationProviderClient(getContext());

                Dexter.withContext(getContext())
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
        });

   submitBtn=v.findViewById(R.id.register);
        submitBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (verify(buildingEt) && verify(squarefootEt) && verify(areaEt) && verify(floorEt) &&
                 verify(localityEt) && verify(pincodeEt) && verify(cityEt) && verify(districtEt) &&
                 verify(stateEt) && verify(amountEt)  && verify(descEt)  && !dealType.getSelectedItem().toString().equals("--Select Deal--")){

                    String Building =buildingEt.getText().toString();
                    String Landmark =landmarkEt.getText().toString();
                    String Square_Foot =squarefootEt.getText().toString();
                    String Area =areaEt.getText().toString();
                    String Floor =floorEt.getText().toString();
                    String Locality =localityEt.getText().toString();
                    String Pincode =pincodeEt.getText().toString();
                    String City =cityEt.getText().toString();

                    String District =districtEt.getText().toString();
                    String  State=stateEt.getText().toString();
                    String  Amount=amountEt.getText().toString();
                    String  Desc=descEt.getText().toString();
                    String User_Name=registrationPreferences.getString("User_Name","NULL");
                    String User_Id=registrationPreferences.getString("User_Id","NULL");
                    String Phone=registrationPreferences.getString("Phone","NULL");


                    RetrofitInterface retrofitInterface= RetrofitInstance.getSeviceData();

CustomProgressDialogue progressDialogue =new CustomProgressDialogue(getContext());
progressDialogue.show();

                    retrofitInterface.addHome(User_Id,User_Name,Phone,"NULL",Landmark,Locality,City,District,State,
                            "India",Pincode,AddressLine,Latitude,Longitude,Is_by_gps,Area,ImageName1,ImageName2,ImageName3,"Yes",
                            Floor,Building,Desc,dealType.getSelectedItem().toString(),Amount,Square_Foot,"NULL","NULL","NULL"
                    ,"NULL","NULL","NULL").enqueue(new Callback<Map<String, String>>() {
                        @Override
                        public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                            if (response.isSuccessful()){
                                if (response.body().get("POST_REQUEST_STATUS").equals("Success")){
                                    successDialog(getContext());
                                    progressDialogue.dismiss();
                                }else {
                                    errorDialog(getContext());
                                    progressDialogue.dismiss();
                                }
                            }else {
                                errorDialog(getContext());
                                progressDialogue.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String, String>> call, Throwable t) {
progressDialogue.dismiss();errorDialog(getContext());
                        }
                    });


                }


            }
        });


uploadBtn.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
uploadImage();
    }
});

        return v;
    }

    private void uploadImage() {

        ByteArrayOutputStream byteArrayOutputStream=new ByteArrayOutputStream();
        bitmap.compress(Bitmap.CompressFormat.JPEG,75,byteArrayOutputStream);
        byte[] imageInByte = byteArrayOutputStream.toByteArray();

         

        RetrofitInterface retrofitInterface =RetrofitInstance.getSeviceData();
//        Toast.makeText(getContext(), "proceed", Toast.LENGTH_SHORT).show();

        retrofitInterface.UploadFile2(imageInByte,ImageName1).enqueue(new Callback<Map<String, String>>() {
            @Override
            public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                if (response.isSuccessful()){
                    if (response.body().get("POST_REQUEST_STATUS").equals("Success")){
                        successDialog(getContext());

                    }else {
                        Toast.makeText(getContext(), "Fail1", Toast.LENGTH_SHORT).show();

                    }
                }else {
                    Toast.makeText(getContext(), "Fail2", Toast.LENGTH_SHORT).show();

                }
            }

            @Override
            public void onFailure(Call<Map<String, String>> call, Throwable t) {
                Toast.makeText(getContext(), "Fail", Toast.LENGTH_SHORT).show();
            }
        });
    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode==IMAGE_REQUEST_1 && resultCode ==RESULT_OK  && data!=null){


            Uri path= data.getData();

            ImageName1 = getNameFromURI(path);
            try {



                bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),path);
                img1.setImageBitmap(bitmap);
                imgText1.setText(ImageName1);
//                Image1Path=getRealPathFromUrl(path);
//                Toast.makeText(getContext(), Image1Path, Toast.LENGTH_SHORT).show();



            } catch (IOException e) {
                e.printStackTrace();
            }

        }



         if (requestCode==IMAGE_REQUEST_2 && resultCode ==RESULT_OK  && data!=null){


            Uri path= data.getData();
             ImageName2 = getNameFromURI(path);
            try {



                bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),path);
                img2.setImageBitmap(bitmap);
                imgText2.setText(ImageName2);



            } catch (IOException e) {
                e.printStackTrace();
            }

        }



         if (requestCode==IMAGE_REQUEST_3 && resultCode ==RESULT_OK  && data!=null){


            Uri path= data.getData();
             ImageName3 = getNameFromURI(path);
            try {



                bitmap= MediaStore.Images.Media.getBitmap(getContext().getContentResolver(),path);
                img3.setImageBitmap(bitmap);
                imgText3.setText(ImageName3);


            } catch (IOException e) {
                e.printStackTrace();
            }

        }





    }
    public String getNameFromURI(Uri uri) {
        Cursor c = getContext().getContentResolver().query(uri, null, null, null, null);
        c.moveToFirst();
        return c.getString(c.getColumnIndex(OpenableColumns.DISPLAY_NAME));
    }

    private boolean verify(TextInputEditText et) {
        boolean value=true;
        String s=et.getText().toString();

        if (s.isEmpty()){
            value  =false;
            et.setError("Error");
        }

        return value;
    }
private String getRealPathFromUrl(Uri uri){
        String[] projection ={MediaStore.Images.Media.DATA};
    CursorLoader loader = new CursorLoader(getActivity(),uri,projection,null,null,null);
    Cursor cursor=loader.loadInBackground();
    int column_idx=cursor.getColumnIndexOrThrow(MediaStore.Images.Media.DATA);
    cursor.moveToFirst();
    String result =cursor.getString(column_idx);
    cursor.close();
    return result;
}

    public void successDialog(Context context) {
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
        textTv.setText("Registration Successful.");
        textTv.setTextColor(getResources().getColor(R.color.green));
        ImageView logoIv = dialog.findViewById(R.id.logo);
        logoIv.setImageResource(R.drawable.checked);


        submit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                dialog.dismiss();
//                buildingEt,squarefootEt,areaEt,floorEt,landmarkEt,localityEt,pincodeEt,cityEt,districtEt,stateEt,amountEt
//                        ,descEt;

                buildingEt.setText("");
                squarefootEt.setText("");
                areaEt.setText("");
                floorEt.setText("");
                landmarkEt.setText("");
                localityEt.setText("");
                pincodeEt.setText("");
                cityEt.setText("");
                districtEt.setText("");
                stateEt.setText("");
                amountEt.setText("");
                descEt.setText("");
            }
        });

        dialog.show();
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
        textTv.setText("Registration Failed");
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


    private void getMyLocation() {

        if (ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_FINE_LOCATION) != PackageManager.PERMISSION_GRANTED && ActivityCompat.checkSelfPermission(getContext(), Manifest.permission.ACCESS_COARSE_LOCATION) != PackageManager.PERMISSION_GRANTED) {
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
                LatLng latLng=new LatLng(location.getLatitude(),location.getLongitude());
                MarkerOptions markerOptions=new MarkerOptions().position(latLng).title("Your Current Location..!!");



                try {
                    Geocoder geo = new Geocoder(getContext().getApplicationContext(), Locale.getDefault());
                    List<Address> addresses = geo.getFromLocation(location.getLatitude(), location.getLongitude(), 1);
//                    localityTv.setText("Loading...");
                    if (addresses != null && addresses.size() > 0) {
                         AddressLine = addresses.get(0).getAddressLine(0);
                        String Country = addresses.get(0).getCountryName();
                        String State = addresses.get(0).getAdminArea();
                        String District = addresses.get(0).getSubAdminArea();
                        String Area = addresses.get(0).getFeatureName();
                        String Pincode = addresses.get(0).getPostalCode();
                        String City = addresses.get(0).getLocality();
                        String sub_localoty = addresses.get(0).getSubLocality();
                        String Premises = addresses.get(0).getPremises();
                         Latitude = String.valueOf(addresses.get(0).getLatitude());
                         Longitude = String.valueOf(addresses.get(0).getLongitude());
                        Is_by_gps="Yes";



                        if (AddressLine != null && Country != null) {

                            stateEt.setText(State);
                            districtEt.setText(District);
                            buildingEt.setText(Premises);
                            localityEt.setText(Area);
                            pincodeEt.setText(Pincode);
                            cityEt.setText(City);


//                                    addressTv.setText( ", " + (sub_localoty != null ? sub_localoty + ", " : "")  + (locality_city != null ? locality_city + ", " : "" ) + (city != null ? city + ", " : "")  + (sub_admin != null ? sub_admin + ", " : "") + (state != null ? state + ", " : "") + country + ", " + (pincode != null ? pincode : ""));
                        } else {
//                            addressTv.setText("Location could not be fetched...");
                        }
                    }
                } catch (Exception e) {
//                    addressTv.setText("Location could not be fetched...");
//                            e.printStackTrace(); // getFromLocation() may sometimes fail
                }



            }
        });
    }

}