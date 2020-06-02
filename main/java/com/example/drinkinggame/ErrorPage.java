package com.example.drinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.view.View;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Button;


import android.os.Bundle;

import java.util.Random;

public class ErrorPage extends AppCompatActivity {
    Button Drink;
    TextView Result;
    EditText Number;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_error);

        Drink = (Button)findViewById(R.id.Drink_button);
        Result = (TextView)findViewById((R.id.Randomnumber));
        Number = (EditText) findViewById(R.id.Number);

        Drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Result.setText((generateRandomIntIntRange(1,Integer.parseInt(Number.getText().toString()))));
            }

        });



        int sNumber = Integer.parseInt(Number.getText().toString());

    }

    public static int generateRandomIntIntRange(int min, int max) {
        Random r = new Random();
        return r.nextInt((max - min) + 1) + min;
    }

}
