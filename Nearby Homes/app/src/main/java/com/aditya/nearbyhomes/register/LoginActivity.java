package com.aditya.nearbyhomes.register;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Build;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.aditya.nearbyhomes.CustomProgressDialogue;
import com.aditya.nearbyhomes.MainActivity;
import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInstance;
import com.aditya.nearbyhomes.RetrofitAccessService.RetrofitInterface;
import com.aditya.nearbyhomes.intro.LocationActivity;
import com.google.android.material.textfield.TextInputLayout;


import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class LoginActivity extends AppCompatActivity  {


    Button signin,demosubmit,otp_login;
    EditText usernameEt,passwordEt;
    TextView regTv;

    RetrofitInterface retrofitInterface;
    List<UserModel>  UserModelList;

    LinearLayout otp_layout;
    TextInputLayout inputLayoutPhone;

    CustomProgressDialogue progressDialogue ;
Spinner spinner;
String spinnerItems[]  =    {
        "Customer",
        "Admin"
};


ArrayAdapter<CharSequence>  spinnerAdaper;

    private SharedPreferences registrationPreferences;
    private SharedPreferences.Editor registerationPrefsEditor;
    public static final String MyPREFERENCES = "MyPrefs";


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
//         In Activity's onCreate() for instance
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

        registrationPreferences = this.getSharedPreferences(MyPREFERENCES, Context.MODE_PRIVATE);
        registerationPrefsEditor = registrationPreferences.edit();


        String LoginStatus = registrationPreferences.getString("LoginStatus", "");

        if (LoginStatus!=null && LoginStatus.equals("1")){
            Intent intent =new Intent(LoginActivity.this, MainActivity.class);
            startActivity(intent);
            finish();
        }

        regTv=findViewById(R.id.reg);
regTv.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        Intent intent=new Intent(LoginActivity.this, LocationActivity.class);
        startActivity(intent);

    }
});


        progressDialogue=new CustomProgressDialogue(LoginActivity.this);
        signin=findViewById(R.id.register);
        usernameEt=findViewById(R.id.number);
        passwordEt=findViewById(R.id.password);
        retrofitInterface = RetrofitInstance.getSeviceData();
        signin.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {

                verify(usernameEt);
                verify(passwordEt);
                String password = passwordEt.getText().toString();

                String username = usernameEt.getText().toString();
                if (verify(passwordEt)  &&  verify(usernameEt)) {


//                    if (spinner.getSelectedItem().toString().equals("Customer")) {


                        progressDialogue.show();


                        UserModelList = new ArrayList<>();

                        Log.d("Credentials", "onClick: " + username);
                        Log.d("Credentials", "onClick: " + password);

                        retrofitInterface.verifyLogin(username, password).enqueue(new Callback<List<UserModel>>() {
                            @Override
                            public void onResponse(Call<List<UserModel>> call, Response<List<UserModel>> response) {

                                if (response.isSuccessful()) {

                                    UserModelList = response.body();

                                    if (UserModelList.size() > 0) {

                                        UserModel data = UserModelList.get(0);


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
//                                    registerationPrefsEditor.commit();


                                        Intent intent = new Intent(LoginActivity.this, MainActivity.class);
                                        startActivity(intent);
                                        finish();

                                        progressDialogue.dismiss();


                                    } else {


                                        progressDialogue.dismiss();
                                        Toast.makeText(LoginActivity.this, "Failed to Connect Server!", Toast.LENGTH_SHORT).show();

                                    }
                                } else {
                                    progressDialogue.dismiss();
                                    Toast.makeText(LoginActivity.this, "Failed to Connect Server", Toast.LENGTH_SHORT).show();
                                }

                            }

                            @Override
                            public void onFailure(Call<List<UserModel>> call, Throwable t) {
                                Toast.makeText(LoginActivity.this, t.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
                                progressDialogue.dismiss();
                            }
                        });

//                    } else if (spinner.getSelectedItem().toString().equals("Admin")) {
//                        Toast.makeText(LoginActivity.this, "started", Toast.LENGTH_SHORT).show();





                    }

                }

//            }


        });




//        usernameEt.addTextChangedListener(new TextWatcher() {
//            @Override
//            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
//
//            }
//
//            @Override
//            public void onTextChanged(CharSequence s, int start, int before, int count) {
//
//            }
//
//            @Override
//            public void afterTextChanged(Editable s) {
//                int txtSize =   s.length();
//                if (txtSize==10){
//                    demosubmit.setEnabled(true);
//                    demosubmit.setBackground(getDrawable(R.drawable.button_bg));
//                }else {
//                    demosubmit.setEnabled(false);
//
//                    demosubmit.setBackground(getDrawable(R.drawable.button_disable_bg));
//                }
//            }
//        });


    }

    private boolean verify(EditText et) {
        boolean value=true;
        String s=et.getText().toString();

        if (s.isEmpty()){
            value  =false;
            et.setError("Error");
        }

        return value;
    }

    public void register_now(View view) {
        Intent intent=new Intent(LoginActivity.this, RegisterActivity.class);
        startActivity(intent);
    }

    public void back_(View view) {
        finish();
    }
}
