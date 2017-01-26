package com.example.mrgo.smoothwaiter;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.support.design.widget.TabLayout;
import android.support.design.widget.FloatingActionButton;
import android.support.design.widget.Snackbar;
import android.support.v4.app.FragmentActivity;
import android.support.v7.app.AppCompatActivity;
import android.support.v7.widget.Toolbar;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;
import android.support.v4.view.ViewPager;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;

import android.widget.Button;
import android.widget.EditText;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

public class OrderActivity extends AppCompatActivity
{

    /**
     * The {@link android.support.v4.view.PagerAdapter} that will provide
     * fragments for each of the sections. We use a
     * {@link FragmentPagerAdapter} derivative, which will keep every
     * loaded fragment in memory. If this becomes too memory intensive, it
     * may be best to switch to a
     * {@link android.support.v4.app.FragmentStatePagerAdapter}.
     */
    private SectionsPagerAdapter mSectionsPagerAdapter;

    /**
     * The {@link ViewPager} that will host the section contents.
     */
    private ViewPager mViewPager;

    private DatabaseHandler db = new DatabaseHandler(this);
    private static int totalTable;

    @Override
    protected void onCreate(Bundle savedInstanceState)
    {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_order);

        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        setSupportActionBar(toolbar);
        // Create the adapter that will return a fragment for each of the three
        // primary sections of the activity.
        mSectionsPagerAdapter = new SectionsPagerAdapter(getSupportFragmentManager());

        // Set up the ViewPager with the sections adapter.
        mViewPager = (ViewPager) findViewById(R.id.container);
        mViewPager.setAdapter(mSectionsPagerAdapter);

        TabLayout tabLayout = (TabLayout) findViewById(R.id.tabs);
        tabLayout.setupWithViewPager(mViewPager);

        FloatingActionButton fab = (FloatingActionButton) findViewById(R.id.fab);
        fab.setOnClickListener(new View.OnClickListener()
        {
            @Override
            public void onClick(View view)
            {
                Snackbar.make(view, "Replace with your own action", Snackbar.LENGTH_LONG)
                        .setAction("Action", null).show();
            }
        });

        totalTable = db.getTotalTable();
        this.setTitle("Smooth Waiter Order");
    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu)
    {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_order, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item)
    {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {

            Intent orderScreen = new Intent(this,SettingActivity.class);
            startActivity(orderScreen);
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /**
     * A placeholder fragment containing a simple view.
     */
    public static class PlaceholderFragment extends Fragment
    {
        /**
         * The fragment argument representing the section number for this
         * fragment.
         */
        private static final String ARG_SECTION_NUMBER = "section_number";

        public PlaceholderFragment()
        {
        }

        /**
         * Returns a new instance of this fragment for the given section
         * number.
         */
        public static PlaceholderFragment newInstance(int sectionNumber)
        {
            PlaceholderFragment fragment = new PlaceholderFragment();
            Bundle args = new Bundle();
            args.putInt(ARG_SECTION_NUMBER, sectionNumber);
            fragment.setArguments(args);
            return fragment;
        }

        @Override
        public View onCreateView(LayoutInflater inflater, ViewGroup container,
                                 Bundle savedInstanceState)
        {

            if(getArguments().getInt(ARG_SECTION_NUMBER) == 1)
            {

//                View rootView = inflater.inflate(R.layout.fragment_table, container, false);
//                return rootView;
                int table = totalTable;
                int temp1 = table%3;
                int temp2 = table/3;
                int row = table/3;
                if(temp1 > 0)
                {
                    row = 1 + temp2;
                }
                //Set a linearLayout to add buttons
                LinearLayout mainLinearLayout = new LinearLayout(getActivity());
                // Set the layout full width, full height
                LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
                mainLinearLayout.setLayoutParams(params);
                mainLinearLayout.setOrientation(LinearLayout.VERTICAL); //or VERTICAL


                int countTable = 0;
                Button button = new Button(getActivity());
                int tableRow = 1;
                for (int i = 1; i <= row; i++)
                {
                    if(i==row)
                    {
                        tableRow = temp1 == 0 ? 3 : temp1;
                    }
                    else
                    {
                        tableRow = 3;
                    }

                    LinearLayout subLinearLayout = new LinearLayout(getActivity());
                    params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
                    subLinearLayout.setLayoutParams(params);
                    subLinearLayout.setOrientation(LinearLayout.HORIZONTAL); //or VERTICAL

                    for (int j = 1;j <= tableRow;j++)
                    {
                        button = new Button(getActivity());
                        countTable++;
                        //For buttons visibility, you must set the layout params in order to give some width and height:
                        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT, 1);
                        button.setLayoutParams(params);
                        button.setText("T" + countTable);
                        button.setId(View.generateViewId());
                        button.setOnClickListener(new View.OnClickListener()
                        {
                            @Override
                            public void onClick(View v)
                            {
                                Staff staff = new Staff();
                                staff.setStuffName("555");
                                Button btn = (Button) v;
                                btn.getText();
                                Intent nextScreen = new Intent(v.getContext(), SettingActivity.class);

                                nextScreen.putExtra("staffKey1", staff); //Key and Value pair
                                startActivityForResult(nextScreen, 1);

                            }
                        });
                        subLinearLayout.addView(button);
                    }
                    mainLinearLayout.addView(subLinearLayout);
                }




                return mainLinearLayout;
            }
            View rootView = inflater.inflate(R.layout.fragment_order, container, false);
            TextView textView = (TextView) rootView.findViewById(R.id.section_label);
            textView.setText(getString(R.string.section_format, getArguments().getInt(ARG_SECTION_NUMBER)));
            return rootView;
        }
    }

    /**
     * A {@link FragmentPagerAdapter} that returns a fragment corresponding to
     * one of the sections/tabs/pages.
     */
    public class SectionsPagerAdapter extends FragmentPagerAdapter
    {

        public SectionsPagerAdapter(FragmentManager fm)
        {
            super(fm);
        }

        @Override
        public Fragment getItem(int position)
        {
            // getItem is called to instantiate the fragment for the given page.
            // Return a PlaceholderFragment (defined as a static inner class below).
            return PlaceholderFragment.newInstance(position + 1);
        }

        @Override
        public int getCount()
        {
            // Show 3 total pages.
            return 3;
        }

        @Override
        public CharSequence getPageTitle(int position)
        {
            switch (position) {
                case 0:
                    return "Table";
                case 1:
                    return "Status Dish";
                case 2:
                    return "Staff";
            }
            return null;
        }
    }
}
