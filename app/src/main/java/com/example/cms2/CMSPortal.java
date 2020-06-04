package com.example.cms2;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.viewpager2.widget.ViewPager2;
import com.google.android.material.tabs.TabLayout;
import com.google.android.material.tabs.TabLayoutMediator;

public class CMSPortal extends AppCompatActivity {

    //integer storing the screen number
    int screen;

    //override method for menu in the top right of the screen inside the three dots
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {

        //instantiating MenuInflater to inflate the menu in the menu rerource file
        MenuInflater inflater = getMenuInflater();
        //inflating menu cms_menu from menu resource file
        inflater.inflate(R.menu.cms_menu, menu);

        return true;
    }

    //override method handling selection of menu item of the menu inflated above
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {

        //setting screen integer to the current screen id
        screen = viewPager.getCurrentItem();
        //instantiating intent passing screen number under the name "FragID"
        Intent intent = new Intent(CMSPortal.this, PostActivity.class).putExtra("FragID",screen);
        //starting activity swapping the current activity with the PostActivity allowing the user to create a post
        //on the current activity
        startActivity(intent);

        return super.onOptionsItemSelected(item);
    }

    //declaring tab layout for the tabs at the top of the screen
    TabLayout tabLayout;
    //declaring the container to hold the fragments for each tab
    ViewPager2 viewPager;
    //array of names for the names of the tabs
    private String [] TITLE_MAP = {
            "World", "Business", "Technology", "Science", "Sports"
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        //locating layout resource file activity_c_m_s_toolbar for this java class
        setContentView(R.layout.activity_c_m_s_toolbar);

        //locating the viewPager in the layout resource file
        viewPager = findViewById(R.id.fragmentContainer);
        //locating the tabLayout  in the layout resource file
        tabLayout = findViewById(R.id.tabs);

        //setting the adapter to set the names of tabs above to the tabs in the tabLayout
        viewPager.setAdapter(createCardAdapter());
        new TabLayoutMediator(tabLayout, viewPager,
                new TabLayoutMediator.TabConfigurationStrategy() {
                    @Override public void onConfigureTab(@NonNull TabLayout.Tab tab, int position) {
                        //setting the title of each tab
                        tab.setText(TITLE_MAP[position]);
                    }
                }).attach();


    }

    private FragmentAdapter createCardAdapter() {
        //declaring the fragment adapter to allow us to replace the fragment in the viewPager
        FragmentAdapter adapter = new FragmentAdapter(this);
        return adapter;
    }
}
