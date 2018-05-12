package bike.douglas.com.bikejanu.Fragments;

import android.support.annotation.Nullable;
import android.support.design.widget.TabLayout;
import android.support.v7.app.AppCompatActivity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;

import bike.douglas.com.bikejanu.R;

public class Consultar_Bike extends AppCompatActivity {

        /**
         * The {@link android.support.v4.view.PagerAdapter} that will provide
         * fragments for each of the sections. We use a
         * {@link FragmentPagerAdapter} derivative, which will keep every
         * loaded fragment in memory. If this becomes too memory intensive, it
         * may be best to switch to a
         * {@link android.support.v4.app.FragmentStatePagerAdapter}.
         */
        private SectionsPagerAdapter mSectionsPagerAdapter;

        /**
         * The {@link ViewPager} that will host the section contents.
         */
        private ViewPager mViewPager;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_consultar__bike);

            //  Toolbar toolbar = (Toolbar) findViewById(R.id.tabsID);
            //   setSupportActionBar(toolbar);
            // Create the adapter that will return a fragment for each of the three
            // primary sections of the activity.
            mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

            // Set up the ViewPager with the sections adapter.
            mViewPager = (ViewPager) findViewById(R.id.container);
            mViewPager.setAdapter(mSectionsPagerAdapter);

            TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);

            mViewPager.addOnPageChangeListener(new TabLayout.TabLayoutOnPageChangeListener(tabLayout));
            tabLayout.addOnTabSelectedListener(new TabLayout.ViewPagerOnTabSelectedListener(mViewPager));



        }





        /**
         * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
         * one of the sections/tabs/pages.
         */
        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {

                switch (position){

                    case 0:
                        Tab1Codigo tab1C = new Tab1Codigo();
                        return tab1C;
                    case 1:
                        Tab2Data tab2 = new Tab2Data();
                        return tab2;
                    case 2:
                        Tab3Cor tab3 = new Tab3Cor();
                        return tab3;
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return 3;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                switch (position){

                    case 0:
                        return "Consultar pelo Codígo";
                    case 1:
                        return "Data";
                    case 2:
                        return "Cor";


                }
                return null;
            }
        }
    }
