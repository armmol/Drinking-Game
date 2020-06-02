package com.example.drinkinggame;

import androidx.appcompat.app.AlertDialog;
import androidx.appcompat.app.AppCompatActivity;

import android.database.Cursor;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

public class ResultsPage extends AppCompatActivity {

    TextView Player1;
    TextView Player2;
    Button history;
    Button addtohistory;
    Button delete;
    EditText DeleteID;
    Database_Helper History;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_results_page);

        History = new Database_Helper(this);



        Player1 = (TextView) findViewById(R.id.Player1);
        Player2 = (TextView) findViewById(R.id.Player2);
        history = (Button) findViewById(R.id.history);
        addtohistory = (Button) findViewById(R.id.addtohistory);
        delete = (Button) findViewById(R.id.delete);
        DeleteID = (EditText) findViewById(R.id.ID_Text);


        final int[] results_arrays = getIntent().getIntArrayExtra("results_array");
        final String[] names_arrays = getIntent().getStringArrayExtra("names_array");
        final  int rounds_played = getIntent().getIntExtra("rounds_played",0);

        int first=0, second=0;

        for(int i =0;i<results_arrays.length;i++)
        {
            if(results_arrays[i]>first && results_arrays[i]>second )
                first =i;
            else if(results_arrays[i]>second)
                second =i;

        }

        Player1.setText("1                     "+names_arrays[first]+"                     "+(results_arrays[first]*100/rounds_played));
        Player2.setText("2                     "+names_arrays[second]+"                     "+(results_arrays[second]*100/rounds_played));

        addData(results_arrays,names_arrays,rounds_played);
        viewAll();
        DeleteData();



    }

    public void addData(final int[]results_arrays,final String[] names_arrays,final int rounds_played) {
        addtohistory.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


                for (int i = 0; i < results_arrays.length; i++) {
                    String d = "" + (results_arrays[i] * 100 / rounds_played);
                    String r = "" + rounds_played;

                    boolean isinserted = History.insertdata(names_arrays[i], r, d);
                    if (isinserted=true)
                        Toast.makeText(ResultsPage.this, "Data Inserted", Toast.LENGTH_LONG).show();
                    else
                        Toast.makeText(ResultsPage.this, "Data not Inserted", Toast.LENGTH_LONG).show();
                }
            }
        });
    }

    public void viewAll()
    {

        history.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Cursor res = History.getAllData();
                if(res.getCount()==0)
                {
                    showMessage("Error","No Data found");
                    return;
                }

                StringBuffer buffer = new StringBuffer();
                while(res.moveToNext()){
                    buffer.append("Id :"+res.getString(0)+"\n");
                    buffer.append("Name :"+res.getString(1)+"\n");
                    buffer.append("Rounds Played :"+res.getString(2)+"\n");
                    buffer.append("Drunk Percentage :"+res.getString(3)+"\n\n");
                }

                showMessage("Data",buffer.toString());

            }
        });

    }

    public void DeleteData(){
        delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Integer deleterows = History.delete(DeleteID.getText().toString());
                if (deleterows > 0)
                    Toast.makeText(ResultsPage.this, "Data Deleted", Toast.LENGTH_LONG).show();
                else
                    Toast.makeText(ResultsPage.this, "Data not Deleted", Toast.LENGTH_LONG).show();
            }
        });
    }

    public void showMessage(String title, String message)
    {
        AlertDialog.Builder builder = new AlertDialog.Builder(this);
        builder.setCancelable(true);
        builder.setTitle(title);
        builder.setMessage(message);
        builder.show();
    }
}
