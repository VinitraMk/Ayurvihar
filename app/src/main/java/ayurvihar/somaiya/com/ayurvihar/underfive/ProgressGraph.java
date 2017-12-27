package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.util.Pair;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebSettings;
import android.webkit.WebView;
import android.widget.PopupWindow;

import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;
import com.jjoe64.graphview.GraphView;
import com.jjoe64.graphview.series.DataPoint;
import com.jjoe64.graphview.series.LineGraphSeries;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;
import java.util.ListIterator;
import java.util.Map;

import ayurvihar.somaiya.com.ayurvihar.Fragment.CTab3;
import ayurvihar.somaiya.com.ayurvihar.MainActivity;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;

import static ayurvihar.somaiya.com.ayurvihar.R.id.graph;

/**
 * Created by mikasa on 31/7/17.
 */

public class ProgressGraph extends AppCompatActivity {

    PopupWindow pw;
    ProgressDialog dialog;
    DatabaseReference databaseChildHc= MainActivity.DATABASE_ROOT.child("Underfive").child("ChkRec");
    String childid;
    SharedPreferences weightForAge = CTab3.weightForAge;
    SharedPreferences.Editor editor;
    String graphList="";
    ArrayList<LineDataSet> lineDataSets = new ArrayList<>();
    ArrayList<String> xaxis = new ArrayList<>();


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.progress_graph);


        childid=getIntent().getExtras().getString("Childid");

        LineChart lineChart = (LineChart)findViewById(R.id.chart);


        graphList = weightForAge.getString("Weightforagelist","");
        Log.v("progress","hello: "+graphList);
        String[] arr = graphList.split(",");
        lineDataSets = getDataSet(arr);
        xaxis = getXAxisValues(arr);

        LineData lineData = new LineData(xaxis,lineDataSets);
        lineChart.setData(lineData);



    }

    private ArrayList<String> getXAxisValues(String arr[]) {
        ArrayList<String> list = new ArrayList<>();
        int l = arr.length;
        int c=0;
        while(c<l) {
            list.add("Check up: "+c);
            c++;
        }

        return list;

    }

    private ArrayList<LineDataSet> getDataSet(String arr[]) {
        ArrayList<LineDataSet> dataset = new ArrayList<>();

        ArrayList<Entry> entries = new ArrayList<>();
        int c=0;
        for(String s:arr) {

            if(!s.equals("")) {

                float temp = Float.parseFloat(s);
                Log.v("float", "" + temp);
                entries.add(new Entry(temp, c));
                c++;
            }
        }
        LineDataSet lineDataSet = new LineDataSet(entries,childid);
        dataset.add(lineDataSet);
        return dataset;
    }
}
