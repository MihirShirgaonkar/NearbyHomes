package com.aditya.nearbyhomes.RetrofitAccessService;


import com.aditya.nearbyhomes.homes.HomeModel;
import com.aditya.nearbyhomes.messages.MSGConnectionModel;
import com.aditya.nearbyhomes.register.UserModel;

import java.util.List;
import java.util.Map;

import okhttp3.MultipartBody;
import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Multipart;
import retrofit2.http.POST;
import retrofit2.http.Part;
import retrofit2.http.Query;

public interface RetrofitInterface {

    @GET("customer.asmx/verifyLogin")
    Call<List<UserModel>> verifyLogin(@Query("Phone") String Phone,
                                      @Query("password") String password);


    @GET("customer.asmx/registerUser")
    Call<Map<String, String>> registerUser(
            @Query("User_Name") String User_Name,
            @Query("Phone") String Phone,
            @Query("Email") String Email,
            @Query("Password") String Password,
            @Query("AddressLine") String AddressLine,
            @Query("Country") String Country,
            @Query("State") String State,
            @Query("District") String District,
            @Query("Area") String Area,
            @Query("Pincode") String Pincode,
            @Query("City") String City,
            @Query("Sub_Localoty") String Sub_Localoty,
            @Query("Premises") String Premises,
            @Query("Latitude") String Latitude,
            @Query("Longitude") String Longitude,
            @Query("User_Status") String User_Status,
            @Query("Remark") String Remark,
            @Query("Remark_") String Remark_,
            @Query("Extra") String Extra,
            @Query("DOB") String DOB,
            @Query("Alternate_Mobile") String Alternate_Mobile);

    @GET("customer.asmx/addHome")
    Call<Map<String, String>> addHome(
            @Query("User_Id") String User_Id,
            @Query("Owner_Name") String Owner_Name,
            @Query("Owner_Phone") String Owner_Phone,
            @Query("House_Number") String House_Number,
            @Query("Landmark") String Landmark,
            @Query("Area") String Area,
            @Query("City") String City,
            @Query("District") String District,
            @Query("State") String State,
            @Query("Country") String Country,
            @Query("Pincode") String Pincode,
            @Query("AddressLine") String AddressLine,
            @Query("Latitude") String Latitude,
            @Query("Longitude") String Longitude,
            @Query("Is_by_location") String Is_by_location,
            @Query("Room_Type") String Room_Type,
            @Query("Photo1") String Photo1,
            @Query("Photo2") String Photo2,
            @Query("Photo3") String Photo3,
            @Query("Room_Availability") String Room_Availability,
            @Query("Floor") String Floor,
            @Query("Building_Name") String Building_Name,
            @Query("Description") String Description,
            @Query("Sale_Type") String Sale_Type,
            @Query("Amount") String Amount,
            @Query("Sq_foot") String Sq_foot,
            @Query("Water_Availability") String Water_Availability,
            @Query("Lift_Availability") String Lift_Availability,
            @Query("Parking_Availability") String Parking_Availability,
            @Query("Remark") String Remark,
            @Query("Remark_") String Remark_,
            @Query("Direction") String Direction
    );


    @GET("customer.asmx/getRoomsData")
    Call<List<HomeModel>> getRoomsData();



    @GET("customer.asmx/getMSG_Connection")
    Call<List<MSGConnectionModel>> getMSG_Connection();


    @GET("customer.asmx/addMSG_Connection")
    Call<Map<String, String>> addMSG_Connection(
            @Query("Owner_Id") String Owner_Id,
            @Query("User_Id") String User_Id,
            @Query("Owner_Name") String Owner_Name,
            @Query("User_Name") String User_Name,
            @Query("Last_Message") String Last_Message,
            @Query("MSG_Status") String MSG_Status,
            @Query("Remark") String Home_Id
    );


 @GET("admin.asmx/UploadFile2")
    Call<Map<String, String>> UploadFile2(
            @Query("f") byte[] f,
            @Query("fileName") String fileName
    );



}
