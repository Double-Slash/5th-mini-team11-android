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
                    return TutorialPage1.getInstance("tutorial1", 1);
                case 1:
                    return TutorialPage2.getInstance("tutorial2", 2);
                case 2:
                    return TutorialPage3.getInstance("tutorial3", 3);
                case 3:
                    return TutorialPage4.getInstance("tutorial4", 4);
                case 4:
                    return TutorialPage5.getInstance("tutorial5", 5);
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

