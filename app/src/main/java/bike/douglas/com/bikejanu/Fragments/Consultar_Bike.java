package bike.douglas.com.bikejanu.Fragments;


import android.os.Bundle;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import bike.douglas.com.bikejanu.R;

public class Consultar_Bike extends AppCompatActivity {



        private SectionsPagerAdapter mSectionsPagerAdapter;


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
                        Tab2Marca tab2 = new Tab2Marca();
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
