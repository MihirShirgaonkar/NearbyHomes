package com.aditya.nearbyhomes.homes;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.detailhome.DetailHomeActivity;
import com.makeramen.roundedimageview.RoundedImageView;
import com.squareup.picasso.Picasso;

import java.util.List;

public class HomesAdapter extends RecyclerView.Adapter<HomesAdapter.HAViewHolder> {

    Context context;
    List<HomeModel> list;

    public HomesAdapter(Context context, List<HomeModel> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public HAViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new HAViewHolder(LayoutInflater.from(context).inflate(R.layout.home_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull HAViewHolder holder, int position) {
        HomeModel n=list.get(position);

        holder.line1.setText(n.getCity()+" ("+n.getRoom_Type()+")");
        holder.line2.setText(n.getBuilding_Name()+", "+n.getArea()+" ("+n.getSq_foot()+"sq ft)");
        holder.line3.setText(n.getSale_Type()+" - "+n.getAmount()+"/-");

        if (n.getSale_Type().equals("Rent")){
            holder.type.setText(n.getSale_Type());
            holder.type.setTextColor(context.getResources().getColor(R.color.colorPrimaryDark));
            holder.type.setBackground(context.getResources().getDrawable(R.drawable.rent_bg));
        }



        if (n.getSale_Type().equals("Sale")){
            holder.type.setText(n.getSale_Type());
            holder.type.setTextColor(context.getResources().getColor(R.color.colorPrimary));
            holder.type.setBackground(context.getResources().getDrawable(R.drawable.sale_bg));
        }

        String path ="http://adityajaitpal22-001-site1.etempurl.com/room_images/";


        String imgurl="http://adityajaitpal22-001-site1.etempurl.com/room_images/sample.jpg";
        String imgurl1="http://adityajaitpal22-001-site1.etempurl.com/room_images/sample3.jpg";
        String imgurl2="http://adityajaitpal22-001-site1.etempurl.com/room_images/sample2.jpg";


        if (position==1){
            Picasso.with(context).load(imgurl).placeholder(R.drawable.place_holder).into(holder.image);
        }else {
            Picasso.with(context).load(imgurl1).placeholder(R.drawable.place_holder).into(holder.image);

        }
        if (position==3) {
            Picasso.with(context).load(imgurl2).placeholder(R.drawable.place_holder).into(holder.image);
        }


        if (position==4) {
            Picasso.with(context).load(imgurl).placeholder(R.drawable.place_holder).into(holder.image);
        }

 if (position==5) {
            Picasso.with(context).load(imgurl2).placeholder(R.drawable.place_holder).into(holder.image);
        }



 if (position==6) {
            Picasso.with(context).load(imgurl1).placeholder(R.drawable.place_holder).into(holder.image);
        }




        if (position==7) {
            Picasso.with(context).load(imgurl).placeholder(R.drawable.place_holder).into(holder.image);
        }

 if (position==8) {
            Picasso.with(context).load(imgurl2).placeholder(R.drawable.place_holder).into(holder.image);
        }



 if (position==9) {
            Picasso.with(context).load(imgurl1).placeholder(R.drawable.place_holder).into(holder.image);
        }




        if (position==10) {
            Picasso.with(context).load(imgurl).placeholder(R.drawable.place_holder).into(holder.image);
        }

 if (position==11) {
            Picasso.with(context).load(imgurl2).placeholder(R.drawable.place_holder).into(holder.image);
        }



 if (position==12) {
            Picasso.with(context).load(imgurl1).placeholder(R.drawable.place_holder).into(holder.image);
        }





        if (position==13) {
            Picasso.with(context).load(imgurl).placeholder(R.drawable.place_holder).into(holder.image);
        }

 if (position==14) {
            Picasso.with(context).load(imgurl2).placeholder(R.drawable.place_holder).into(holder.image);
        }



 if (position==15) {
            Picasso.with(context).load(imgurl1).placeholder(R.drawable.place_holder).into(holder.image);
        }




        holder.itemView.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(context, DetailHomeActivity.class);
                intent.putExtra("Home_Id",n.getHome_Id());
                intent.putExtra("User_Id",n.getUser_Id());
                intent.putExtra("Owner_Name",n.getOwner_Name());
                intent.putExtra("Owner_Phone",n.getOwner_Phone());
                intent.putExtra("House_Number",n.getHouse_Number());
                intent.putExtra("Landmark",n.getLandmark());
                intent.putExtra("Area",n.getArea());
                intent.putExtra("City",n.getCity());
                intent.putExtra("District",n.getDistrict());
                intent.putExtra("State",n.getState());
                intent.putExtra("Country",n.getCountry());
                intent.putExtra("Pincode",n.getPincode());
                intent.putExtra("AddressLine",n.getAddressLine());
                intent.putExtra("Latitude",n.getLatitude());
                intent.putExtra("Longitude",n.getLongitude());
                intent.putExtra("Is_by_location",n.getIs_by_location());
                intent.putExtra("Room_Type",n.getRoom_Type());
                intent.putExtra("Photo1",n.getPhoto1());
                intent.putExtra("Photo2",n.getPhoto2());
                intent.putExtra("Photo3",n.getPhoto3());
                intent.putExtra("Floor",n.getFloor());
                intent.putExtra("Building_Name",n.getBuilding_Name());
                intent.putExtra("Description",n.getDescription());
                intent.putExtra("Sale_Type",n.getSale_Type());
                intent.putExtra("Amount",n.getAmount());
                intent.putExtra("Sq_foot",n.getSq_foot());
                intent.putExtra("Remark",n.getRemark());
                intent.putExtra("Added_On",n.getAdded_On());
                intent.putExtra("Direction",n.getDirection());



                context.startActivity(intent);
            }
        });


    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class HAViewHolder extends RecyclerView.ViewHolder {

        RoundedImageView image;
        TextView line1,line2,line3,type;
        public HAViewHolder(@NonNull View itemView) {
            super(itemView);

            line1=itemView.findViewById(R.id.line1);
            line3=itemView.findViewById(R.id.line3);
            line2=itemView.findViewById(R.id.line2);
            type=itemView.findViewById(R.id.type);
            image=itemView.findViewById(R.id.img);

        }
    }
}
