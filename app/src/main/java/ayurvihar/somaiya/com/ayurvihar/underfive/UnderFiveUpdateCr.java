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
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
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
    TextView empty;
    ListView childlist;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    ProgressDialog dialog;


    ArrayList<String> poss;

    String fname, lname, dob, addr = "",cid;
    int c;

    public static final DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    public static final DatabaseReference databaseChildHr=CHILD_DB.child("GenRec");
    public static final DatabaseReference databaseChildHcr=CHILD_DB.child("ChkRec");
    public static final DatabaseReference databaseChildImm=CHILD_DB.child("ImmRec");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_updatecr);

        empty = (TextView) findViewById(R.id.empty);
        search = (Button) findViewById(R.id.searchchild);
        set1 = (EditText) findViewById(R.id.set1);
        set2 = (EditText) findViewById(R.id.set2);
        set3 = (EditText) findViewById(R.id.set3);
        set1.setText("Kira");
        set2.setText("Light");
        set3.setText("24-12-2017");
        childlist = (ListView) findViewById(R.id.childlist);
        dialog = new ProgressDialog(UnderFiveUpdateCr.this);

        set3.setInputType(InputType.TYPE_NULL);
        set3.requestFocus();
        empty.setText("");


        poss= new ArrayList<>();

        //Execurting spinner list for selecting date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();


        childlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.v("ufu",""+poss.get(position));
                cid=poss.get(position);
                int in=cid.indexOf("Child Identifier");
                if(in!=-1)
                {
                    c=0;
                    in+=17;
                    //Log.v("ufup",""+cid.indexOf("Child Identifier")+" "+cid.substring(in,in+15));
                    cid=cid.substring(in,in+15);
                    cid=cid.trim();
                    Log.v("cid",cid);
                    Intent i = new Intent(UnderFiveUpdateCr.this,UnderfiveScrollview.class);
                    i.putExtra("childid",cid);
                    i.putExtra("Dob",set3.getText().toString().trim());
                    startActivity(i);
                }
            }
        });


        search.setOnClickListener(new View.OnClickListener() {

            public void onClick(View v) {
                dialog.setMessage("Searching the database...");
                dialog.show();
                fname = set1.getText().toString().trim();
                lname = set2.getText().toString().trim();
                dob = set3.getText().toString().trim();
                if(poss.size()!=0)
                    poss.clear();
                if (fname.equals("") || lname.equals("") || dob.equals(""))
                    Toast.makeText(UnderFiveUpdateCr.this, "No fields can be empty", Toast.LENGTH_SHORT).show();
                else {
                    dialog.setMessage("Searching the database");
                    databaseChildHr.addValueEventListener(new ValueEventListener() {
                        @Override
                        public void onDataChange(DataSnapshot dataSnapshot) {
                            for (DataSnapshot ds : dataSnapshot.getChildren()) {
                                UnderFiveCr ufc = ds.getValue(UnderFiveCr.class);
                                //Log.v("upcr",""+ds.getKey()+"/"+ds.child("fname"));
                                Log.v("upcr",""+ufc.getFname());
                                if (fname.equals(ufc.getFname()) && lname.equals(ufc.getLname()) && dob.equals(ufc.getDob())) {
                                    Log.v("ufu", ufc.getFname());
                                    addr = "";
                                    addr += (ufc.getRoom() + ", " + ufc.getBldg() + ", " + ufc.getArea() + ", " + ufc.getAc() + ", " + ufc.getTown());
                                    String str1= "Name: " + fname + " " + lname + "\n" +
                                            "Date of Birth: " + dob + "\n" +
                                            "Address: " + addr + "\n"+
                                            "Child Identifier: "+ ufc.getChildid() + "\n";
                                    poss.add(str1);
                                }
                            }
                            if(poss.size()==0)
                                empty.setText("No Records Found");
                            dialog.dismiss();
                            childlist.setAdapter(new ArrayAdapter<>(UnderFiveUpdateCr.this,R.layout.child_textview,poss));
                        }

                        @Override
                        public void onCancelled(DatabaseError databaseError) {
                            dialog.dismiss();
                        }
                    });
                }
            }
        });

        childlist.setEmptyView(findViewById(R.id.empty));
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
