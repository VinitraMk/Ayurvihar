package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
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
 * Created by sanchita on 10-08-2017.
 */

public class UnderFiveSearchCr extends AppCompatActivity implements View.OnClickListener {

    Button search;
    EditText set1, set2, set3, set4, set5;
    ListView childlist;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    ProgressDialog dialog;


    ArrayList<String> poss=new ArrayList<>();

    String fname="", lname="", dob="", pno="",area="", addr ,cid;
    int c;

    public static final DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    public static final DatabaseReference databaseChildHr=CHILD_DB.child("GenRec");


    protected void onCreate(@Nullable Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_searchcr);

        search = (Button) findViewById(R.id.searchchild);
        set1 = (EditText) findViewById(R.id.set1);
        set2 = (EditText) findViewById(R.id.set2);
        set3 = (EditText) findViewById(R.id.set3);
        set4 = (EditText) findViewById(R.id.set4);
        set5 = (EditText) findViewById(R.id.set5);

        set3.setInputType(InputType.TYPE_NULL);
        set3.requestFocus();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();

        childlist = (ListView) findViewById(R.id.childlist);
        dialog = new ProgressDialog(UnderFiveSearchCr.this);

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                //dialog.setMessage("Searching the database...");
                //dialog.show();
                fname = set1.getText().toString().trim();
                lname = set2.getText().toString().trim();
                dob = set3.getText().toString().trim();
                pno = set4.getText().toString().trim();
                area = set5.getText().toString().trim();

                if (poss.size() != 0)
                    poss.clear();
                if (fname.equals("") && lname.equals("") && dob.equals("") && pno.equals("") && area.equals(""))
                    Toast.makeText(UnderFiveSearchCr.this, "All fields cannot be empty", Toast.LENGTH_SHORT).show();
                else {
                    dialog.setMessage("Searching the database");
                    dialog.show();
                    databaseChildHr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                UnderFiveCr ufc = ds.getValue(UnderFiveCr.class);
                                //Log.v("upcr",""+ds.getKey()+"/"+ds.child("fname"));
                                Log.v("upcr", "" + ufc.getFname());

                                if (!fname.equals("")) {
                                    if (!fname.equals(ufc.getFname()))
                                        continue;
                                } else
                                    fname = ufc.getFname();
                                if (!lname.equals("")) {
                                    if (!lname.equals(ufc.getLname()))
                                        continue;
                                } else
                                    lname = ufc.getLname();
                                if (!dob.equals("")) {
                                    if (!dob.equals(ufc.getDob()))
                                        continue;
                                } else
                                    dob = ufc.getDob();
                                if (!pno.equals("")) {
                                    if (!pno.equals(ufc.getMob())) ;
                                    continue;
                                } else
                                    pno = ufc.getMob();
                                if (!area.equals(""))
                                    if (!area.equals(ufc.getArea()))
                                        continue;

                                Log.v("ufu", ufc.getFname());
                                addr = "";
                                addr += (ufc.getRoom() + ", " + ufc.getBldg() + ", " + ufc.getArea() + ", " + ufc.getAc() + ", " + ufc.getTown());
                                String str1 = "Name: " + fname + " " + lname + "\n" +
                                        "Date of Birth: " + dob + "\n" +
                                        "Address: " + addr + "\n" +
                                        "Contact Number: " + pno + "\n" +
                                        "Child Identifier: " + ufc.getChildid() + "\n";
                                poss.add(str1);
                            }
                            dialog.dismiss();
                            childlist.setAdapter(new ArrayAdapter<>(UnderFiveSearchCr.this, R.layout.child_textview, poss));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            dialog.dismiss();
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
