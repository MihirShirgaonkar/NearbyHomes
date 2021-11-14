package com.aditya.nearbyhomes.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.Toast;

import com.aditya.nearbyhomes.CustomProgressDialogue;
import com.aditya.nearbyhomes.MainActivity;
import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInstance;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInterface;
import com.google.android.material.textfield.TextInputEditText;

import java.util.List;
import java.util.Map;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class RegisterActivity extends AppCompatActivity {


    Bundle bundle;
    String AddressLine, Country, State, District, Area, Pincode, City, Sub_Localoty, Premises, Latitude, Longitude;
    TextInputEditText nameEt, phoneEt, passFirstEt, passSecondEt;
    Button registerBtn;
    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;

    public static final String MyPREFERENCES = "MyPrefs";

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_register);


        nameEt = findViewById(R.id.name);
        phoneEt = findViewById(R.id.number);
        passFirstEt = findViewById(R.id.password);
        passSecondEt = findViewById(R.id.password2);
        registrationPreferences = getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
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


        bundle = getIntent().getExtras();

        AddressLine = bundle.getString("AddressLine", "NULL");
        Country = bundle.getString("Country", "NULL");
        State = bundle.getString("State", "NULL");
        District = bundle.getString("District", "NULL");
        Area = bundle.getString("Area", "NULL");
        Pincode = bundle.getString("Pincode", "NULL");
        City = bundle.getString("City", "NULL");
        Sub_Localoty = bundle.getString("Sub_Localoty", "NULL");
        Premises = bundle.getString("Premises", "NULL");
        Latitude = bundle.getString("Latitude", "NULL");
        Longitude = bundle.getString("Longitude", "NULL");


        registerBtn = findViewById(R.id.register);
        registerBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                if (verify(nameEt) && verify(phoneEt) && verify(passFirstEt) && verify(passSecondEt) && passwordMatch(passFirstEt, passSecondEt)) {

                    String Name = nameEt.getText().toString();
                    String Phone = phoneEt.getText().toString();
                    String Password = passFirstEt.getText().toString();


                    CustomProgressDialogue progressDialogue = new CustomProgressDialogue(RegisterActivity.this);
                    progressDialogue.show();

                    RetrofitInterface retrofitInterface = RetrofitInstance.getSeviceData();
                    retrofitInterface.registerUser(Name,
                            Phone,
                            "NULL",
                            Password,
                            AddressLine,
                            Country,
                            State,
                            District,
                            Area,
                            Pincode,
                            City,
                            Sub_Localoty,
                            Premises,
                            Latitude,
                            Longitude,
                            "Active",
                            "NULL",
                            "NULL",
                            "NULL",
                            "NULL",
                            "NULL").enqueue(new Callback<Map<String, String>>() {
                        @Override
                        public void onResponse(Call<Map<String, String>> call, Response<Map<String, String>> response) {
                            if (response.isSuccessful()) {
                                if (response.body().get("POST_REQUEST_STATUS").equals("Success")) {



                                    retrofitInterface.verifyLogin(Phone,Password).enqueue(new Callback<List<UserModel>>() {
                                        @Override
                                        public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {
                                            if (response.isSuccessful() && response.body().size() > 0 && response.body().get(0)!=null){
                                                UserModel data = response.body().get(0);


                                                registerationPrefsEditor.putString("User_Id", data.getUser_Id());
                                                registerationPrefsEditor.putString("User_Name", data.getUser_Name());
                                                registerationPrefsEditor.putString("AddressLine", data.getAddressLine());
                                                registerationPrefsEditor.putString("Country", data.getCountry());
                                                registerationPrefsEditor.putString("State", data.getState());
                                                registerationPrefsEditor.putString("District", data.getDistrict());
                                                registerationPrefsEditor.putString("Area", data.getArea());
                                                registerationPrefsEditor.putString("Pincode", data.getPincode());
                                                registerationPrefsEditor.putString("City", data.getCity());
                                                registerationPrefsEditor.putString("Sub_Localoty", data.getSub_Localoty());
                                                registerationPrefsEditor.putString("Premises", data.getPremises());
                                                registerationPrefsEditor.putString("Latitude", data.getLatitude());
                                                registerationPrefsEditor.putString("Longitude", data.getLongitude());
                                                registerationPrefsEditor.putString("User_Status", data.getUser_Status());
                                                registerationPrefsEditor.putString("Remark", data.getRemark());
                                                registerationPrefsEditor.putString("Remark_", data.getRemark_());
                                                registerationPrefsEditor.putString("Extra", data.getExtra());
                                                registerationPrefsEditor.putString("Alternate_Mobile", data.getAlternate_Mobile());
                                                registerationPrefsEditor.putString("Email", data.getEmail());
                                                registerationPrefsEditor.putString("Phone", data.getPhone());
                                                registerationPrefsEditor.putString("Password", data.getPassword());
                                                registerationPrefsEditor.putString("DOB", data.getDOB());
                                                registerationPrefsEditor.putString("Added_On", data.getAdded_On());
                                                registerationPrefsEditor.putString("LoginStatus", "1");
                                                registerationPrefsEditor.putString("Role", "User");
                                                registerationPrefsEditor.apply();



                                                Intent intent=new Intent(RegisterActivity.this, MainActivity.class);
                                                startActivity(intent);
                                                finish();
                                                progressDialogue.dismiss();
                                            }else {
                                                Toast.makeText(RegisterActivity.this, "Try Login", Toast.LENGTH_SHORT).show();
                                                progressDialogue.dismiss();
                                            }
                                        }

                                        @Override
                                        public void onFailure(Call<List<UserModel>> call, Throwable t) {
                                            Toast.makeText(RegisterActivity.this, "Try Login", Toast.LENGTH_SHORT).show();
                                            progressDialogue.dismiss();
                                        }
                                    });





                                } else {
                                    Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();
                                    progressDialogue.dismiss();
                                }
                            } else {
                                Toast.makeText(RegisterActivity.this, "Failed", Toast.LENGTH_SHORT).show();

                                progressDialogue.dismiss();
                            }
                        }

                        @Override
                        public void onFailure(Call<Map<String, String>> call, Throwable t) {
                            progressDialogue.dismiss();
                            Toast.makeText(RegisterActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                        }
                    });


                }

            }
        });


    }

    public void back(View view) {
        finish();
    }


    private boolean verify(TextInputEditText et) {
        boolean value = true;
        String s = et.getText().toString();

        if (s.isEmpty()) {
            value = false;
            et.setError("Error");
        }

        return value;
    }

    private boolean passwordMatch(TextInputEditText et1, TextInputEditText et2) {
        boolean value = false;
        String pass = et1.getText().toString();
        String pass1 = et2.getText().toString();

        if (pass.equals(pass1)) {
            return true;
        }


        et1.setError("Enter Same Password");
        et2.setError("Enter Same Password");
        return value;
    }


    public void login(View view) {
        Intent intent=new Intent(RegisterActivity.this,LoginActivity.class);
        startActivity(intent);
    }
}