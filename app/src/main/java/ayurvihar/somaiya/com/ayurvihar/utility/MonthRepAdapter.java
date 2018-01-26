package ayurvihar.somaiya.com.ayurvihar.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.ArrayList;

import ayurvihar.somaiya.com.ayurvihar.R;

/**
 * Created by killua on 26/1/18.
 */

public class MonthRepAdapter extends ArrayAdapter<UnderFiveImm> {

    ArrayList<UnderFiveImm> vaclist = new ArrayList<>();


    public MonthRepAdapter(Context ctx, int resourceId, ArrayList<UnderFiveImm> vacs){
        super(ctx,resourceId,vacs);
        vaclist=vacs;
    }


    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.monthvac, null);

        TextView vaccine = (TextView) convertView.findViewById(R.id.vaccine);
        vaccine.setText(vaclist.get(position).getName());

        TextView given = (TextView) convertView.findViewById(R.id.lc);
        given.setText(vaclist.get(position).getGiven());

        TextView due = (TextView) convertView.findViewById(R.id.mc);
        due.setText(vaclist.get(position).getDue());

        return convertView;

    }
}
