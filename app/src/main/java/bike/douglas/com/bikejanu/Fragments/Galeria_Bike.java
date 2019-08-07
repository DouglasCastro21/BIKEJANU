package bike.douglas.com.bikejanu.Fragments;

import android.content.Intent;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.tabs.TabLayout;

import bike.douglas.com.bikejanu.R;


public class Galeria_Bike extends AppCompatActivity {


     String  Numero_serie ;
     static String  Numero_serie_serie = null;




        private SectionsPagerAdapter mSectionsPagerAdapter;





        private ViewPager mViewPager;
        ImageView imageView1;

        @Override
        protected void onCreate(Bundle savedInstanceState) {
            super.onCreate(savedInstanceState);
            setContentView(R.layout.activity_galeria__bike);

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





            Intent intent = getIntent();


            if(intent !=null) {

                Bundle params = intent.getExtras();

                if (params != null) {

                    //dados do modelo
                    Numero_serie = params.getString("Numero_serie");


                }

            }


            Numero_serie_serie = Numero_serie;



        }















        public class SectionsPagerAdapter extends FragmentPagerAdapter {

            public SectionsPagerAdapter(FragmentManager fm) {
                super(fm);
            }

            @Override
            public Fragment getItem(int position) {

                switch (position){

                    case 0:

                        Imagem1 tab1C = new Imagem1();
                        return tab1C;
                    case 1:
                        Imagem2 tab2 = new Imagem2();
                        return tab2;
                    case 2:
                        Imagem3 tab3 = new Imagem3();
                        return tab3;

                    case 3:
                        Imagem4 tab4 = new Imagem4();
                        return tab4;

                    case 4:
                        Imagem5 tab5 = new Imagem5();
                        return tab5;
                    default:
                        return null;
                }
            }

            @Override
            public int getCount() {
                // Show 3 total pages.
                return 5;
            }

            @Nullable
            @Override
            public CharSequence getPageTitle(int position) {

                switch (position){

                    case 0:
                        return "Imagem - 1";
                    case 1:
                        return "Imagem - 2";
                    case 2:
                        return "Imagem - 3";

                    case 3:
                        return "Imagem - 4";

                    case 4:
                        return "Imagem - 5";
                }
                return null;
            }
        }
    }
