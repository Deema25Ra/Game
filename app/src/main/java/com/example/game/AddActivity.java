package com.example.game;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class AddActivity extends AppCompatActivity {

    // good 100 %

    EditText name_input, type_input, price_input;
    Button add_button;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_add);

        name_input = findViewById(R.id.name_input);
        type_input = findViewById(R.id.type_input);
        price_input = findViewById(R.id.price_input);
        add_button = findViewById(R.id.add_button);

        add_button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                try {
                    DBHelper myDB = new DBHelper(AddActivity.this);
                    myDB.addGame(name_input.getText().toString().trim(),
                            type_input.getText().toString().trim(),
                            Integer.valueOf(price_input.getText().toString().trim()));
                }catch (Exception e){
                    Toast.makeText(AddActivity.this, "No game added", Toast.LENGTH_SHORT).show();
                }
                // go to home again
                Intent intent = new Intent(AddActivity.this, HomeActivity.class);
                startActivity(intent);
            }
        });
    }
}
