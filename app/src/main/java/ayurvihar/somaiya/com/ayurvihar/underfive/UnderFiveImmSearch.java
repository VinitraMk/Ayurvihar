package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

public class UnderFiveImmSearch extends AppCompatActivity implements View.OnClickListener{

    Button search;
    EditText set1;
    ListView childlist;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    ProgressDialog dialog;
    String cid="";

    ArrayList<String> poss;

    String  date="";

    public static final DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    public static final DatabaseReference databaseImmRec=CHILD_DB.child("ImmRec");

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_five_imm_search);
        search = (Button) findViewById(R.id.search);
        set1 = (EditText) findViewById(R.id.set1);
        dialog = new ProgressDialog(UnderFiveImmSearch.this);

        set1.setInputType(InputType.TYPE_NULL);
        set1.requestFocus();

        poss= new ArrayList<>();

        //Executing spinner list for selecting date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();



        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                date = set1.getText().toString().trim();

                if (poss.size() != 0)
                    poss.clear();
                if (date.equals(""))
                    Toast.makeText(UnderFiveImmSearch.this, "No fields can be empty", Toast.LENGTH_SHORT).show();
                else {
                    dialog.setMessage("Searching the database");
                    dialog.show();
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

}
