package com.example.game;


import androidx.appcompat.app.AppCompatActivity;

import androidx.appcompat.app.ActionBar;
import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class ManageActivity extends AppCompatActivity {

    Button delete_button;
    String id, name, type, price;

    //***********************************************************************************
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_manage);

        delete_button = findViewById(R.id.delete_button);

        //First we call this
        getIntentData();

        //*****************************
        delete_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                confirmDialog();
            }
        });
    }   // end of on create good

    //***********************************************************************************

    // take the name of game and info
    void getIntentData(){
        if(getIntent().hasExtra("id") && getIntent().hasExtra("name") && getIntent().hasExtra("type") && getIntent().hasExtra("price")){
            //Getting Data from Intent the recent data
            id = getIntent().getStringExtra("id");
            name = getIntent().getStringExtra("name");
            type = getIntent().getStringExtra("type");
            price = getIntent().getStringExtra("price");
        }else{
            Toast.makeText(this, "There is no Games Added.", Toast.LENGTH_SHORT).show();
        }
    }

    //***********************************************************************************
    // working good
    void confirmDialog(){
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setTitle("Delete " + name + " ?");
        builder.setMessage("Are you sure you want to delete " + name + " ?");

        //***************************
        //for yes
        builder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                DBHelper mydb = new DBHelper(ManageActivity.this);
                mydb.deleteOneRow(id);

                // go to the home after finish
                Intent intent = new Intent(ManageActivity.this, HomeActivity.class);
                startActivity(intent);
                finish();
            }
        });
        //************************
        // for no
        builder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialogInterface, int i) {
                //do nothing
            }
        });

        //**************************
       // builder.create().show();


        AlertDialog alert = builder.create();
        alert.show();
        Button nbutton = alert.getButton(DialogInterface.BUTTON_NEGATIVE);
        nbutton.setTextColor(Color.BLACK);
        Button pbutton = alert.getButton(DialogInterface.BUTTON_POSITIVE);
        pbutton.setTextColor(Color.BLACK);
    }
}