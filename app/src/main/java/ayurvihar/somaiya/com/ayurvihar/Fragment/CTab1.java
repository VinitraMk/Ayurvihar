package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.Map;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveUpdateCr;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderfiveScrollview;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by mikasa on 30/7/17.
 */

public class CTab1 extends Fragment {

    EditText Addn1,Addn2,Addn3,Addn4,Addn5,Addn6,Addn7,Addn8,Addn9;
    Spinner sGen,sNic,sTown,sAc;
    TextView childidn;
    Button update;
    DatabaseReference CHILD_DB= MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildHr,tempRef;
    UnderFiveCr tempufc;
    String townarr[],acarr[];
    String[] genarr={"Male","Female"};
    String[] nicarr={"Yes","No"};
    int townin,acin;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {

        //return super.onCreateView(inflater, container, savedInstanceState);

        View view=inflater.inflate(R.layout.ctab1,container,false);

        databaseChildHr=CHILD_DB.child("GenRec");
        townarr=CTab1.this.getResources().getStringArray(R.array.towns);
        acarr=CTab1.this.getResources().getStringArray(R.array.areacode);

        Addn1 = (EditText) view.findViewById(R.id.Addn1);
        Addn2 = (EditText) view.findViewById(R.id.Addn2);
        Addn3 = (EditText) view.findViewById(R.id.Addn3);
        Addn4 = (EditText) view.findViewById(R.id.Addn4);
        Addn5 = (EditText) view.findViewById(R.id.Addn5);
        Addn6 = (EditText) view.findViewById(R.id.Addn6);
        Addn7 = (EditText) view.findViewById(R.id.Addn7);
        Addn8 = (EditText) view.findViewById(R.id.Addn8);
        Addn9 = (EditText) view.findViewById(R.id.Addn9);

        sGen = (Spinner) view.findViewById(R.id.gender);
        sNic = (Spinner) view.findViewById(R.id.nic);
        sTown = (Spinner) view.findViewById(R.id.towns);
        sAc = (Spinner) view.findViewById(R.id.areacode);

        childidn = (TextView) view.findViewById(R.id.childidn);
        update = (Button) view.findViewById(R.id.Update);

        //loadEntries();

        Addn9.setInputType(InputType.TYPE_NULL);
        Addn9.requestFocus();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                databaseChildHr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            UnderFiveCr tufc=ds.getValue(UnderFiveCr.class);
                            if(tufc.getChildid().trim().equals(UnderfiveScrollview.cid.trim()))
                            {
                                Log.v("hello",""+ds.child("fname"));
                                ds.child("fname").getRef().setValue(Addn1.getText().toString().trim());
                                ds.child("lname").getRef().setValue(Addn2.getText().toString().trim());
                                ds.child("moname").getRef().setValue(Addn3.getText().toString().trim());
                                ds.child("fatname").getRef().setValue(Addn4.getText().toString().trim());
                                ds.child("room").getRef().setValue(Addn5.getText().toString().trim());
                                ds.child("bldg").getRef().setValue(Addn6.getText().toString().trim());
                                ds.child("town").getRef().setValue(sTown.getSelectedItem().toString().trim());
                                ds.child("area").getRef().setValue(Addn7.getText().toString().trim());
                                ds.child("ac").getRef().setValue(sAc.getSelectedItem().toString().trim());
                                ds.child("mob").getRef().setValue(Addn8.getText().toString().trim());
                                ds.child("dob").getRef().setValue(Addn9.getText().toString().trim());
                                ds.child("gen").getRef().setValue(sGen.getSelectedItem().toString().trim());
                                ds.child("nic").getRef().setValue(sNic.getSelectedItem().toString().trim());
                            }
                        }
                    }

                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });
        return view;

    }

    private int indexOf(String str, String arr[])
    {
        int i=0;
        int n=arr.length-1,m=-1;
        for(i=0;i<n;i++)
        {
            if(arr[i].equals(str))
            {
                m=i;
                break;
            }
        }
        return m;
    }

    @Override
    public void onStart()
    {
        super.onStart();
        //UnderfiveScrollview.dialog.setMessage("Loading from database...");
        databaseChildHr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                //Log.v("hello",""+dataSnapshot.getValue());
                //UnderFiveCr tempufc=dataSnapshot.getValue(UnderFiveCr.class);
                //Log.v("hello",""+tempufc.getFname());
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UnderFiveCr tempufc=ds.getValue(UnderFiveCr.class);
                    if(tempufc.getChildid().trim().equals(UnderfiveScrollview.cid.trim()))
                    {
                        Addn1.setText(tempufc.getFname());
                        Addn2.setText(tempufc.getLname());
                        Addn3.setText(tempufc.getMoname());
                        Addn4.setText(tempufc.getFatname());
                        Addn5.setText(tempufc.getRoom());
                        Addn6.setText(tempufc.getBldg());

                        townin=indexOf(tempufc.getTown(),townarr);
                        sTown.setSelection(townin);

                        Addn7.setText(tempufc.getArea());

                        acin=indexOf(tempufc.getAc(),acarr);
                        sAc.setSelection(acin);

                        Addn8.setText(tempufc.getMob());
                        Addn9.setText(tempufc.getDob());

                        if(tempufc.getGen().equals("Male"))
                            sGen.setSelection(0);
                        else
                            sGen.setSelection(1);

                        if(tempufc.getNic().equals("Yes"))
                            sGen.setSelection(1);
                        else
                            sGen.setSelection(0);
                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }


}
