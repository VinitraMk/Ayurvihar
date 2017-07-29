package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by mikasa on 6/7/17.
 */


public class UnderFiveAddCr extends AppCompatActivity implements View.OnClickListener{

    EditText Addn1 , Addn2 , Addn3 , Addn4 , Addn5 , Addn6 , Addn7 , Addn8 , Addn9;
    DatePicker DOB;
    String fname,ln,mn,fn,ci,fi,room,bldg,town,area,ac,mob,dob,nic,gen;
    int month,day,year;
    Spinner sGen,sNic,sTown,sAc;
    Button AddRecord,Refresh;
    SimpleDateFormat dateFormatter,ts;
    private DatePickerDialog datePickerDialog;
    DatabaseReference CHILD_DB=MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildHr,databaseChildHcr,databaseChildImm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_addcr);

        databaseChildHr=CHILD_DB.child("GenRec");
        databaseChildHcr=CHILD_DB.child("ChkRec");
        databaseChildImm=CHILD_DB.child("ImmRec");

        Addn1 = (EditText) findViewById(R.id.Addn1);
        Addn2 = (EditText) findViewById(R.id.Addn2);
        Addn3 = (EditText) findViewById(R.id.Addn3);
        Addn4 = (EditText) findViewById(R.id.Addn4);
        Addn5 = (EditText) findViewById(R.id.Addn5);
        Addn6 = (EditText) findViewById(R.id.Addn6);
        Addn7 = (EditText) findViewById(R.id.Addn7);
        Addn8 = (EditText) findViewById(R.id.Addn8);
        Addn9 = (EditText) findViewById(R.id.Addn9);

        //DOB = (DatePicker) findViewById(R.id.DOB);

        Addn9.setInputType(InputType.TYPE_NULL);
        Addn9.requestFocus();


        AddRecord = (Button) findViewById(R.id.AddRecord);
        Refresh = (Button) findViewById(R.id.Refresh);
        sGen = (Spinner) findViewById(R.id.gender);
        sNic = (Spinner) findViewById(R.id.nic);
        sTown = (Spinner) findViewById(R.id.towns);
        sAc = (Spinner) findViewById(R.id.areacode);

        //Execurting spinner list for selecting date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        ts = new SimpleDateFormat("dd-MM-yyyy'|'HH:mm:ss",Locale.US);
        String timeStamp = ts.format(new Date());
        timeStamp=(timeStamp.substring(0,2)+timeStamp.substring(3,5)+timeStamp.substring(6,10)+timeStamp.substring(11,13)+timeStamp.substring(14,16)+timeStamp.substring(17,19));
        ci=timeStamp;
        Log.v("ufu",""+timeStamp);
        setDateTimeField();


        //Database handling of Under5 begins

        AddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=Addn1.getText().toString().trim();
                ln=Addn2.getText().toString().trim();
                mn=Addn3.getText().toString().trim();
                fn=Addn4.getText().toString().trim();
                room=Addn5.getText().toString().trim();
                bldg=Addn6.getText().toString().trim();
                town=sTown.getSelectedItem().toString();
                area=Addn7.getText().toString().trim();
                ac=sAc.getSelectedItem().toString();
                mob=Addn8.getText().toString().trim();
                dob=Addn9.getText().toString().trim();
                gen=sGen.getSelectedItem().toString();
                nic=sNic.getSelectedItem().toString();
                if(!fname.equals("") && !ln.equals("") && !dob.equals("") && !mob.equals("")){
                    UnderFiveCr ufc=new UnderFiveCr(fname,ln,mn,fn,ci,room,bldg,town,area,ac,mob,dob,nic,gen);
                    UnderFiveHc uhc=new UnderFiveHc(0,ci,"","","","","");
                    UnderFiveImm uim=new UnderFiveImm(ci,"","","","","","","","","","","",
                            "","","","","","","","","","","","","","","","","","","","","","","","","","","");
                    databaseChildHr.push().setValue(ufc);
                    databaseChildHcr.push().setValue(uhc);
                    databaseChildImm.push().setValue(uim);
                    Toast.makeText(UnderFiveAddCr.this,"Successfully added child record",Toast.LENGTH_SHORT).show();
                    AddRecord.setVisibility(View.INVISIBLE);
                }
                else{
                    Toast.makeText(UnderFiveAddCr.this,"Fill First name,last name,phone and DOB",Toast.LENGTH_SHORT).show();
                }
            }
        });

        Refresh.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Addn1.setText("");
                Addn2.setText("");
                Addn3.setText("");
                Addn4.setText("");
                Addn5.setText("");
                Addn6.setText("");
                Addn7.setText("");
                Addn8.setText("");
                Addn9.setText("");


                sGen.setSelection(0);
                sNic.setSelection(0);
                sTown.setSelection(0);
                sAc.setSelection(0);
                if(AddRecord.getVisibility()==View.INVISIBLE)
                    AddRecord.setVisibility(View.VISIBLE);
            }
        });

    }

    private void setDateTimeField() {
        Addn9.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

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
