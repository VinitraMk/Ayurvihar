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

import ayurvihar.somaiya.com.ayurvihar.Fragment.BackHandledFragment;
import ayurvihar.somaiya.com.ayurvihar.Fragment.CTab1;
import ayurvihar.somaiya.com.ayurvihar.Fragment.CTab2;
import ayurvihar.somaiya.com.ayurvihar.Fragment.CTab3;
import ayurvihar.somaiya.com.ayurvihar.R;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveCr;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveHc;
import ayurvihar.somaiya.com.ayurvihar.utility.UnderFiveImm;

/**
 * Created by mikasa on 30/7/17.
 */

public class UnderfiveScrollview extends FragmentActivity implements BackHandledFragment.BackHandlerInterface {
    private BackHandledFragment selectedFragment;


    private TabLayout tabLayout;
    private ViewPager viewPager;
    public static UnderfiveScrollview SV;
    public static ProgressDialog dialog;
    public static Context ctx;

    public static UnderFiveCr ufc;
    public static UnderFiveHc uhc;
    public static UnderFiveImm uim;

    public static String cid,dob;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.underfive_scrollview);

        ctx = getApplicationContext();
        SV=UnderfiveScrollview.this;
        dialog = new ProgressDialog(UnderfiveScrollview.this);
        viewPager = (ViewPager)findViewById(R.id.cviewpager);
        setupViewPager(viewPager);
        tabLayout = (TabLayout)findViewById(R.id.ctabs);
        tabLayout.setupWithViewPager(viewPager);
        cid=getIntent().getExtras().getString("childid");
        dob=getIntent().getExtras().getString("Dob");
        Log.v("scroll",cid);
    }

    private void setupViewPager(ViewPager viewPager) {
        ViewPagerAdapter adapter = new ViewPagerAdapter( getSupportFragmentManager());
        adapter.addFrag(new CTab1(), "          Child Data          ");
        adapter.addFrag(new CTab2(), "          Immunization          ");
        adapter.addFrag(new CTab3(), "          Health Checkup          ");

        viewPager.setAdapter(adapter);

    }

    static class ViewPagerAdapter extends FragmentPagerAdapter{
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

    @Override
    public void onBackPressed() {
        if(selectedFragment == null || !selectedFragment.onBackPressed()) {
            // Selected fragment did not consume the back press event.
            super.onBackPressed();
            Intent i = new Intent(UnderfiveScrollview.this,UnderFiveUpdateCr.class);
            startActivity(i);
        }
    }

    @Override
    public void setSelectedFragment(BackHandledFragment selectedFragment) {
        this.selectedFragment = selectedFragment;
    }


}