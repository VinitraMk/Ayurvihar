package ayurvihar.somaiya.com.ayurvihar.underfive;

import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.util.Log;

import java.util.ArrayList;
import java.util.List;

import ayurvihar.somaiya.com.ayurvihar.Fragment.CTab1S;
import ayurvihar.somaiya.com.ayurvihar.Fragment.CTab2S;
import ayurvihar.somaiya.com.ayurvihar.R;


/**
 * Created by heretic on 10/8/17.
 */

public class UnderFiveSearch1CrOutput extends FragmentActivity {
    private TabLayout tabLayout;
    private  ViewPager viewPager;
    public UnderFiveSearch1CrOutput SV = UnderFiveSearch1CrOutput.this;
    public static ProgressDialog dialog;
    public  Context ctx;


    public static String date ="";


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_search1cr_output);

        ctx = getApplicationContext();
        dialog = new ProgressDialog(UnderFiveSearch1CrOutput.this);
        viewPager = (ViewPager)findViewById(R.id.cviewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.ctabs);
        tabLayout.setupWithViewPager(viewPager);
        date=getIntent().getExtras().getString("date");
        Log.v("scroll",date);
    }

    private void setupViewPager(ViewPager viewPager) {
        UnderfiveScrollview.ViewPagerAdapter adapter = new UnderfiveScrollview.ViewPagerAdapter(getSupportFragmentManager());
        adapter.addFrag(new CTab1S(), "          Child Records          ");
        adapter.addFrag(new CTab2S(), "       Vaccine Requirement       ");

        viewPager.setAdapter(adapter);

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter {
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
        Intent i=new Intent(UnderFiveSearch1CrOutput.this,UnderFiveSearch1Cr.class);
        i.putExtra("date",date);
        startActivity(i);
    }

}
