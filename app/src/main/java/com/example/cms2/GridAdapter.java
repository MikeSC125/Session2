package com.example.cms2;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import org.w3c.dom.Text;

import java.util.List;

public class GridAdapter extends ArrayAdapter<Item> {

    //declaring list called items
    List <Item> items;


    public GridAdapter(@NonNull Context context, int resource, List<Item> pItems) {
        super(context, resource);

        //setting list declared above to the list parameter
        items = pItems;
    }

    @Override
    public int getCount() {
        //getting size of the arraylist declared above
        return items.size();
    }

    @NonNull
    @Override
    public View getView(final int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        View view;
        //inflating layout, getting context of activity and system service to inflate
        LayoutInflater inflater = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        //inflating view
        view = inflater.inflate(R.layout.grid_element_layout, null);

        //locating imageview in layout resource file
        ImageView imageView = view.findViewById(R.id.postImage);
        //storing imageURI to string as per individual element in grid
        String imageURI = items.get(position).image;
        //setting the image locating the image as per URI string above
        imageView.setImageURI(Uri.parse(imageURI));

        return view;
    }



}
