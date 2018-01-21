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
    ArrayList<String> graphList = new ArrayList<>();
    public static SharedPreferences weightForAge;
    public SharedPreferences.Editor editor;
    String listString="",cid;

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

        weightForAge = getContext().getSharedPreferences("Weightforage",Context.MODE_PRIVATE);
        editor = weightForAge.edit();
        cid=UnderfiveScrollview.cid;

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

        /*databaseChildHcr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(reclist.size()!=0) {
                    reclist.clear();
                }
                if(graphList.size()!=0)
                    graphList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    UnderFiveHc uhc=ds.getValue(UnderFiveHc.class);
                    listString="";
                    if(uhc.getChildid().trim().equals(UnderfiveScrollview.cid.trim()))
                    {
                        String item = "Health Checkup No:"+uhc.gethCheckNo()+"\n"+
                                "Checkup Date: "+uhc.getCdate()+"\n"+
                                "Height: "+uhc.getHeight()+" Weight: "+uhc.getWeight()+"\n"+
                                "Weight for Age Status: "+uhc.getWfar()+"\n"+
                                "Remarks: "+uhc.getRem()+"\n";
                        reclist.add(item);
                        graphList.add(uhc.getWfar());
                    }
                    for(String s: graphList) {
                        listString += s;
                        listString += ",";
                    }
                    editor.putString("Weightforagelist",listString);
                    editor.commit();
                }
                if(getActivity()!=null)
                    recList.setAdapter(new ArrayAdapter<>(CTab3.this.getContext(),R.layout.child_textview,reclist));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/

        databaseChildHcr.child(cid).addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("val",""+dataSnapshot.getValue());
                if(reclist.size()!=0) {
                    reclist.clear();
                }
                if(graphList.size()!=0)
                    graphList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    Log.v("val",""+ds.getValue());
                    UnderFiveHc uhc = ds.getValue(UnderFiveHc.class);
                    listString="";
                    String item = "Health Checkup No:"+uhc.gethCheckNo()+"\n"+
                            "Checkup Date: "+uhc.getCdate()+"\n"+
                            "Height: "+uhc.getHeight()+" Weight: "+uhc.getWeight()+"\n"+
                            "Weight for Age Status: "+uhc.getWfar()+"\n"+
                            "Remarks: "+uhc.getRem()+"\n";
                    reclist.add(item);
                    graphList.add(uhc.getWfar());
                    for(String s: graphList) {
                        listString += s;
                        listString += ",";
                    }
                    editor.putString("Weightforagelist",listString);
                    editor.commit();

                }

                if(getActivity()!=null)
                    recList.setAdapter(new ArrayAdapter<>(CTab3.this.getContext(),R.layout.child_textview,reclist));

            }
            @Override
            public void onCancelled(DatabaseError databaseError) {
            }
        });


        /*Query query;
        query = databaseChildHcr.orderByKey().equalTo(cid);
        query.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Log.v("val",""+dataSnapshot.getKey());
                if(reclist.size()!=0) {
                    reclist.clear();
                }
                if(graphList.size()!=0)
                    graphList.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    UnderFiveHc uhc = ds.getValue(UnderFiveHc.class);
                    Log.v("val",""+ds.getValue());
                    Log.v("val",""+uhc.getChildid());
                    String item = "Health Checkup No:"+uhc.gethCheckNo()+"\n"+
                            "Checkup Date: "+uhc.getCdate()+"\n"+
                            "Height: "+uhc.getHeight()+" Weight: "+uhc.getWeight()+"\n"+
                            "Weight for Age Status: "+uhc.getWfar()+"\n"+
                            "Remarks: "+uhc.getRem()+"\n";
                    reclist.add(item);
                    graphList.add(uhc.getWfar());

                    for(String s: graphList) {
                        listString += s;
                        listString += ",";
                    }
                    editor.putString("Weightforagelist",listString);
                    editor.commit();
                }
                if(getActivity()!=null)
                    recList.setAdapter(new ArrayAdapter<>(CTab3.this.getContext(),R.layout.child_textview,reclist));
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });*/
    }
}
