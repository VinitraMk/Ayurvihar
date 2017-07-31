package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.app.DatePickerDialog;
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
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import org.w3c.dom.Text;

import java.lang.reflect.Field;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Locale;
import java.util.Map;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderFiveUpdateCr;
import ayurvihar.somaiya.com.ayurvihar.underfive.UnderfiveScrollview;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;
import ayurvihar.somaiya.com.ayurvihar.utility.VaccineAdapter;

import static ayurvihar.somaiya.com.ayurvihar.R.id.childidn;

/**
 * Created by mikasa on 30/7/17.
 */

public class CTab2 extends Fragment implements View.OnClickListener {

    DatabaseReference CHILD_DB= MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildImm;

    ListView simpleList;
    ArrayList<UnderFiveImm> vaclist = new ArrayList<>();
    UnderFiveImm ufi;
    VaccineAdapter vaccineAdapter;
    TextView childidn;
    Button setdate;
    Spinner type,vacname;
    String stype,svacname,fvacname="",sdate;
    EditText date;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    ArrayList<UnderFiveImm> ufilist;
    public final static String CLASS_NAME="ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm";


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
    }

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //return super.onCreateView(inflater, container, savedInstanceState);

        databaseChildImm = CHILD_DB.child("ImmRec");
        View view=inflater.inflate(R.layout.ctab2,container,false);
        simpleList = (ListView) view.findViewById(R.id.vaclist);
        childidn = (TextView) view.findViewById(R.id.childidn);
        setdate = (Button) view.findViewById(R.id.setdate);
        type = (Spinner) view.findViewById(R.id.type);
        vacname = (Spinner) view.findViewById(R.id.vacname);
        date = (EditText) view.findViewById(R.id.date);
        date.setInputType(InputType.TYPE_NULL);
        date.requestFocus();
        ufilist=UnderFiveImm.list;

        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();

        //Set dates
        setdate.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                stype=((type.getSelectedItem().toString().trim().toLowerCase()).substring(0,1));
                svacname=(vacname.getSelectedItem().toString().trim().toLowerCase());
                sdate = date.getText().toString().trim();
                fvacname=stype+svacname;

                String str = UnderfiveScrollview.cid;
                updateEntry(str,sdate,fvacname);
            }
        });

        return view;
    }

    @Override
    public void onStart(){
        super.onStart();
        databaseChildImm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                if(vaclist.size()!=0)
                    vaclist.clear();
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    UnderFiveImm tufi=ds.getValue(UnderFiveImm.class);
                    if(tufi.getChildid().trim().equals(UnderfiveScrollview.cid.trim()))
                    {
                        childidn.setText(tufi.getChildid().trim());
                        vaclist.add(new UnderFiveImm("bcg",tufi.getGbcg(),tufi.getDbcg()));
                        vaclist.add(new UnderFiveImm("dpv0",tufi.getGdpv0(),tufi.getDdpv0()));
                        vaclist.add(new UnderFiveImm("hbv0",tufi.getGhbv0(),tufi.getDhbv0()));
                        vaclist.add(new UnderFiveImm("dptopv1",tufi.getGdptopv1(),tufi.getDdptopv1()));
                        vaclist.add(new UnderFiveImm("dptopv2",tufi.getGdptopv2(),tufi.getDdptopv2()));
                        vaclist.add(new UnderFiveImm("dptopv3",tufi.getGdptopv3(),tufi.getDdptopv3()));
                        vaclist.add(new UnderFiveImm("hbv1",tufi.getGhbv1(),tufi.getDhbv1()));
                        vaclist.add(new UnderFiveImm("hbv2",tufi.getGhbv2(),tufi.getDhbv2()));
                        vaclist.add(new UnderFiveImm("hbv3",tufi.getGhbv3(),tufi.getDhbv3()));
                        vaclist.add(new UnderFiveImm("mv1",tufi.getGmv1(),tufi.getDmv1()));
                        vaclist.add(new UnderFiveImm("mmr",tufi.getGmmr(),tufi.getDmmr()));
                        vaclist.add(new UnderFiveImm("dobv2",tufi.getGdobv2(),tufi.getDdobv2()));
                        vaclist.add(new UnderFiveImm("v3",tufi.getGv3(),tufi.getDv3()));
                        vaclist.add(new UnderFiveImm("v4",tufi.getGv4(),tufi.getDv4()));
                        vaclist.add(new UnderFiveImm("v5",tufi.getGv5(),tufi.getDv5()));
                        vaclist.add(new UnderFiveImm("v6",tufi.getGv6(),tufi.getDv6()));
                        vaclist.add(new UnderFiveImm("v7",tufi.getGv7(),tufi.getDv7()));
                        vaclist.add(new UnderFiveImm("v8",tufi.getGv8(),tufi.getDv8()));
                        vaclist.add(new UnderFiveImm("dv9",tufi.getGdv9(),tufi.getDdv9()));
                        //mapToList((Map)dataSnapshot.getValue());
                        if(getActivity()!=null){
                            vaccineAdapter = new VaccineAdapter(getContext(),R.layout.imm_item,vaclist);
                            simpleList.setAdapter(vaccineAdapter);
                        }
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //UnderfiveScrollview.dialog.dismiss();
            }
        });
    }

    private void setDateTimeField() {
        date.setOnClickListener(this);
        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(CTab2.this.getContext(), new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                date.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == date) {
            datePickerDialog.show();
        }
    }

    private void updateEntry(String str, final String val, final String fiename)
    {
        UnderfiveScrollview.dialog.setMessage("Saving changes to database...");
        UnderfiveScrollview.dialog.show();
        final String tempstr=str;
        final String tempval=val;
        final String tempfie=fiename;
        databaseChildImm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for(DataSnapshot ds:dataSnapshot.getChildren())
                {
                    ufi=ds.getValue(UnderFiveImm.class);
                    Log.v("immh",ufi.getChildid()+" "+tempstr);
                    if(ufi.getChildid().trim().equals(tempstr.trim()))
                    {
                        ds.getRef().child(tempfie).setValue(tempval);
                        UnderfiveScrollview.dialog.dismiss();
                        //break;
                    }
                }
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                //UnderfiveScrollview.dialog.dismiss();
            }
        });

    }

    private UnderFiveImm updateClassEntry(String str){
        for(UnderFiveImm obj:ufilist){
            if(obj.getChildid().trim().equals(str.trim())){
                return obj;
            }
        }
        return null;
    }


}
