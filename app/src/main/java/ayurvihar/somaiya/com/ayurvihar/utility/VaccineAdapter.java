package ayurvihar.somaiya.com.ayurvihar.utility;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import java.util.ArrayList;

import ayurvihar.somaiya.com.ayurvihar.R;

/**
 * Created by mikasa on 30/7/17.
 */

public class VaccineAdapter extends ArrayAdapter<UnderFiveImm> {

    ArrayList<UnderFiveImm> vaclist = new ArrayList<>();

    public VaccineAdapter(Context ctx, int resourceId, ArrayList<UnderFiveImm> vacs){
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
        convertView = inflater.inflate(R.layout.imm_item, null);

        TextView vaccine = (TextView) convertView.findViewById(R.id.vaccine);
        vaccine.setText(vaclist.get(position).getName());

        TextView given = (TextView) convertView.findViewById(R.id.given);
        given.setText(vaclist.get(position).getGiven());

        TextView due = (TextView) convertView.findViewById(R.id.due);
        due.setText(vaclist.get(position).getDue());

        return convertView;

    }
}
