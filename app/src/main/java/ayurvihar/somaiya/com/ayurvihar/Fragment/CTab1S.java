package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveSearch1Cr;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveSearch1CrOutput;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveUpdateCr;
import ayurvihar.somaiya.com.ayurvihar.underfive.VaccineSummary;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by heretic on 10/8/17.
 */

public class CTab1S extends BackHandledFragment {
    DatabaseReference CHILD_DB= MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildImm;
    ArrayList<String> vacclist;
    ListView vaccList;
    String date="";

    UnderFiveImm ufi;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseChildImm = CHILD_DB.child("ImmRec");
        View view=inflater.inflate(R.layout.ctab1s,container,false);
        vaccList = (ListView) view.findViewById(R.id.vacclist);
        vacclist = new ArrayList<String>();
        return view;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        date= UnderFiveSearch1CrOutput.date;

        vaccList.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
                String str = vacclist.get(i);
                Log.v("str",str);
                int in = str.indexOf("Child id");
                int in1 = str.indexOf("Vaccines");
                if(in!=-1 && in1!=-1) {
                    in+=10;
                    String cid = str.substring(in,in+15).trim();
                    Intent intent = new Intent(getContext(), VaccineSummary.class);
                    intent.putExtra("childid",cid);
                    str=str.substring(in1,str.length());
                    Log.v("currdue",str);
                    intent.putExtra("currdue",str);
                    startActivity(intent);
                }
            }
        });

        databaseChildImm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(vacclist.size()!=0)
                    vacclist.clear();
                for(DataSnapshot ds: dataSnapshot.getChildren()) {
                    ufi = ds.getValue(UnderFiveImm.class);
                    Log.v("indate",date);
                    ArrayList<String> list = ufi.getVal(ufi,date);
                    if(list.size()!=0) {
                        String item = "Child id: "+ds.child("childid").getValue(String.class);
                        item+="\nVaccines: ";
                        for (String str : list) {
                            item += "\n"+str;
                        }
                        item += "\n";
                        vacclist.add(item);
                    }
                }
                vaccList.setAdapter(new ArrayAdapter<String>(CTab1S.this.getContext(), R.layout.child_textview,vacclist));

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

