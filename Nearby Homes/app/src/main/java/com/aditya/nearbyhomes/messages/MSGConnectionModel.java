package com.aditya.nearbyhomes.messages;

import com.google.gson.annotations.SerializedName;

public class MSGConnectionModel {
    private String Message_Id;
    private String Owner_Id;
    private String User_Id;
    private String Owner_Name;
    private String User_Name;
    
    
    @SerializedName("Last_Message")
    private String Phone;
    private String MSG_Status;
    private String Added_On;
    
    @SerializedName("Remark")
    private String Home_Id;


    // Getter Methods 

    public String getMessage_Id() {
        return Message_Id;
    }

    public String getOwner_Id() {
        return Owner_Id;
    }

    public String getUser_Id() {
        return User_Id;
    }

    public String getOwner_Name() {
        return Owner_Name;
    }

    public String getUser_Name() {
        return User_Name;
    }

    public String getPhone() {
        return Phone;
    }

    public String getMSG_Status() {
        return MSG_Status;
    }

    public String getAdded_On() {
        return Added_On;
    }

    public String getHome_Id() {
        return Home_Id;
    }

    // Setter Methods 

    public void setMessage_Id(String Message_Id) {
        this.Message_Id = Message_Id;
    }

    public void setOwner_Id(String Owner_Id) {
        this.Owner_Id = Owner_Id;
    }

    public void setUser_Id(String User_Id) {
        this.User_Id = User_Id;
    }

    public void setOwner_Name(String Owner_Name) {
        this.Owner_Name = Owner_Name;
    }

    public void setUser_Name(String User_Name) {
        this.User_Name = User_Name;
    }

    public void setPhone(String Phone) {
        this.Phone = Phone;
    }

    public void setMSG_Status(String MSG_Status) {
        this.MSG_Status = MSG_Status;
    }

    public void setAdded_On(String Added_On) {
        this.Added_On = Added_On;
    }

    public void setHome_Id(String Home_Id) {
        this.Home_Id = Home_Id;
    }
}