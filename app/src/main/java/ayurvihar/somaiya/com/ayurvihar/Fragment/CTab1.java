package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.text.InputType;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveHome;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveUpdateCr;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderfiveScrollview;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by mikasa on 30/7/17.
 */

public class CTab1 extends Fragment implements View.OnClickListener{

    EditText Addn1,Addn2,Addn3,Addn4,Addn5,Addn6,Addn7,Addn8,Addn9;
    Spinner sGen,sNic,sTown,sAc,sTrack;
    TextView childidn;
    Button update;
    DatabaseReference CHILD_DB= MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildHr;
    String townarr[],acarr[];
    String genarr[];
    String nicarr[];
    String trackarr[];
    int townin,acin,genin,nicin,trackin;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;

    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {


        View view=inflater.inflate(R.layout.ctab1,container,false);

        databaseChildHr=CHILD_DB.child("GenRec");
        townarr=CTab1.this.getResources().getStringArray(R.array.towns);
        acarr=CTab1.this.getResources().getStringArray(R.array.areacode);
        genarr=CTab1.this.getResources().getStringArray(R.array.gender);
        nicarr=CTab1.this.getResources().getStringArray(R.array.nic);
        trackarr=CTab1.this.getResources().getStringArray(R.array.track);

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
        sTrack = (Spinner) view.findViewById(R.id.track);

        childidn = (TextView) view.findViewById(R.id.childidn);
        update = (Button) view.findViewById(R.id.Update);

        Addn9.setInputType(InputType.TYPE_NULL);
        Addn9.requestFocus();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();

        update.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Log.v("upd",""+sNic.getSelectedItem());
                databaseChildHr.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren())
                        {
                            UnderFiveCr tufc=ds.getValue(UnderFiveCr.class);
                            if(tufc.getChildid().trim().equals(UnderfiveScrollview.cid.trim()))
                            {
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
                                ds.child("track").getRef().setValue(sTrack.getSelectedItem().toString().trim());
                                Toast.makeText(getContext(),"Updated the database",Toast.LENGTH_SHORT);
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
        databaseChildHr.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot ds : dataSnapshot.getChildren()) {
                    UnderFiveCr tempufc=ds.getValue(UnderFiveCr.class);
                    if(tempufc.getChildid().trim().equals(UnderfiveScrollview.cid.trim()))
                    {
                        childidn.setText(tempufc.getChildid());
                        Addn1.setText(tempufc.getFname());
                        Addn2.setText(tempufc.getLname());
                        Addn3.setText(tempufc.getMoname());
                        Addn4.setText(tempufc.getFatname());
                        Addn5.setText(tempufc.getRoom());
                        Addn6.setText(tempufc.getBldg());
                        Addn7.setText(tempufc.getArea());
                        Addn8.setText(tempufc.getMob());
                        Addn9.setText(tempufc.getDob());
                        Addn8.setText(tempufc.getMob());
                        Addn9.setText(tempufc.getDob());

                        townin=Arrays.binarySearch(townarr,tempufc.getTown());
                        sTown.setSelection(townin);

                        genin=Arrays.binarySearch(genarr,tempufc.getGen());
                        sGen.setSelection(genin);

                        nicin = Arrays.binarySearch(nicarr,tempufc.getNic());
                        sNic.setSelection(nicin);

                        acin= Arrays.binarySearch(acarr,tempufc.getAc());
                        sAc.setSelection(acin);

                        trackin = Arrays.binarySearch(trackarr,tempufc.getTrack());
                        sTrack.setSelection(trackin);

                    }

                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

    }

    public int indexOf(String[] arr,String key) {
        return Arrays.binarySearch(arr,key);

    }

    private void setDateTimeField() {
        Addn9.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Addn9.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == Addn9) {
            datePickerDialog.show();
        }
    }
}
