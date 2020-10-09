package doubleslash05.mini.team11.ui.sj;

import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;

import java.util.ArrayList;

import doubleslash05.mini.team11.model.data.Fragment1;
import doubleslash05.mini.team11.model.data.Fragment2;
import doubleslash05.mini.team11.model.data.Fragment3;
import doubleslash05.mini.team11.model.data.Fragment4;
import doubleslash05.mini.team11.model.data.Fragment5;

public class FragmentAdapter extends FragmentStateAdapter {
    public int mCount;
    public ArrayList<String> itemList;
    static public FragmentManager fa;
    FragmentManager fm;

    public FragmentAdapter( FragmentActivity fa, int count) {

        super(fa);

        mCount = count;

    }
    @NonNull
    @Override
    public Fragment createFragment(int position) {
        int index = getRealPosition(position);

        if(index==0) return new Fragment1();
        else if(index==1) return new Fragment2();
        else if(index==2) return new Fragment3();
        else if(index==3) return new Fragment4();
        else return new Fragment5();
    }

    @Override
    public int getItemCount() {
        return 2000;
    }

    public int getRealPosition(int position) { return position % mCount; }

}