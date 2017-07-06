package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;

import com.google.firebase.database.DatabaseReference;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.MainActivity;

/**
 * Created by mikasa on 6/7/17.
 */


public class UnderFiveAddCr extends AppCompatActivity implements View.OnClickListener {

    EditText Addn1 , Addn2 , Addn3 , Addn4 , Addn5 , Addn6 , Addn7 , Addn8 , Addn9;
    Button AddRecord;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    public static final DatabaseReference databaseChildTable=MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildHr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_addcr);

        databaseChildHr=databaseChildTable.child("Childhr");
        Addn1 = (EditText) findViewById(R.id.Addn1);
        Addn2 = (EditText) findViewById(R.id.Addn2);
        Addn3 = (EditText) findViewById(R.id.Addn3);
        Addn4 = (EditText) findViewById(R.id.Addn4);
        Addn5 = (EditText) findViewById(R.id.Addn5);
        Addn6 = (EditText) findViewById(R.id.Addn6);
        Addn7 = (EditText) findViewById(R.id.Addn7);
        Addn8 = (EditText) findViewById(R.id.Addn8);
        Addn9 = (EditText) findViewById(R.id.Addn9);
        Addn9.setInputType(InputType.TYPE_NULL);
        Addn9.requestFocus();


        //Execurting spinner list for selecting date
        AddRecord = (Button) findViewById(R.id.AddRecord);

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();


        //Database handling of Under5 begins

        AddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {


            }
        });



    }

    private void setDateTimeField() {
        Addn9.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Addn9.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == Addn9) {
            datePickerDialog.show();
        }
    }
}
