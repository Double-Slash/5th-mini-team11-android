package doubleslash05.mini.team11.ui.tutorial;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;
import androidx.viewpager.widget.ViewPager;

import android.os.Bundle;
import android.view.Window;

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
                    TutorialPage1 tutorialPage1 = new TutorialPage1();
                    return tutorialPage1;
                case 1:
                    TutorialPage2 tutorialPage2 = new TutorialPage2();
                    return tutorialPage2;
                case 2:
                    TutorialPage3 tutorialPage3 = new TutorialPage3();
                    return tutorialPage3;
                case 3:
                    TutorialPage4 tutorialPage4 = new TutorialPage4();
                    return tutorialPage4;
                case 4:
                    TutorialPage5 tutorialPage5 = new TutorialPage5();
                    return tutorialPage5;
                default:
                    return null;

            }
        }

        @Nullable
        @Override
        public CharSequence getPageTitle(int position) {
            return "Page" + position;

        }
    }





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_tutorial);
        ViewPager vpPager = (ViewPager)findViewById(R.id.vpPager);
        adapterViewPager = new TutorialPageAdapter(getSupportFragmentManager());
        vpPager.setAdapter(adapterViewPager);
        vpPager.setCurrentItem(0);


    }
}

