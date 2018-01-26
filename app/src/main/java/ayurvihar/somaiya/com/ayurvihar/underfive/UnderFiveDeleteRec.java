package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.AlertDialog;
import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.content.DialogInterface;
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
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

public class UnderFiveDeleteRec extends AppCompatActivity implements View.OnClickListener {

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
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_five_delete_rec);

        empty = (TextView) findViewById(R.id.empty);
        search = (Button) findViewById(R.id.searchchild);
        set1 = (EditText) findViewById(R.id.set1);
        set2 = (EditText) findViewById(R.id.set2);
        set3 = (EditText) findViewById(R.id.set3);
        set1.setText("Raj");
        set2.setText("Nandu");
        set3.setText("24-12-2017");
        childlist = (ListView) findViewById(R.id.childlist);
        dialog = new ProgressDialog(UnderFiveDeleteRec.this);

        set3.setInputType(InputType.TYPE_NULL);
        set3.requestFocus();
        empty.setText("");


        poss= new ArrayList<>();

        //Execurting spinner list for selecting date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();


        childlist.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, final int position, long id) {

                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {

                        if (!isFinishing()){
                            new AlertDialog.Builder(UnderFiveDeleteRec.this)
                                    .setTitle("Confirm Delete")
                                    .setMessage("Are you sure that you want to delete this record?")
                                    .setCancelable(true)
                                    .setPositiveButton("ok", new DialogInterface.OnClickListener() {
                                        @Override
                                        public void onClick(DialogInterface dialog, int which) {
                                            Log.v("ufu",""+poss.get(position));
                                            cid=poss.get(position);
                                            int in=cid.indexOf("Child Identifier");
                                            if(in!=-1)
                                            {
                                                c=0;
                                                in+=17;
                                                Log.v("ufup",""+cid.indexOf("Child Identifier")+" "+cid.substring(in,in+15));
                                                cid=cid.substring(in,in+15);
                                                cid = cid.trim();
                                                deleteRecord(cid);
                                                /*if(dialog.isShowing()) {
                                                    dialog.dismiss();
                                                }*/
                                            }
                                        }
                                    }).show();
                        }
                    }
                });


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
                    Toast.makeText(UnderFiveDeleteRec.this, "No fields can be empty", Toast.LENGTH_SHORT).show();
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
                            childlist.setAdapter(new ArrayAdapter<>(UnderFiveDeleteRec.this,R.layout.child_textview,poss));
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

    public void deleteRecord(final String childid) {

        dialog.setMessage("Deleting Record from Database...");
        dialog.show();
        //Remove record from GenRec
        Query query ;
        query = databaseChildHr.orderByChild("childid").equalTo(childid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    Log.v("val",ds.getKey());
                    ds.getRef().removeValue();
                }
                Toast.makeText(UnderFiveDeleteRec.this,"Removed record from database",Toast.LENGTH_SHORT);
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });





        //Remove record from ImmRec
        query = databaseChildImm.orderByChild("childid").equalTo(childid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    Log.v("ref", "" + ds.getRef());
                    ds.getRef().removeValue();
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        //Remove record from ChkRec
        Log.v("vval",""+databaseChildHcr.child(cid).getRef());
        databaseChildHcr.child(cid).getRef().removeValue();
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

    @Override
    public void onBackPressed() {
        Intent i = new Intent(UnderFiveDeleteRec.this,UnderFiveHome.class);
        startActivity(i);
    }
}
