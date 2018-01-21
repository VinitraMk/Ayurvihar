package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.DatePickerDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.text.InputType;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DatabaseReference;

import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Locale;
import java.util.TimeZone;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;

/**
 * Created by mikasa on 31/7/17.
 */

public class UnderFiveHealthRep extends AppCompatActivity implements View.OnClickListener {

    EditText Addn1,Addn2,Addn3,Addn4;
    Button addrep;
    TextView childidn,chkno;
    String childid,dob,cur,ratio="";
    SimpleDateFormat dateFormatter,ts;
    private DatePickerDialog datePickerDialog;
    int chkn,age,weight;
    DatabaseReference databaseHcr = MainActivity.DATABASE_ROOT.child("Underfive").child("ChkRec");

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_hlthrep);

        Addn1 = (EditText) findViewById(R.id.chkdate);
        Addn2 = (EditText) findViewById(R.id.height);
        Addn3 = (EditText) findViewById(R.id.weight);
        Addn4 = (EditText) findViewById(R.id.remark);
        addrep = (Button) findViewById(R.id.addrep);
        childidn = (TextView) findViewById(R.id.childidn);
        chkno = (TextView) findViewById(R.id.chkno);


        chkn = getIntent().getExtras().getInt("Nocu");
        dob = getIntent().getExtras().getString("Dob");

        //set Age of child
        dob = (dob.substring(6,10)+dob.substring(3,5)+dob.substring(0,2));
        Date date = Calendar.getInstance().getTime();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyyMMdd");
        sdf.setTimeZone(TimeZone.getTimeZone("GMT"));
        cur = sdf.format(date);
        Log.v("hello",cur);
        age = (Integer.parseInt(cur)-Integer.parseInt(dob)/10000);


        childid = getIntent().getExtras().getString("Childid");
        childid=childid.trim();
        childidn.setText(childid);
        chkno.setText(""+chkn);

        Addn1.setInputType(InputType.TYPE_NULL);
        Addn1.requestFocus();
        dateFormatter = new SimpleDateFormat("dd-MM-yyyy", Locale.US);
        setDateTimeField();

        addrep.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String td = Addn1.getText().toString().trim();
                String the = Addn2.getText().toString().trim();
                String twe = Addn3.getText().toString().trim();
                String trem = Addn4.getText().toString().trim();

                if(!td.equals("") && !the.equals("") && !twe.equals(""))
                {
                    weight = Integer.parseInt(Addn3.getText().toString().trim());
                    Double dt = ((double)weight/age);
                    ratio += (new DecimalFormat("#0.000000000").format(dt));
                    UnderFiveHc uhc = new UnderFiveHc(chkn,childid,td,the,twe,ratio,trem);
                    databaseHcr.child(childid).push().setValue(uhc);
                    finish();
                }
                else
                    Toast.makeText(UnderFiveHealthRep.this,"Fill Date,Weight and Height fields",Toast.LENGTH_SHORT).show();
            }
        });



    }

    private void setDateTimeField() {
        Addn1.setOnClickListener(this);

        Calendar newCalendar = Calendar.getInstance();
        datePickerDialog = new DatePickerDialog(this, new DatePickerDialog.OnDateSetListener() {

            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                Calendar newDate = Calendar.getInstance();
                newDate.set(year, monthOfYear, dayOfMonth);
                Addn1.setText(dateFormatter.format(newDate.getTime()));
            }

        }, newCalendar.get(Calendar.YEAR), newCalendar.get(Calendar.MONTH), newCalendar.get(Calendar.DAY_OF_MONTH));

    }

    @Override
    public void onClick(View view) {
        if(view == Addn1) {
            datePickerDialog.show();
        }
    }

    @Override
    public void onBackPressed() {
        finish();
    }
}
