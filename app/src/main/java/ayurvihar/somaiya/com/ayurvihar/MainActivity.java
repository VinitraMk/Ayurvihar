package ayurvihar.somaiya.com.ayurvihar;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;


import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

import ayurvihar.somaiya.com.ayurvihar.underfive.*;
import ayurvihar.somaiya.com.ayurvihar.utility.*;


public class MainActivity extends AppCompatActivity {

    EditText Username,Password;
    Spinner module;
    Button Login;

    boolean logged=false,found=false;

    String user,guser,password,gpassword;

    public static SharedPreferences latestUpdate;
    public static SharedPreferences.Editor editor;
    private static final String PREF_NAME="latestUpgDate";
    public static final String KEY_TODAY_DATE_ANDTIME = "TimeToday";
    public static String Val,curDate;

    public static final DatabaseReference DATABASE_ROOT=FirebaseDatabase.getInstance().getReference();
    DatabaseReference databaseUsers;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        databaseUsers=DATABASE_ROOT.child("Users");
        module=(Spinner)findViewById(R.id.department_select);
        Username=(EditText)findViewById(R.id.Username);
        Password=(EditText)findViewById(R.id.Password);
        Login=(Button)findViewById(R.id.login);

        Username.setText("admin");
        Password.setText("admin");

        try {
            DateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
            Date date = new Date();
            curDate = dateFormat.format(date);
            latestUpdate = getSharedPreferences(PREF_NAME, 0);
            editor = latestUpdate.edit();

            Val=latestUpdate.getString(KEY_TODAY_DATE_ANDTIME,"");
            //Val="10-12-2017";
            Log.i("Stored Val", Val);
            Log.i("Today date", curDate);




        }
        catch (Exception e) {
            e.printStackTrace();
            Log.i("", "Exception here Run Service.");
        }



        Login.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                user=Username.getText().toString().trim();
                password=Password.getText().toString().trim();
                Log.v("users",""+user+" "+password);
                logged=false;
                found=false;
                databaseUsers.addValueEventListener(new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        for(DataSnapshot ds:dataSnapshot.getChildren()){
                            Log.v("userobj",""+ds.child("mUsername").getValue());
                            guser=ds.child("mUsername").getValue().toString();
                            gpassword=ds.child("mPassword").getValue().toString();
                            if(guser.equals(user)){
                                found=true;
                                if(gpassword.equals(password)) {
                                    logged = true;
                                    loginTask();
                                }
                            }
                        }
                        if(found==true && logged==false)
                            Toast.makeText(MainActivity.this,"Invalid password",Toast.LENGTH_SHORT).show();
                        else if(found==false){
                            addUser();
                        }
                    }
                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
            }
        });

    }

    private void addUser(){
        AdminUser newUser=new AdminUser(user,password);
        databaseUsers.push().setValue(newUser);
    }

    private void loginTask(){
        String dept=(String)module.getSelectedItem();
        switch(dept){
            case "Under Five":
                Log.v("curdate",Val+","+curDate);
                if(!Val.equals(curDate)) {
                    Log.v("Starting first time","Yes");
                    Intent i = new Intent(MainActivity.this,DailyUpdates.class);
                    startActivity(i);

                }
                else {
                    Intent i = new Intent(MainActivity.this, UnderFiveHome.class);
                    startActivity(i);
                }
                break;
        }
    }
}
