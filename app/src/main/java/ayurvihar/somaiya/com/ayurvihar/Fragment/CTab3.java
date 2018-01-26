package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.provider.ContactsContract;
import android.support.annotation.Nullable;
import android.support.design.widget.FloatingActionButton;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.PopupWindow;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.underfive.ProgressGraph;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveDeleteRec;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveHealthRep;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveUpdateCr;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderfiveScrollview;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;

/**
 * Created by mikasa on 30/7/17.
 */

public class CTab3 extends android.support.v4.app.Fragment {

    FloatingActionButton addhcr,graph;
    DatabaseReference databaseChildHcr = MainActivity.DATABASE_ROOT.child("Underfive").child("ChkRec");
    ArrayList<String> reclist = new ArrayList<>();
    ListView recList;
    PopupWindow pw;
    ArrayList<String> graphWeightList = new ArrayList<>();
    ArrayList<String> graphMonthList   = new ArrayList<>();
    public static SharedPreferences weight,time;
    public SharedPreferences.Editor editorw, editort;
    String listWeightString="",listMonthString="",cid;
    String dob;
    String[] dobv;


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        View view=inflater.inflate(R.layout.ctab3,container,false);
        //editor.clear();

        addhcr = (FloatingActionButton) view.findViewById(R.id.addhcr);
        recList = (ListView) view.findViewById(R.id.reclist);
        graph = (FloatingActionButton)view.findViewById(R.id.graph);

        weight = getContext().getSharedPreferences("Weight",Context.MODE_PRIVATE);
        time = getContext().getSharedPreferences("Weight",Context.MODE_PRIVATE);
        editorw = weight.edit();
        editort = time.edit();
        cid=UnderfiveScrollview.cid;
        dob= UnderfiveScrollview.dob;

        Log.v("null",""+dob);
        dobv = dob.split("-");

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

        graph.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent i = new Intent(CTab3.this.getContext(), ProgressGraph.class);
                Log.v("call","graph");
                i.putExtra("Childid",UnderfiveScrollview.cid);



                startActivity(i);
            }
        });
        return view;
    }


    @Override
    public void onStart() {
        super.onStart();

        databaseChildHcr.child(cid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("val",""+dataSnapshot.getValue());
                if(reclist.size()!=0) {
                    reclist.clear();
                }
                if(graphWeightList.size()!=0)
                    graphWeightList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    Log.v("val",""+ds.getValue());
                    UnderFiveHc uhc = ds.getValue(UnderFiveHc.class);
                    listMonthString="";
                    listWeightString="";
                    String item = "Health Checkup No:"+uhc.gethCheckNo()+"\n"+
                            "Checkup Date: "+uhc.getCdate()+"\n"+
                            "Height: "+uhc.getHeight()+" Weight: "+uhc.getWeight()+"\n"+
                            //"Weight for Age Status: "+uhc.getWfar()+"\n"+
                            "Remarks: "+uhc.getRem()+"\n";
                    reclist.add(item);
                    graphWeightList.add(uhc.getWeight());
                    for(String s: graphWeightList) {
                        listWeightString += s;
                        listWeightString += ",";
                    }
                    graphMonthList.add(monthCalc(uhc.getCdate()));
                    for(String s: graphMonthList) {
                        listMonthString += s;
                        listMonthString += ",";
                    }
                    editorw.putString("WeightList",listWeightString);
                    editort.putString("MonthList",listMonthString);
                    editorw.commit();
                    editort.commit();

                }

                if(getActivity()!=null)
                    recList.setAdapter(new ArrayAdapter<>(CTab3.this.getContext(),R.layout.child_textview,reclist));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });
    }

    public String monthCalc(String date)
    {
        String [] datev = date.split("-");
        int years=0, months=0, days=0;
        years = Integer.parseInt(datev[2]) - Integer.parseInt(dobv[2]);

        months = Integer.parseInt(datev[1]) - Integer.parseInt(dobv[1]);

        days = Integer.parseInt(datev[0]) - Integer.parseInt(dobv[0]);
        if(days<0)
        {
            months--;
            days += 30;
        }

        float month = years*12 + months;
        if(days<25 && days>10)
            month += 0.5;
        if(days>25)
            month++;

        return Float.toString(month);

    }
}
