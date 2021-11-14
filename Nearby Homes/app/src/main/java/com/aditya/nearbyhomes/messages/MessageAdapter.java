package com.aditya.nearbyhomes.messages;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.aditya.nearbyhomes.R;
import com.aditya.nearbyhomes.detailhome.DetailHomeActivity;

import java.util.List;

public class MessageAdapter extends RecyclerView.Adapter<MessageAdapter.MSGViewHolder> {

    List<MSGConnectionModel> list;
    Context context;

    public MessageAdapter(List<MSGConnectionModel> list, Context context) {
        this.list = list;
        this.context = context;
    }

    @NonNull
    @Override
    public MSGViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new MSGViewHolder(LayoutInflater.from(context).inflate(R.layout.customer_list_card,parent,false));
    }

    @Override
    public void onBindViewHolder(@NonNull MSGViewHolder holder, int position) {
MSGConnectionModel n =list.get(position);

holder.name.setText(n.getOwner_Name());
holder.number.setText("Mobile Number : "+n.getPhone());


holder.whatsapp.setOnClickListener(new View.OnClickListener() {
    @Override
    public void onClick(View view) {
        wssappMsg(n.getPhone(),"Hi");
    }
});



    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    class MSGViewHolder extends RecyclerView.ViewHolder {


        TextView name,number;
        ImageView whatsapp;

        public MSGViewHolder(@NonNull View itemView) {
            super(itemView);

            name=itemView.findViewById(R.id.name);
            number=itemView.findViewById(R.id.number);
            whatsapp=itemView.findViewById(R.id.whatsapp);

        }
    }
    public void wssappMsg(String phone,String msg){
        try {

            String url = "https://api.whatsapp.com/send?phone=+91"+phone;
            Intent i = new Intent(Intent.ACTION_VIEW);
            i.setData(Uri.parse(url));
            context.startActivity(i);
        }catch (Exception e){
            Toast.makeText(context, "Error", Toast.LENGTH_SHORT).show();
        }
    }
}
