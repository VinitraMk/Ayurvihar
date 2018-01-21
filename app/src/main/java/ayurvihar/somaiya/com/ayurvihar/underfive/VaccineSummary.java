package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.app.ProgressDialog;
import android.provider.ContactsContract;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
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
import java.util.Iterator;
import java.util.List;
import java.util.Locale;
import java.util.Map;

import ayurvihar.somaiya.com.ayurvihar.Fragment.CTab1;
import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

public class VaccineSummary extends AppCompatActivity {

    TextView tfname,tlname,tmoname,tfatname,troom,tline,ttown,tac,tacno,tmob,tdob,tgen,tnic,ttrack;
    TextView childidn;
    ListView childlist;
    DatabaseReference CHILD_DB= MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildImm,databaseChildCr;
    String townarr[],acarr[];
    String genarr[];
    String nicarr[];
    int townin,acin,genin,nicin;
    ArrayList<String> poss=new ArrayList<>();
    String addr="",sfn,sln,smn,sftn,sroom,sline,stown,sac,sacno,smob,sdob,sgen,snc,strk,cid,duevac;



    ProgressDialog dialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vaccine_summary);

        databaseChildImm=CHILD_DB.child("ImmRec");
        databaseChildCr=CHILD_DB.child("GenRec");
        townarr=this.getResources().getStringArray(R.array.towns);
        acarr=this.getResources().getStringArray(R.array.areacode);
        genarr=this.getResources().getStringArray(R.array.gender);
        nicarr=this.getResources().getStringArray(R.array.nic);

        childidn = (TextView)findViewById(R.id.childidn);
        tfname = (TextView)findViewById(R.id.tfname);
        tlname = (TextView)findViewById(R.id.tlname);
        tmoname = (TextView)findViewById(R.id.tmoname);
        tfatname = (TextView)findViewById(R.id.tfatname);
        troom = (TextView)findViewById(R.id.troom);
        tline = (TextView)findViewById(R.id.tline);
        ttown = (TextView)findViewById(R.id.ttown);
        tac = (TextView)findViewById(R.id.tac);
        tacno = (TextView)findViewById(R.id.tacno);
        tmob = (TextView)findViewById(R.id.tmob);
        tdob = (TextView)findViewById(R.id.tdob);
        tgen = (TextView)findViewById(R.id.tgen);
        tnic = (TextView)findViewById(R.id.tnic);
        ttrack = (TextView)findViewById(R.id.ttrack);

        childlist = (ListView)findViewById(R.id.childlist);

        dialog = new ProgressDialog(VaccineSummary.this);

        cid = getIntent().getStringExtra("childid").trim();
        duevac = getIntent().getStringExtra("currdue").trim();

        getAllData();
    }

    public void getAllData() {

        dialog.setMessage("Searching the database...");
        dialog.show();
        if(poss.size()!=0)
            poss.clear();

        Query query ;
        query = databaseChildCr.orderByChild("childid").equalTo(cid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.v("val",""+dataSnapshot.child(dataSnapshot.));
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    UnderFiveCr ufc = ds.getValue(UnderFiveCr.class);
                    childidn.setText(ufc.getChildid());
                    tfname.setText(ufc.getFname());
                    tlname.setText(ufc.getLname());
                    tmoname.setText(ufc.getMoname());
                    tfatname.setText(ufc.getFatname());
                    troom.setText(ufc.getRoom());
                    tline.setText(ufc.getBldg());
                    ttown.setText(ufc.getTown());
                    tac.setText(ufc.getArea());
                    tacno.setText(ufc.getAc());
                    tmob.setText(ufc.getMob());
                    tdob.setText(ufc.getDob());
                    tgen.setText(ufc.getGen());
                    tnic.setText(ufc.getNic());
                    ttrack.setText(ufc.getTrack());
                }
                dialog.dismiss();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


        Query query1;
        query1 = databaseChildImm.orderByChild("childid").equalTo(cid);
        query1.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(poss.size()!=0)
                    poss.clear();

                Log.v("duevac",""+dataSnapshot.getValue());

                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    UnderFiveImm uim = ds.getValue(UnderFiveImm.class);
                    HashMap<String,Integer> hp = uim.getMissingVac(uim);
                    String data = "Missed Vaccines:\n";
                    if(hp.size()>0) {
                        for (String sit : hp.keySet())
                            data += sit + ": " + hp.get(sit) + "\n";
                    }
                    else
                        data+="No Vaccines Missed\n";
                    data += "Due " + duevac + "\n";
                    Log.v("duevac",duevac);
                    poss.add(data);
                }
                childlist.setAdapter(new ArrayAdapter<>(VaccineSummary.this,R.layout.child_textview,poss));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

        /*databaseChildImm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(poss.size()!=0)
                    poss.clear();

                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    if(ds.child("childid").equals(cid)) {
                        UnderFiveImm uim = ds.getValue(UnderFiveImm.class);
                        HashMap<String,Integer> hp = uim.getMissingVac(uim);
                        String data = "Missed Vaccines:\n";
                        if(hp.size()>0) {
                            for (String sit : hp.keySet())
                                data += sit + ": " + hp.get(sit) + "\n";
                        }
                        else
                            data+="No Vaccines Missed\n";
                        data += "Due " + duevac + "\n";
                        Log.v("due",duevac);
                        poss.add(data);
                    }
                }
                childlist.setAdapter(new ArrayAdapter<>(VaccineSummary.this,R.layout.child_textview,poss));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }


}
