package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.util.Pair;
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
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveSearch1CrOutput;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by heretic on 10/8/17.
 */

public class CTab1S extends Fragment {
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
                        String item = ds.child("childid").getValue(String.class);
                        item+=": ";
                        for (String str : list) {
                            item += str + "\t";
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
}

