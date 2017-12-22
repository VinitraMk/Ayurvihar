package ayurvihar.somaiya.com.ayurvihar.Fragment;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.util.Pair;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ListView;

import com.google.firebase.database.DatabaseReference;

import java.util.ArrayList;

import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;


public class ITab1 extends Fragment {
    DatabaseReference CHILD_DB = MainActivity.DATABASE_ROOT.child("Underfive");
    DatabaseReference databaseChildImm;
    ListView simpleList;
    String date = "";
    ArrayList<Pair<String, String>> childlist;

    UnderFiveImm ufi;

    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        databaseChildImm = CHILD_DB.child("ImmRec");
        View view = inflater.inflate(R.layout.fragment_itab1, container, false);
        return view;


    }
}
