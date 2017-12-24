package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.ProgressDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveIntervals;
import ayurvihar.somaiya.com.ayurvihar.utility.VaccineAdapter;
import ayurvihar.somaiya.com.ayurvihar.utility.VaccineDetailAdapter;

public class UnderFiveVacDet extends AppCompatActivity {

    Spinner sVac;
    EditText eVal;
    Button set;
    String vacarr[];
    String esval="",vacname="";
    int intval;

    ProgressDialog dialog;

    ArrayList<UnderFiveIntervals> vaclist = new ArrayList<>();
    ListView simpleList;

    VaccineDetailAdapter vaccineDetailAdapter;

    DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildInt=CHILD_DB.child("Intervals");
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_five_vac_det);

        vacarr=UnderFiveVacDet.this.getResources().getStringArray(R.array.vaccines);
        sVac=(Spinner) findViewById(R.id.vaccine);
        eVal=(EditText) findViewById(R.id.eval);
        set=(Button) findViewById(R.id.setval);
        simpleList=(ListView)findViewById(R.id.vaclist);
        dialog = new ProgressDialog(UnderFiveVacDet.this);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esval=eVal.getText().toString().trim();
                vacname=sVac.getSelectedItem().toString().trim();
                if(esval.equals(""))
                    Toast.makeText(UnderFiveVacDet.this,"Enter value of the vaccine",Toast.LENGTH_SHORT);
                else {
                    addValue(vacname,esval);
                }

            }
        });
    }

    @Override
    public void onStart() {
        super.onStart();

        dialog.setMessage("Searching the database..");
        dialog.show();
        databaseChildInt.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if (vaclist.size() != 0)
                    vaclist.clear();
                Log.v("intervals",""+dataSnapshot.getKey());
                UnderFiveIntervals tufi = dataSnapshot.getValue(UnderFiveIntervals.class);
                vaclist.add(new UnderFiveIntervals("bcg",tufi.getBcg()));
                vaclist.add(new UnderFiveIntervals("dpv0", tufi.getDpv0()));
                vaclist.add(new UnderFiveIntervals("hbv0", tufi.getHbv0()));
                vaclist.add(new UnderFiveIntervals("dptopv1", tufi.getDptopv1()));
                vaclist.add(new UnderFiveIntervals("dptopv2", tufi.getDptopv2()));
                vaclist.add(new UnderFiveIntervals("dptopv3", tufi.getDptopv3()));
                vaclist.add(new UnderFiveIntervals("hbv1", tufi.getHbv1()));
                vaclist.add(new UnderFiveIntervals("hbv2", tufi.getHbv2()));
                vaclist.add(new UnderFiveIntervals("hbv3", tufi.getHbv3()));
                vaclist.add(new UnderFiveIntervals("mv1", tufi.getMv1()));
                vaclist.add(new UnderFiveIntervals("mmr", tufi.getMmr()));
                vaclist.add(new UnderFiveIntervals("dobv2", tufi.getDobv2()));
                vaclist.add(new UnderFiveIntervals("v3", tufi.getV3()));
                vaclist.add(new UnderFiveIntervals("v4", tufi.getV4()));
                vaclist.add(new UnderFiveIntervals("v5", tufi.getV5()));
                vaclist.add(new UnderFiveIntervals("v6", tufi.getV6()));
                vaclist.add(new UnderFiveIntervals("v7", tufi.getV7()));
                vaclist.add(new UnderFiveIntervals("v8", tufi.getV8()));
                vaclist.add(new UnderFiveIntervals("dv9", tufi.getDv9()));

                vaccineDetailAdapter = new VaccineDetailAdapter(UnderFiveVacDet.this, R.layout.immint_item, vaclist);
                simpleList.setAdapter(vaccineDetailAdapter);
                dialog.dismiss();
            }




            @Override
            public void onCancelled(DatabaseError databaseError) {
                dialog.dismiss();
            }
        });

    }

    public void addValue(String vacname,String esval) {

        intval = Integer.parseInt(esval);
        databaseChildInt.child(vacname).getRef().setValue(intval);

    }
}
