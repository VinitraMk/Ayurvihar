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
 * Created by mikasa on 24/12/17.
 */

public class VaccineDetailAdapter extends ArrayAdapter<UnderFiveIntervals> {

    ArrayList<UnderFiveIntervals> vacs = new ArrayList<>();

    public VaccineDetailAdapter(Context ctx, int resourceId, ArrayList<UnderFiveIntervals> vacs){
        super(ctx,resourceId,vacs);
        this.vacs=vacs;
    }

    @Override
    public int getCount() {
        return super.getCount();
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {

        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        convertView = inflater.inflate(R.layout.immint_item, null);

        TextView vaccine = (TextView) convertView.findViewById(R.id.vaccine);
        vaccine.setText(vacs.get(position).getVacname());

        TextView given = (TextView) convertView.findViewById(R.id.value);
        String tval = ""+(vacs.get(position).getVal());
        given.setText(tval);

        return convertView;

    }
}
