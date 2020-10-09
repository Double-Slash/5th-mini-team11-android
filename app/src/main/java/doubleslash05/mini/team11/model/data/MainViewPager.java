package doubleslash05.mini.team11.model.data;

import android.os.Bundle;
import android.view.View;

import androidx.annotation.NonNull;
import androidx.core.view.ViewCompat;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.viewpager2.adapter.FragmentStateAdapter;
import androidx.viewpager2.widget.ViewPager2;

import java.util.ArrayList;

import doubleslash05.mini.team11.R;
import doubleslash05.mini.team11.ui.sj.FragmentAdapter;
import me.relex.circleindicator.CircleIndicator3;

public class MainViewPager extends FragmentActivity {
    private ViewPager2 mpager;
    private FragmentStateAdapter pagerAdapter;
    private int num_page = 5;
    private CircleIndicator3 mIndicator;

    public FragmentActivity fa;
    public ArrayList<String> itemList;

    public MainViewPager(ArrayList<String> itemList) {
        this.itemList = itemList;

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        mpager = findViewById(R.id.viewpager);
        pagerAdapter = new FragmentAdapter(fa,num_page);
        mpager.setAdapter(pagerAdapter);

        mIndicator = findViewById(R.id.indicator);
        mIndicator.setViewPager(mpager);
        mIndicator.createIndicators(num_page,0);

        mpager.setOrientation(ViewPager2.ORIENTATION_HORIZONTAL);
        mpager.setCurrentItem(1000);
        mpager.setOffscreenPageLimit(4);

        mpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
                if (positionOffsetPixels == 0) {
                    mpager.setCurrentItem(position);
                }
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                mIndicator.animatePageSelected(position%num_page);
            }

        });

        final float pageMargin= getResources().getDimensionPixelOffset(R.dimen.pageMargin);
        final float pageOffset = getResources().getDimensionPixelOffset(R.dimen.offset);

        mpager.setPageTransformer(new ViewPager2.PageTransformer() {
            @Override
            public void transformPage(@NonNull View page, float position) {
                float myOffset = position * -(2 * pageOffset + pageMargin);
                if (mpager.getOrientation() == ViewPager2.ORIENTATION_HORIZONTAL) {
                    if (ViewCompat.getLayoutDirection(mpager) == ViewCompat.LAYOUT_DIRECTION_RTL) {
                        page.setTranslationX(-myOffset);
                    } else {
                        page.setTranslationX(myOffset);
                    }
                } else {
                    page.setTranslationY(myOffset);
                }
            }
        });

    }
}