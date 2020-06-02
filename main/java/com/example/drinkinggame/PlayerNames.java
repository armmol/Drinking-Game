package com.example.drinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class PlayerNames extends AppCompatActivity {
    int i = 0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_player_names);

        final int n = getIntent().getIntExtra("random_number",0);
        final String[] names = new String[n];


        final Button EnterName = (Button) findViewById(R.id.EnterName);
        final EditText Plyername = (EditText) findViewById(R.id.Playername);

        EnterName.setOnClickListener(new View.OnClickListener() {


            @Override

            public void onClick(View v) {

                if (i< n ) {
                    names[i] = Plyername.getText().toString();
                    Plyername.setText("");
                    i++;
                     if(i==n) {
                         Plyername.setText("LETS DRINK");
                         openMainActivity(n, names);
                     }
                }
            }

        });

    }

    public void openMainActivity(int x,String[]names) {

        Intent IntentMain = new Intent(this, MainActivity.class);
        IntentMain.putExtra("player_number", x);
        IntentMain.putExtra("Names",names);
        startActivity((IntentMain));
    }
}