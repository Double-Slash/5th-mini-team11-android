package doubleslash05.mini.team11.ui.tutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.os.Handler;

import com.google.android.material.tabs.TabLayout;

import doubleslash05.mini.team11.R;



public class TutorialActivity extends AppCompatActivity {



    FragmentPagerAdapter adapterViewPager;


    public static class TutorialPageAdapter extends FragmentPagerAdapter {
        private static int PAGE_NUN = 5;



        @Override
        public int getItemPosition(@NonNull Object object) {
            return super.getItemPosition(object);
        }




        public TutorialPageAdapter(FragmentManager fragmentManager) {



            super(fragmentManager);


        }

        @Override
        public int getCount() {
            return PAGE_NUN;
        }

        @NonNull
        @Override
        public Fragment getItem(int position) {
            switch (position) {
                case 0:
                    TutorialFragment1 tutorialFragment1 = new TutorialFragment1();
                    return tutorialFragment1;
                case 1:
                    TutorialFragment2 tutorialFragment2 = new TutorialFragment2();
                    return tutorialFragment2;
                case 2:
                    TutorialFragment3 tutorialFragment3 = new TutorialFragment3();
                    return tutorialFragment3;
                case 3:
                    TutorialFragment4 tutorialFragment4 = new TutorialFragment4();
                    return tutorialFragment4;
                case 4:
                    TutorialFragment5 tutorialFragment5 = new TutorialFragment5();
                    return tutorialFragment5;
                default:
                    return null;

            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return null;

        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        final ViewPager vpPager = (ViewPager)findViewById(R.id.vpPager);
        TabLayout customTab = (TabLayout)findViewById(R.id.customTab);

        customTab.setupWithViewPager(vpPager, true);

        adapterViewPager = new TutorialPageAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(0);


        new Handler().postDelayed(new Runnable()
        {
            @Override
            public void run()
            {

                vpPager.setCurrentItem(1);
            }
        },  5000);





    }





}

