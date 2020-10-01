package doubleslash05.mini.team11.ui.sj;

import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentPagerAdapter;

import doubleslash05.mini.team11.model.data.Fragment_1;
import doubleslash05.mini.team11.model.data.Fragment_2;

public class PageAdapter extends FragmentPagerAdapter {
    int numOfTab;
    public PageAdapter(FragmentManager fm,int numTabs){
        super(fm);
        this.numOfTab=numTabs;
    }
    public Fragment getItem(int position){
        switch (position){
            case 0:
                Fragment_1 tab1 = new Fragment_1();
                return tab1;
            case 1:
                Fragment_2 tab2 = new Fragment_2();
                return tab2;
            default:
                return null;
        }
    }

    public int getCount(){
        return numOfTab;
    }

}
