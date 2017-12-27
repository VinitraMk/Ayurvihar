package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveSearch1Cr;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveSearch1CrOutput;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveUpdateCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by heretic on 10/8/17.
 */

public class CTab2S extends BackHandledFragment {

    DatabaseReference CHILD_DB= MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildImm;
    ListView vaccList;
    String date="";
    ArrayList<String> vacclist;
    Map<String,Integer>  vacCount;
    UnderFiveImm ufi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseChildImm = CHILD_DB.child("ImmRec");
        View view=inflater.inflate(R.layout.ctab2s,container,false);
        vaccList = (ListView) view.findViewById(R.id.vacclist);
        vacclist = new ArrayList<String>();
        vacCount = new HashMap<String,Integer>();
        initVc();

        return view;
    }

    public void initVc() {
        vacCount.put("BCG",0);
        vacCount.put("DPV0",0);
        vacCount.put("HBV0",0);
        vacCount.put("DPT/OPV1",0);
        vacCount.put("DPT/OPV2",0);
        vacCount.put("DPT/OPV3",0);
        vacCount.put("HBV1",0);
        vacCount.put("HBV2",0);
        vacCount.put("HBV3",0);
        vacCount.put("MV1",0);
        vacCount.put("MMR",0);
        vacCount.put("DOBV2",0);
        vacCount.put("V3",0);
        vacCount.put("V4",0);
        vacCount.put("V5",0);
        vacCount.put("V6",0);
        vacCount.put("V7",0);
        vacCount.put("V8",0);
        vacCount.put("DV9",0);


    }

    @Override
    public void onStart() {
        super.onStart();
        date= UnderFiveSearch1CrOutput.date;

        databaseChildImm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(vacclist.size()!=0) {
                    vacclist.clear();
                    initVc();
                }


                for(DataSnapshot ds: dataSnapshot.getChildren())
                {
                    ufi=ds.getValue(UnderFiveImm.class);
                    vacCount = ufi.getVacc(ufi,date,vacCount);
                }
                int count =0;
                for (String x: vacCount.keySet())
                {
                    if(vacCount.get(x)!=0) {
                        int temp = vacCount.get(x);
                        String item = x + ":\t" + temp;
                        vacclist.add(item);
                        count+=temp;
                    }
                }

                String tot = "Total:\t" + count;
                vacclist.add(tot);
                vaccList.setAdapter(new ArrayAdapter<String>(CTab2S.this.getContext(), R.layout.child_textview,vacclist));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    @Override
    public boolean onBackPressed() {
        Intent i = new Intent(getContext(),UnderFiveSearch1Cr.class);
        i.removeExtra("date");
        startActivity(i);
        return true;
    }

    @Override
    public String getTagText() {
        return null;
    }
}
