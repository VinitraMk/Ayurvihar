package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.content.DialogInterface;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
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
import java.util.Locale;

import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by mikasa on 6/7/17.
 */


public class UnderFiveAddCr extends AppCompatActivity implements View.OnClickListener {

    EditText Addn1 , Addn2 , Addn3 , Addn4 , Addn5 , Addn6 , Addn7 , Addn8 , Addn9, Addn10, Addn11;
    String fname,ln,mn,fn,ci,fi,room,bldg,town,area,ac,mob,dob,nic,gen;
    Spinner sGen,sNic,sTown,sAc;
    Button AddRecord,Refresh;
    SimpleDateFormat dateFormatter;
    private DatePickerDialog datePickerDialog;
    public static final DatabaseReference CHILD_DB=MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildHr,databaseChildHcr,databaseChildImm;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_addcr);

        databaseChildHr=CHILD_DB.child("Childhr");
        databaseChildHcr=CHILD_DB.child("Childhcr");
        databaseChildImm=CHILD_DB.child("Childimm");

        Addn1 = (EditText) findViewById(R.id.Addn1);
        Addn2 = (EditText) findViewById(R.id.Addn2);
        Addn3 = (EditText) findViewById(R.id.Addn3);
        Addn4 = (EditText) findViewById(R.id.Addn4);
        Addn5 = (EditText) findViewById(R.id.Addn5);
        Addn6 = (EditText) findViewById(R.id.Addn6);
        Addn7 = (EditText) findViewById(R.id.Addn7);
        Addn8 = (EditText) findViewById(R.id.Addn8);
        Addn9 = (EditText) findViewById(R.id.Addn9);
        Addn10 = (EditText) findViewById(R.id.Addn10);
        Addn11 = (EditText) findViewById(R.id.Addn11);
        Addn11.setInputType(InputType.TYPE_NULL);
        Addn11.requestFocus();


        AddRecord = (Button) findViewById(R.id.AddRecord);
        Refresh = (Button) findViewById(R.id.Refresh);
        sGen = (Spinner) findViewById(R.id.gender);
        sNic = (Spinner) findViewById(R.id.nic);
        sTown = (Spinner) findViewById(R.id.towns);
        sAc = (Spinner) findViewById(R.id.areacode);

        //Execurting spinner list for selecting date
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();


        //Database handling of Under5 begins

        AddRecord.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                fname=Addn1.getText().toString().trim();
                ln=Addn2.getText().toString().trim();
                mn=Addn3.getText().toString().trim();
                fn=Addn4.getText().toString().trim();
                ci=Addn5.getText().toString().trim();
                fi=Addn6.getText().toString().trim();
                room=Addn7.getText().toString().trim();
                bldg=Addn8.getText().toString().trim();
                town=sTown.getSelectedItem().toString();
                area=Addn9.getText().toString().trim();
                ac=sAc.getSelectedItem().toString();
                mob=Addn10.getText().toString().trim();
                dob= Addn11.getText().toString().trim();
                gen=sGen.getSelectedItem().toString();
                nic=sNic.getSelectedItem().toString();
                if(!fname.equals("") && !ln.equals("") && !dob.equals("") && !mob.equals("")){
                    UnderFiveCr ufc=new UnderFiveCr(fname,ln,mn,fn,ci,fi,room,bldg,town,area,ac,mob,dob,nic,gen);
                    UnderFiveHc uhc=new UnderFiveHc(0,fname,ln,dob,"","","","","");
                    UnderFiveImm uim=new UnderFiveImm(fname,ln,dob,mob,"","","","","","","","","","","",
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
                Addn10.setText("");
                Addn11.setText("");

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
        Addn11.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Addn11.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == Addn11) {
            datePickerDialog.show();
        }
    }
}
