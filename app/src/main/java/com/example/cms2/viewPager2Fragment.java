package com.example.cms2;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.GridView;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.core.content.ContextCompat;
import androidx.fragment.app.Fragment;

import java.util.ArrayList;
import java.util.List;

public class viewPager2Fragment extends Fragment {

    //declaring variable components to be used in this class
    GridView gridView;
    List<Item> items = new ArrayList<>();

    //declaring an array of colors for the background of each tab
    private int[] COLOR_MAP = {
            R.color.white,R.color.white,R.color.white,R.color.white,R.color.white
    };
    //declaring more variables to be used in this class
    private static final String count = "";
    private Integer counter;

    //constructor taking an integer as an argument
    public static viewPager2Fragment newInstance(Integer counter) {
        //creating a new instance of the class
        viewPager2Fragment fragment = new viewPager2Fragment();
        //declaring bundle
        Bundle args = new Bundle();
        //adding an int to the bundle with value of the counter parameter
        args.putInt(count, counter);
        //setting fragment arguments
        fragment.setArguments(args);
        return fragment;
    }

    public viewPager2Fragment() {
        // Required empty public constructor
    }

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            //counter variable stored above saving the arguments from the bundle declared above
            counter = getArguments().getInt(count);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        //inflating layout
        return inflater.inflate(R.layout.fragment_layout, container, false);
    }

    @Override public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        //setting the background color to white as declared above, mapping from the array
        //by using the counter
        view.setBackgroundColor(ContextCompat.getColor(getContext(), COLOR_MAP[counter]));

        //arraylist declared above saving all from db using counter
        items = Item.getAll(counter);
        //locating gridView from the layout resource file
        gridView = view.findViewById(R.id.grid);

        //instantiating an adapter to the grid to handle the inputting from the arraylist to the grid
        GridAdapter gridAdapter = new GridAdapter(getActivity(),R.layout.grid_element_layout,items);
        //setting adapter
        gridView.setAdapter(gridAdapter);

        //adding an onclick for each element in the grid
        gridView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {

            }
        });

    }

    //on resume used for returning to the fragment
    @Override
    public void onResume() {
        super.onResume();
        //re-obtaining all elements from db by counter
        items = Item.getAll(counter);

        //instantiating an adapter to the grid to handle the inputting from the arraylist to the grid
        GridAdapter gridAdapter = new GridAdapter(getActivity(),R.layout.grid_element_layout,items);
        //setting adapter
        gridView.setAdapter(gridAdapter);
    }
}
