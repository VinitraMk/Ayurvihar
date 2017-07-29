package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.text.Layout;
import android.util.AttributeSet;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
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
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;

/**
 * Created by mikasa on 29/7/17.
 */

public class UnderFiveUpdateCr extends AppCompatActivity implements View.OnClickListener {

    Button search;
    EditText set1, set2, set3;
    ListView childlist;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;

    ArrayList<String> poss;

    String fname, lname, dob, addr = "";

    DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildHr;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_updatecr);

        search = (Button) findViewById(R.id.searchchild);
        set1 = (EditText) findViewById(R.id.set1);
        set2 = (EditText) findViewById(R.id.set2);
        set3 = (EditText) findViewById(R.id.set3);
        childlist = (ListView) findViewById(R.id.childlist);

        set3.setInputType(InputType.TYPE_NULL);
        set3.requestFocus();

        databaseChildHr = CHILD_DB.child("Childhr");

        poss= new ArrayList<>();

        //Execurting spinner list for selecting date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();


        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname = set1.getText().toString().trim();
                lname = set2.getText().toString().trim();
                dob = set3.getText().toString().trim();
                if(poss.size()!=0)
                    poss.clear();
                if (fname.equals("") || lname.equals("") || dob.equals(""))
                    Toast.makeText(UnderFiveUpdateCr.this, "No fields can be empty", Toast.LENGTH_SHORT).show();
                else {
                    databaseChildHr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                UnderFiveCr ufc = ds.getValue(UnderFiveCr.class);
                                if (fname.equals(ufc.getFname()) && lname.equals(ufc.getLname()) && dob.equals(ufc.getDob())) {
                                    Log.v("ufu", ufc.getFname());
                                    addr = "";
                                    addr += (ufc.getRoom() + ", " + ufc.getBldg() + ", " + ufc.getArea() + ", " + ufc.getAc() + ", " + ufc.getTown());
                                    String str= "Name: " + fname + " " + lname + "\n" +
                                            "Date of Birth: " + dob + "\n" +
                                            "Address: " + addr + "\n";
                                    poss.add(str);
                                    Log.v("ufu",""+str+"\n"+poss.size());
                                }
                            }
                            childlist.setAdapter(new ArrayAdapter<>(UnderFiveUpdateCr.this,R.layout.child_textview,poss));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {

                        }
                    });
                }
            }
        });
    }

    private void setDateTimeField() {
        set3.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                set3.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == set3) {
            datePickerDialog.show();
        }
    }
}
