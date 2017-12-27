package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.R;

/**
 * Created by heretic on 10/8/17.
 */

public class UnderFiveSearch1Cr extends AppCompatActivity implements View.OnClickListener {

    Button search;
    EditText set1;
    ListView childlist;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    ProgressDialog dialog;

    ArrayList<String> poss;

    String  date="";
    public static final String message= "com.somiaya.ayurvihar.ayurvihar.underfive";

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_search1cr);
        search = (Button) findViewById(R.id.search);
        set1 = (EditText) findViewById(R.id.set1);
        dialog = new ProgressDialog(UnderFiveSearch1Cr.this);

        set1.setInputType(InputType.TYPE_NULL);

        poss= new ArrayList<>();

        //Executing spinner list for selecting date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = set1.getText().toString().trim();


                if (date.equals(""))
                    Toast.makeText(UnderFiveSearch1Cr.this, "Date field can be empty", Toast.LENGTH_SHORT).show();
                else {
                    Intent i = new Intent(getApplicationContext(),UnderFiveSearch1CrOutput.class);
                    i.putExtra("date",date);
                    startActivity(i);
                }
            }
        });




    }



    private void setDateTimeField() {
        set1.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                set1.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    public String getDate()
    {
        return date;
    }
    @Override
    public void onClick(View view) {
        if(view == set1) {
            datePickerDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(UnderFiveSearch1Cr.this,UnderFiveHome.class);
        startActivity(i);
    }
}
