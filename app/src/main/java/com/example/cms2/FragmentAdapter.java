package com.example.cms2;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.viewpager2.adapter.FragmentStateAdapter;

public class FragmentAdapter extends FragmentStateAdapter {
    private static final int noOfTabs = 5;

    public FragmentAdapter(@NonNull FragmentActivity fragmentActivity) {
        super(fragmentActivity);
    }

    @NonNull @Override public Fragment createFragment(int position) {
        //creating a new instance of viewPager2Fragment via constructor
        return viewPager2Fragment.newInstance(position);
    }

    @Override public int getItemCount() {
        //returning the total number of tabs
        return noOfTabs;
    }
}