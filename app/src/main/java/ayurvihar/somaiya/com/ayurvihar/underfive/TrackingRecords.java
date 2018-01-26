package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.Spinner;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;

public class TrackingRecords extends AppCompatActivity {

    public static final DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    public static final DatabaseReference databaseChildHr=CHILD_DB.child("GenRec");
    Spinner spinner;
    String selection="";
    ProgressDialog dialog;
    ArrayList<String> poss = new ArrayList<>();
    String addr="";
    ListView childlist;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tracking_records);

        spinner = (Spinner) findViewById(R.id.filters);
        spinner.setSelection(0);

        dialog = new ProgressDialog(TrackingRecords.this);
        childlist = (ListView) findViewById(R.id.childlist);

        getAllData();

        spinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {

                selection=""+adapterView.getItemAtPosition(i);

                switch (selection) {
                    case "None":
                        getAllData();
                        break;
                    case "Tracking Somaiya":
                        applyFilter(selection);
                        break;
                    case "Not Tracking":
                        applyFilter(selection);
                        break;
                    case "Tracking Non-Somaiya":
                        applyFilter(selection);
                        break;
                }

            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

        childlist.setEmptyView(findViewById(R.id.empty));
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
                childlist.setAdapter(new ArrayAdapter<>(TrackingRecords.this, R.layout.child_textview, poss));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.dismiss();
            }
        });

    }

    public void applyFilter(final String selection) {

        dialog.setMessage("Searching the database...");
        dialog.show();
        if(poss.size()!=0)
            poss.clear();
        databaseChildHr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UnderFiveCr ufc = ds.getValue(UnderFiveCr.class);


                    if(ufc.getTrack().equals(selection)) {

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
                childlist.setAdapter(new ArrayAdapter<>(TrackingRecords.this, R.layout.child_textview, poss));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.dismiss();
            }
        });
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(TrackingRecords.this,UnderFiveHome.class);
        startActivity(i);
    }
}
