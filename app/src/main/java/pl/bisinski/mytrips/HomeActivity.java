package pl.bisinski.mytrips;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;

import com.pixelcan.inkpageindicator.InkPageIndicator;

public class HomeActivity extends AppCompatActivity {

    FragmentPagerAdapter adapterViewPager;
    ViewPager mPager;
    InkPageIndicator mIndicator;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_home);

        adapterViewPager = new MyPagerAdapter(getSupportFragmentManager());

        mPager = findViewById(R.id.vpPager);
        mPager.setAdapter(adapterViewPager);

        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mPager);
    }

    public static class MyPagerAdapter extends FragmentPagerAdapter {
        private static int NUM_ITEMS = 2;

        public MyPagerAdapter(FragmentManager fragmentManager) {
            super(fragmentManager);
        }

        // Returns total number of pages
        @Override
        public int getCount() {
            return NUM_ITEMS;
        }

        // Returns the fragment to display for that page
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    return CategoryFragment.newInstance();
                case 1:
                    return TripFragment.newInstance();
                default:
                    return null;
            }
        }

        // Returns the page title for the top indicator
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page " + position;
        }

    }
}