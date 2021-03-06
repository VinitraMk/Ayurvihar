package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.ProgressDialog;
import android.content.Intent;
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

    Spinner sVac,sOpt;
    EditText eVal;
    Button set;
    String vacarr[];
    String esval="",vacname="",opt="";
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
        sOpt=(Spinner) findViewById(R.id.selopt);
        eVal=(EditText) findViewById(R.id.eval);
        set=(Button) findViewById(R.id.setval);
        simpleList=(ListView)findViewById(R.id.vaclist);
        dialog = new ProgressDialog(UnderFiveVacDet.this);

        set.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                esval=eVal.getText().toString().trim();
                vacname=sVac.getSelectedItem().toString().trim();
                opt=sOpt.getSelectedItem().toString().trim();
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
                vaclist.add(new UnderFiveIntervals("bcg",tufi.getBcg(),tufi.getTrbcg()));
                vaclist.add(new UnderFiveIntervals("dpv0", tufi.getDpv0(),tufi.getTrdpv0()));
                vaclist.add(new UnderFiveIntervals("hbv0", tufi.getHbv0(),tufi.getTrhbv0()));
                vaclist.add(new UnderFiveIntervals("dptopv1", tufi.getDptopv1(),tufi.getTrdptopv1()));
                vaclist.add(new UnderFiveIntervals("dptopv2", tufi.getDptopv2(),tufi.getTrdptopv2()));
                vaclist.add(new UnderFiveIntervals("dptopv3", tufi.getDptopv3(),tufi.getTrdptopv3()));
                vaclist.add(new UnderFiveIntervals("hbv1", tufi.getHbv1(),tufi.getTrhbv1()));
                vaclist.add(new UnderFiveIntervals("hbv2", tufi.getHbv2(),tufi.getTrhbv2()));
                vaclist.add(new UnderFiveIntervals("hbv3", tufi.getHbv3(),tufi.getTrhbv3()));
                vaclist.add(new UnderFiveIntervals("mv1", tufi.getMv1(),tufi.getTrmv1()));
                vaclist.add(new UnderFiveIntervals("mmr", tufi.getMmr(),tufi.getTrmmr()));
                vaclist.add(new UnderFiveIntervals("dobv2", tufi.getDobv2(),tufi.getTrdobv2()));
                vaclist.add(new UnderFiveIntervals("v3", tufi.getV3(),tufi.getTrv3()));
                vaclist.add(new UnderFiveIntervals("v4", tufi.getV4(),tufi.getTrv4()));
                vaclist.add(new UnderFiveIntervals("v5", tufi.getV5(),tufi.getTrv5()));
                vaclist.add(new UnderFiveIntervals("v6", tufi.getV6(),tufi.getTrv6()));
                vaclist.add(new UnderFiveIntervals("v7", tufi.getV7(),tufi.getTrv7()));
                vaclist.add(new UnderFiveIntervals("v8", tufi.getV8(),tufi.getTrv8()));
                vaclist.add(new UnderFiveIntervals("dv9", tufi.getDv9(),tufi.getTrdv9()));

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
        if(opt.equals("Interval"))
            databaseChildInt.child(vacname).getRef().setValue(intval);
        else
            databaseChildInt.child(("tr"+vacname)).getRef().setValue(intval);
        Toast.makeText(UnderFiveVacDet.this,"Updated database",Toast.LENGTH_SHORT);
    }

    @Override
    public void onBackPressed() {
        Intent i = new Intent(UnderFiveVacDet.this,UnderFiveHome.class);
        startActivity(i);
    }
}
