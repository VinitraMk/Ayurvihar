package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.widget.ListView;
import android.widget.TextView;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.MonthRepAdapter;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;
import ayurvihar.somaiya.com.ayurvihar.utility.VaccineAdapter;

public class MonthlyReport extends AppCompatActivity {

    String start="",end="";
    int mon,year;
    int sd,ed,sm,em,sy,ey;
    ListView childlist;
    ArrayList<UnderFiveImm> datalist = new ArrayList();
    TextView month;
    DateFormat dateFormat;
    DatabaseReference CHILD_DB= MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildImm;
    HashMap<String,Integer> hp,hp1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_monthly_report);

        databaseChildImm=CHILD_DB.child("ImmRec");

        childlist = (ListView) findViewById(R.id.childlist);
        month = (TextView) findViewById(R.id.month);

        dateFormat = new SimpleDateFormat("dd-MM-yyyy");
        Date date = new Date();
        String curr = dateFormat.format(date);
        mon = Integer.parseInt(curr.substring(3,5));
        year = Integer.parseInt(curr.substring(6,10));

        getDateRange(mon,year);
        month.setText(start+" to "+end);

        hp=new HashMap<>();
        hp1=new HashMap<>();

        getData();

    }

    public void getData() {

        databaseChildImm.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {

                if(datalist.size()!=0)
                    datalist.clear();
                UnderFiveImm ufi = new UnderFiveImm();
                hp = ufi.init();
                hp1 = ufi.init();

                for(DataSnapshot ds:dataSnapshot.getChildren()) {
                    //UnderFiveImm ufi = ds.getValue(UnderFiveImm.class);
                    int ltm=0,ltf=0,mtm=0,mtf=0;
                    Date date1 = new Date(sy,sm-1,sd);
                    Date date2 = new Date(ey,em-1,ed);
                    Log.v("date",""+date1+","+date2);
                    int dd,dm,dy;
                    String childdob = ds.child("dob").getValue().toString();
                    Log.v("date",childdob);
                    String track = ds.child("track").getValue().toString().trim();
                    String gen = ds.child("gen").getValue().toString().trim();
                    dd=Integer.parseInt(childdob.substring(0,2));
                    dm=Integer.parseInt(childdob.substring(3,5));
                    dy=Integer.parseInt(childdob.substring(6,10));

                    for(DataSnapshot ds1:ds.getChildren()) {
                        int tm,ty,td;
                        String temp = ""+ds1.getValue();
                        String key = ""+ds1.getKey();
                        Log.v("date",key+","+temp);

                        if(!temp.equals("") && key.charAt(0)=='g' && !key.equals("gen")) {
                            temp = ds1.getValue(String.class);
                            String vname = ds1.getKey();
                            vname=vname.substring(1,vname.length());
                            Log.v("date1",temp+","+vname);
                            td=Integer.parseInt(temp.substring(0,2));
                            tm=Integer.parseInt(temp.substring(3,5));
                            ty=Integer.parseInt(temp.substring(6,10));
                            Date dated = new Date(ty,tm-1,td);
                            Log.v("date1",""+dated);
                            Log.v("date1",date1+","+date2);

                            if(dated.after(date1) && dated.before(date2) && track.equals("Tracking Somaiya")) {
                                int age = getAge(dy,dm-1,dd);
                                Log.v("age",""+age+","+dated);
                                if(age<1) {
                                    if (gen.equals("Male")) {
                                        //ltm++;
                                        String tk = "m"+vname;
                                        Log.v("age",""+hp.get(tk));
                                        hp.put(tk,hp.get(tk)+1);
                                        Log.v("age",""+hp.get(tk));
                                    }
                                    else {
                                        //ltf++;
                                        String tk = "f"+vname;
                                        Log.v("age",""+hp.get(tk));
                                        hp.put(tk,hp.get(tk)+1);
                                        Log.v("age",""+hp.get(tk));
                                    }
                                }
                                else {
                                    if (gen.equals("Male")) {
                                        //mtm++;
                                        String tk = "m"+vname;
                                        Log.v("age",""+hp1.get(tk));
                                        hp1.put(tk,hp1.get(tk)+1);
                                        Log.v("age",""+hp1.get(tk));
                                    }
                                    else {
                                        //mtf++;
                                        String tk = "f"+vname;
                                        Log.v("age",""+hp1.get(tk));
                                        hp1.put(tk,hp1.get(tk)+1);
                                        Log.v("age",""+hp1.get(tk));
                                    }
                                }
                                //datalist.add(new UnderFiveImm(vname,"M: "+ltm+"\nF: "+ltf,"M: "+mtm+"\nF: "+mtf));
                            }
                        }
                    }
                }
                ArrayList<String> keyarl = new ArrayList<>();
                keyarl.addAll(hp.keySet());
                Collections.sort(keyarl);
                for(String s:keyarl) {
                    String st = s.substring(1,s.length());
                    Log.v("vac",st);
                    if(s.charAt(0)=='m') {
                        datalist.add(new UnderFiveImm(st,"M: "+hp.get(s)+"\nF: "+hp.get("f"+st)+"\nT: "+(hp.get(s)+hp.get("f"+st)),"M: "+hp1.get(s)+"\nF: "+hp1.get("f"+st)+"\nT: "+(hp1.get(s)+hp1.get("f"+st))));
                    }
                }
                //vaccineAdapter = new VaccineAdapter(getContext(), R.layout.imm_item, vaclist);
                childlist.setAdapter(new MonthRepAdapter(getApplicationContext(),R.layout.monthvac,datalist));

            }

            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });
    }

    public void getDateRange(int mon,int year) {

        start="26-";
        end="25-";

        sd=25;
        ed=26;

        em=mon;
        if(mon==1)
            sm=12;
        else
            sm=mon-1;
        if(sm<10)
            start+="0"+sm+"-";
        else
            start+=sm+"-";

        if(em<10)
            end+="0"+(em)+"-";
        else
            start+=(em)+"-";



        end+=year;
        ey=year;
        if(mon==1)
            year--;
        start+=year;
        sy=year;

        Log.v("month",start+","+end);

    }

    public static boolean isInteger(String s) {
        try {
            Integer.parseInt(s);
        } catch(NumberFormatException e) {
            return false;
        } catch(NullPointerException e) {
            return false;
        }
        // only got here if we didn't return false
        return true;
    }

    private int getAge(int year, int month, int day){
        Calendar dob = Calendar.getInstance();
        Calendar today = Calendar.getInstance();

        dob.set(year, month, day);

        int age = today.get(Calendar.YEAR) - dob.get(Calendar.YEAR);

        if (today.get(Calendar.DAY_OF_YEAR) < dob.get(Calendar.DAY_OF_YEAR)){
            age--;
        }

        Integer ageInt = new Integer(age);
        String ageS = ageInt.toString();

        //return ageS;
        return age;
    }
}
