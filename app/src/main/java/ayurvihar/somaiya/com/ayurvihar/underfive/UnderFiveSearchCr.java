package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.util.Pair;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.HashMap;
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;

/**
 * Created by sanchita on 10-08-2017.
 */

public class UnderFiveSearchCr extends AppCompatActivity implements View.OnClickListener {

    Button search;
    EditText set1, set2;
    Spinner spinner;
    ListView childlist;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    ProgressDialog dialog;
    String Set1="",Set2="",selection="";


    ArrayList<String> poss=new ArrayList<>();
    HashMap<String,String> filters = new HashMap<>();
    ArrayList<String> options = new ArrayList<>();

    String fname="", lname="", dob="", pno="",area="", addr ,cid;
    int c;

    public static final DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    public static final DatabaseReference databaseChildHr=CHILD_DB.child("GenRec");


    protected void onCreate(@Nullable final Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_searchcr);

        search = (Button) findViewById(R.id.appfil);
        set1 = (EditText) findViewById(R.id.set1);
        set2 = (EditText) findViewById(R.id.set2);
        spinner = (Spinner) findViewById(R.id.filters);

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                selection = ""+parent.getItemAtPosition(position);
                selection = selection.trim();
                Log.v("sel",selection);
                set1.setVisibility(View.GONE);
                set2.setVisibility(View.GONE);
                set1.setInputType(InputType.TYPE_NULL);
                set2.setInputType(InputType.TYPE_NULL);
                set1.setText("");
                set2.setText("");
                search.setVisibility(View.GONE);
                Set1="";
                Set2="";
                switch (selection) {
                    case "Name":
                        set1.setVisibility(View.VISIBLE);
                        set1.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                        set1.setHint("First Name");
                        Set1 = set1.getText().toString().trim();
                        search.setVisibility(View.VISIBLE);
                        set2.setVisibility(View.VISIBLE);
                        set2.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                        set2.setHint("Last Name");
                        break;
                    case "DOB":
                        set1.setVisibility(View.VISIBLE);
                        set1.setHint("DOB");
                        setDateTimeField();
                        set1.setInputType(InputType.TYPE_NULL);
                        set1.requestFocus();
                        search.setVisibility(View.VISIBLE);
                        break;
                    case "Contact No":
                        set1.setVisibility(View.VISIBLE);
                        set1.setInputType(InputType.TYPE_CLASS_PHONE);
                        set1.setHint("Mobile No");
                        search.setVisibility(View.VISIBLE);
                        break;
                    case "Area":
                        set1.setVisibility(View.VISIBLE);
                        set1.setInputType(InputType.TYPE_TEXT_FLAG_CAP_WORDS);
                        set1.setHint("Area");
                        search.setVisibility(View.VISIBLE);
                        break;
                    case "None":
                        set1.setVisibility(View.GONE);
                        set2.setVisibility(View.GONE);
                        set1.setInputType(InputType.TYPE_NULL);
                        set2.setInputType(InputType.TYPE_NULL);
                        set1.setText("");
                        set2.setText("");
                        search.setVisibility(View.GONE);
                        Set1="";
                        Set2="";
                        getAllData();
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        //setDateTimeField();

        childlist = (ListView) findViewById(R.id.childlist);
        dialog = new ProgressDialog(UnderFiveSearchCr.this);

        getAllData();

        search.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Set1 = set1.getText().toString().trim();
                Set2 = set2.getText().toString().trim();
                if(Set1.equals("") && Set2.equals(""))
                    Toast.makeText(UnderFiveSearchCr.this,"Atleast one field must be filled",Toast.LENGTH_SHORT);
                else {
                    applyFilter(selection, Set1, Set2);
                }
            }
        });

        childlist.setEmptyView(findViewById(R.id.empty));
    }

    public void applyFilter(final String selection,final String param1,final String param2) {

        dialog.setMessage("Searching the database...");
        dialog.show();
        if(poss.size()!=0)
            poss.clear();
        databaseChildHr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UnderFiveCr ufc = ds.getValue(UnderFiveCr.class);
                    int f=0;

                    if(selection.equals("Name")) {
                        if((ufc.getFname().trim().equals(param1)) || (ufc.getLname().trim().equals(param2)))
                            f=1;
                    }
                    else if(selection.equals("DOB")) {
                        if(ufc.getDob().trim().equals(param1))
                            f=1;
                    }
                    else if(selection.equals("Contact No")) {
                        if(ufc.getMob().trim().equals(param1))
                            f=1;
                    }
                    else if(selection.equals("Area")) {
                        if(ufc.getArea().trim().equals(param1))
                            f=1;
                    }

                    if(f==1) {

                        //Log.v("upcr",""+ds.getKey()+"/"+ds.child("fname"));
                        //Log.v("upcr", "" + ufc.getFname());
                        addr = "";
                        addr += (ufc.getRoom() + ", " + ufc.getBldg() + ", " + ufc.getArea() + ", " + ufc.getAc() + ", " + ufc.getTown());
                        String str1 = "Name: " + ufc.getFname() + " " + ufc.getLname() + "\n" +
                                "Date of Birth: " + ufc.getDob() + "\n" +
                                "Address: " + addr + "\n" +
                                "Contact Number: " + ufc.getMob() + "\n" +
                                "Child Identifier: " + ufc.getChildid() + "\n";
                        poss.add(str1);
                    }
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



    public void getAllData() {

        dialog.setMessage("Searching the database...");
        dialog.show();
        if(poss.size()!=0)
            poss.clear();
        databaseChildHr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UnderFiveCr ufc = ds.getValue(UnderFiveCr.class);

                    //Log.v("upcr",""+ds.getKey()+"/"+ds.child("fname"));
                    Log.v("upcr", "" + ufc.getFname());
                    addr = "";
                    addr += (ufc.getRoom() + ", " + ufc.getBldg() + ", " + ufc.getArea() + ", " + ufc.getAc() + ", " + ufc.getTown());
                    String str1 = "Name: " + ufc.getFname() + " " + ufc.getLname() + "\n" +
                            "Date of Birth: " + ufc.getDob() + "\n" +
                            "Address: " + addr + "\n" +
                            "Contact Number: " + ufc.getMob() + "\n" +
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

    @Override
    public void onClick(View view) {
        if(view == set1) {
            datePickerDialog.show();
        }
    }
}
