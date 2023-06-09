package com.example.game;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
public class CustomAdapter extends RecyclerView.Adapter<CustomAdapter.MyViewHolder> {

    // good 100 % check the animate cond

    private Context context;
    private Activity activity;
    private ArrayList game_id, game_name, game_type, game_price;

    Animation translate_anim;

    //***********************************************************************************
    // adapter is the contaier of each row
    // conts
    CustomAdapter(Activity activity, Context context, ArrayList game_id, ArrayList game_name, ArrayList game_type, ArrayList game_price){

        this.activity = activity;
        this.context = context;

        this.game_id = game_id;
        this.game_name = game_name;
        this.game_type = game_type;
        this.game_price = game_price;
    }

    //***********************************************************************************
    // take the info for each row and display

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        LayoutInflater inflater = LayoutInflater.from(context);
        View view = inflater.inflate(R.layout.my_row, parent, false);
        return new MyViewHolder(view);
    } // end of on create


    //***********************************************************************************
    // very importatnt to view the row info and if click on it go to manage page
    //@RequiresApi(api = Build.VERSION_CODES.M)
    @Override
    public void onBindViewHolder(@NonNull final MyViewHolder holder, final int position) {

        // take the text from array from the DB and set to the filed

        holder.game_id_txt.setText(String.valueOf(game_id.get(position)));
        holder.game_name_txt.setText(String.valueOf(game_name.get(position)));
        holder.game_type_txt.setText(String.valueOf(game_type.get(position)));
        holder.game_price_txt.setText(String.valueOf(game_price.get(position)));

        //Recyclerview onClickListener   all for manage
        //*********************
        // if click on it go to manage page

        holder.mainLayout.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(context, ManageActivity.class);
                intent.putExtra("id", String.valueOf(game_id.get(holder.getAdapterPosition())));
                intent.putExtra("name", String.valueOf(game_name.get(holder.getAdapterPosition())));
                intent.putExtra("type", String.valueOf(game_type.get(holder.getAdapterPosition())));
                intent.putExtra("price", String.valueOf(game_price.get(holder.getAdapterPosition())));
                activity.startActivityForResult(intent, 1);
            }
        });   // not work at all must check again

    }
    //***********************************************************************************

    @Override
    public int getItemCount() {
        return game_id.size();
    }

    // inner class

    //***********************************************************************************
    // view items
    class MyViewHolder extends RecyclerView.ViewHolder {

        TextView game_id_txt, game_name_txt, game_type_txt, game_price_txt;
        LinearLayout mainLayout;  // for manage

        MyViewHolder(@NonNull View itemView) {
            super(itemView);

            // take the value by id
            game_id_txt = itemView.findViewById(R.id.game_id_txt);
            game_name_txt = itemView.findViewById(R.id.game_name_txt);
            game_type_txt = itemView.findViewById(R.id.game_type_txt);
            game_price_txt = itemView.findViewById(R.id.game_price_txt);

            mainLayout = itemView.findViewById(R.id.mainLayout);


            //Animate Recyclerview
             translate_anim = AnimationUtils.loadAnimation(context, R.anim.translate_anim);
             mainLayout.setAnimation(translate_anim);
        }

    }

}
