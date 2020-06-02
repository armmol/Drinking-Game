package com.example.drinkinggame;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.view.View;
import android.widget.TextView;
import android.widget.Button;


import android.os.Bundle;


public class MainActivity extends AppCompatActivity {
    Button Drink;
    TextView Randomnumber;
    TextView Person;
    TextView Cardtext;
    Button Results;
    int roundsplayed=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_activity);


        final int variable = getIntent().getIntExtra("player_number", 0);
        final String[] names = getIntent().getStringArrayExtra("Names");
        final String[] cards = {"Drink 2 shots","Drink 3 shots","Drink a shot","exchange types of drinks(eg.whiskey with vodka)","sing a song after chugging drink", "exchange drinks with a player and finish it","chugg","Drink someones drink + your drink","finish your drink","serve everyone refills"};
        final int[] results = new int[names.length];
        for(int i = 0;i<results.length;i++)
        {
            results[i]=0;
        }

        Drink = (Button)findViewById(R.id.Drink_button);
        Randomnumber = (TextView)findViewById(R.id.Randomnumber);
        Person = (TextView)findViewById(R.id.Person_No_);
        Cardtext = (TextView) findViewById(R.id.CardText);
        Results = (Button)findViewById(R.id.Results);


        Drink.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                int pointer  =(int)getRandomIntegerBetweenRange(1,variable);
                String a = ""+pointer;

                Randomnumber.setText(a);
                results[pointer-1]++;
                roundsplayed++;
                Cardtext.setText(cards[(int)getRandomIntegerBetweenRange(0,cards.length-1)]);

                Person.setText(names[pointer-1]+" has to");
            }

        });


        Results.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openResultsPage(results,names,roundsplayed);
            }
        });
    }
    public static double getRandomIntegerBetweenRange(double min, double max){
        double x = (int)(Math.random()*((max-min)+1))+min;
        return x;
    }

    public void openResultsPage(int[]x,String[]y,int z)
    {
        Intent IntentMain = new Intent(this, ResultsPage.class);
        IntentMain.putExtra("results_array",x);
        IntentMain.putExtra("names_array",y);
        IntentMain.putExtra("rounds_played",z);
        startActivity((IntentMain));
    }

}
