package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ayurvihar.somaiya.com.ayurvihar.Fragment.ITab1;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

public class UnderFiveImmOutput extends FragmentActivity {

    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static ProgressDialog dialog;
    public static Context ctx;

    public static UnderFiveCr ufc;
    public static UnderFiveHc uhc;
    public static UnderFiveImm uim;

    public static String cid;
    String date = "";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_under_five_imm_output);

        ctx = getApplicationContext();
        dialog = new ProgressDialog(getApplicationContext());
        viewPager = (ViewPager)findViewById(R.id.cviewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.ctabs);
        tabLayout.setupWithViewPager(viewPager);
        date=getIntent().getExtras().getString("Dob");
        cid=getIntent().getExtras().getString("childid");
        Log.v("scroll",date);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager());
        adapter.addFrag(new ITab1(), "          Child Records          ");
        adapter.addFrag(new ITab1(), "       Vaccine Requirement       ");

        viewPager.setAdapter(adapter);

    }

    class ViewPagerAdapter extends FragmentPagerAdapter {
        private final List<Fragment> mFragmentList = new ArrayList<>();
        private final List<String> mFragmentTitleList = new ArrayList<>();
        public ViewPagerAdapter(FragmentManager manager) {
            super(manager);
        }

        @Override
        public Fragment getItem(int position) {
            return mFragmentList.get(position);
        }

        @Override
        public int getCount() {
            return mFragmentList.size();
        }

        public void addFrag(Fragment fragment, String title) {
            mFragmentList.add(fragment);
            mFragmentTitleList.add(title);
        }

        @Override
        public CharSequence getPageTitle(int position) {
            return mFragmentTitleList.get(position);
        }
    }

    public void onBackPressed(){
        //Under5_Health_Record_Core.hr.clear();
        if(getFragmentManager().findFragmentById(R.id.ctabs)!=null)
        {
            getFragmentManager().beginTransaction().remove(getFragmentManager().findFragmentById(R.id.ctabs)).commit();
        }
        finish();

        Intent i=new Intent(UnderFiveImmOutput.this,UnderFiveImmSearch.class);
        i.putExtra("date",date);
        startActivity(i);
    }
}
