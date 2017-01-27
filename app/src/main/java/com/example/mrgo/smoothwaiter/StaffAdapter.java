package com.example.mrgo.smoothwaiter;

import android.content.Context;
import android.support.annotation.NonNull;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

/**
 * Created by Mr.GO on 27/01/2017.
 */

public class StaffAdapter extends ArrayAdapter<String>
{
    public StaffAdapter(Context context, String []values)
    {
        super(context, R.layout.row_staff_layout, R.id.rowText, values);
    }

    @NonNull
    @Override
    public View getView(int position, View convertView, ViewGroup parent)
    {
        //return super.getView(position, convertView, parent);

        //used to take an xml file and make a code view object out of it
        LayoutInflater inflater = LayoutInflater.from(getContext());

        //inflate takes an xml file and generates a view from it.
        //for which parent group to automatically add it to?
        //and true or false, should I add it to the parent group? In our case, not this time
        View view = inflater.inflate(R.layout.row_staff_layout, parent, false);

        String tvShow = getItem(position); //getPosition gets item at position from values passed in via the constructor. e.g position = 3. tvShows[3]

        TextView rowText = (TextView) view.findViewById(R.id.rowText); //get the TextView reference from the generated view
        rowText.setText(tvShow);

        return view;
    }
}
