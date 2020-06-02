package com.example.drinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class Main2Activity extends AppCompatActivity {


    EditText Number;
    Button start;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main2);

        Number = (EditText) findViewById(R.id.Number);
        start = (Button) findViewById(R.id.start);


        start.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if (!Number.getText().toString().matches("") || Number.getText().toString().matches("1")) {
                    openPlayersPage(Integer.parseInt(Number.getText().toString()));
                    Toast.makeText(Main2Activity.this, "Maximum number of players is 10", Toast.LENGTH_SHORT).show();
                }
            }
        });

    }
    public void openPlayersPage(int x){
        Intent IntentMain = new Intent(this, PlayerNames.class);
        IntentMain.putExtra("random_number", x);
        startActivity((IntentMain));
    }
}
