package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveIntervals;

/**
 * Created by mikasa on 27/11/17.
 */

public class DailyUpdates extends AppCompatActivity {

    ProgressDialog dialog;
    public static final DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    public static final DatabaseReference databaseChildHr=CHILD_DB.child("GenRec");
    public static final DatabaseReference databaseChildHcr=CHILD_DB.child("ChkRec");
    public static final DatabaseReference databaseChildImm=CHILD_DB.child("ImmRec");
    public static final DatabaseReference databaseIntervals=CHILD_DB.child("Intervals");

    UnderFiveImm ufi;
    UnderFiveIntervals ufit;
    final Date date = new Date();

    Map<String,Object> immlist,timmlist;
    Map<String,Long> intlist;

    DateFormat df = new SimpleDateFormat("dd-MM-yyyy");


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.dailyupdates);

        DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        //final Date date = new Date();
        dialog = new ProgressDialog(DailyUpdates.this);
        final String curDate = dateFormat.format(date);
        dialog.setMessage("Updating the database....");
        dialog.show();
        //access1();
        access2();





    }

    public void access2() {
        CHILD_DB.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                /*GenericTypeIndicator<HashMap<String, Object>> objectsGTypeInd = new GenericTypeIndicator<HashMap<String, Object>>() {};
                Map<String, Object> objectHashMap = dataSnapshot.getValue(objectsGTypeInd);
                ArrayList<Object> objectArrayList = new ArrayList<Object>(objectHashMap.values());*/
                DataSnapshot dsimm = dataSnapshot.child("ImmRec");
                DataSnapshot dsint = dataSnapshot.child("Intervals");

                immlist = (Map<String,Object>)dsimm.getValue();
                intlist = (Map<String,Long>)dsint.getValue();
                timmlist = immlist;
                Date valueDate = new Date();
                Date afterUpdate = new Date();
                String newDateString = "";

                Log.v("mainlist",""+immlist);


                //Log.v("int",""+intlist.get("dobv2"));

                for(Map.Entry<String,Object> entry:immlist.entrySet()) {
                    String mkey = entry.getKey();
                    Map singleValue = (Map)entry.getValue();
                    Map newSingleValue = singleValue;
                    Log.v("immlist",""+singleValue);
                    Iterator it = singleValue.entrySet().iterator();
                    Map.Entry<String,String> pair,tpair;
                    while(it.hasNext()) {
                        pair = (Map.Entry)it.next();
                        tpair = pair;
                        String key = pair.getKey();
                        String value = pair.getValue();
                        try {
                            valueDate = df.parse(value);
                            //newDateString = df.format(newDate);
                            if(key.charAt(0)=='d' && valueDate.before(date)) {
                                afterUpdate = valueDate;
                                Calendar ct = Calendar.getInstance();
                                ct.setTime(afterUpdate);
                                Long amount = intlist.get(key.substring(1,key.length()));
                                //Log.v("intval",""+amount.intValue());
                                ct.add(Calendar.DATE,amount.intValue());
                                afterUpdate=ct.getTime();
                                Log.v("uptime",": "+valueDate+","+afterUpdate);
                                newDateString = df.format(afterUpdate);
                                tpair.setValue(newDateString);
                                Log.v("pair",""+tpair);
                                newSingleValue.put(key,newDateString);
                            }

                        }
                        catch (ParseException e){
                            e.printStackTrace();
                        }
                        //singleValue.put(mkey,tpair);


                    }
                    Log.v("immlistupdate",""+newSingleValue);
                    timmlist.put(entry.getKey(),newSingleValue);

                }
                Log.v("mainlistup",""+timmlist);
                Log.v("immref",""+dataSnapshot.child("ImmRec").getRef().setValue(timmlist));
                // Storing name in pref
                MainActivity.editor.putString(MainActivity.KEY_TODAY_DATE_ANDTIME,MainActivity.curDate);
                // commit changes
                MainActivity.editor.commit();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
        dialog.dismiss();
        Intent i = new Intent(DailyUpdates.this,UnderFiveHome.class);
        startActivity(i);
    }

    public void access1() {
    databaseChildImm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    ufi = ds.getValue(UnderFiveImm.class);


                    HashMap<String,String> hp = ufi.getList(ufi);
                    Log.v("ufi",ufi.childid);
                    Iterator it = hp.entrySet().iterator();
                    Date startDate = new Date();



                    while(it.hasNext()) {
                        Map.Entry pair = (Map.Entry)it.next();
                        String key = ""+pair.getKey();
                        String value = ""+pair.getValue();
                        String newDateString="";

                        try {
                            startDate = df.parse(value);
                            newDateString = df.format(startDate);
                            //System.out.println(newDateString);
                            //Log.v("immdate",""+ufi.childid+" "+newDateString);


                        } catch (ParseException e) {
                            e.printStackTrace();
                        }
                        if(key.charAt(0)=='d' && startDate.before(date)) {
                            Log.v("charat", "yes: " + key);
                            Calendar ct = Calendar.getInstance();
                            ct.setTime(startDate);
                            ct.add(Calendar.DATE,7);
                            startDate=ct.getTime();
                            newDateString=df.format(startDate);
                            //Log.v("updtime",""+newDateString);

                        }
                    }
                }

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });


    }
}
