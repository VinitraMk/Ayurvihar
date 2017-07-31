package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
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

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveHealthRep;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderfiveScrollview;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;

/**
 * Created by mikasa on 30/7/17.
 */

public class CTab3 extends Fragment {

    FloatingActionButton addhcr;
    DatabaseReference databaseChildHcr = MainActivity.DATABASE_ROOT.child("Underfive").child("ChkRec");
    ArrayList<String> reclist = new ArrayList<>();
    ListView recList;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view=inflater.inflate(R.layout.ctab3,container,false);

        addhcr = (FloatingActionButton) view.findViewById(R.id.addhcr);
        recList = (ListView) view.findViewById(R.id.reclist);

        addhcr.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CTab3.this.getContext(), UnderFiveHealthRep.class);
                i.putExtra("Nocu",reclist.size());
                i.putExtra("Childid",UnderfiveScrollview.cid);
                i.putExtra("Dob",UnderfiveScrollview.dob);
                startActivity(i);
            }
        });
        return view;
    }

    @Override
    public void onStart() {
        super.onStart();

        databaseChildHcr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(reclist.size()!=0)
                    reclist.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    UnderFiveHc uhc=ds.getValue(UnderFiveHc.class);
                    if(uhc.getChildid().trim().equals(UnderfiveScrollview.cid.trim()))
                    {
                        String item = "Health Checkup No:"+uhc.gethCheckNo()+"\n"+
                                "Checkup Date: "+uhc.getCdate()+"\n"+
                            "Height: "+uhc.getHeight()+" Weight: "+uhc.getWeight()+"\n"+
                            "Weight for Age Status: "+uhc.getWfar()+"\n"+
                            "Remarks: "+uhc.getRem()+"\n";
                        reclist.add(item);
                    }
                }
                recList.setAdapter(new ArrayAdapter<String>(CTab3.this.getContext(),R.layout.child_textview,reclist));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }
}
