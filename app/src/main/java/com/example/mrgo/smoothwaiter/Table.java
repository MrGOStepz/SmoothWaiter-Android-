package com.example.mrgo.smoothwaiter;

import android.content.Context;
import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.LinearLayout;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Table.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Table#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Table extends Fragment
{
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Table()
    {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Table.
     */
    // TODO: Rename and change types and number of parameters
    public static Table newInstance(String param1, String param2)
    {
        Table fragment = new Table();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    @Override
    public void onCreate(Bundle savedInstanceState)
    {
        super.onCreate(savedInstanceState);
        if (getArguments() != null)
        {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }

    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState)
    {
//        // Inflate the layout for this fragment
//        return inflater.inflate(R.layout.fragment_table, container, false);

        // TODO Auto-generated method stub
        super.onCreateView(inflater,container, savedInstanceState);

        //Set a linearLayout to add buttons
        LinearLayout mainLinearLayout = new LinearLayout(getActivity());
        // Set the layout full width, full height
        LinearLayout.LayoutParams params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.MATCH_PARENT);
        mainLinearLayout.setLayoutParams(params);
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL); //or VERTICAL

        LinearLayout subLinearLayout = new LinearLayout(getActivity());
        params = new LinearLayout.LayoutParams(LinearLayout.LayoutParams.MATCH_PARENT, LinearLayout.LayoutParams.WRAP_CONTENT);
        mainLinearLayout.setLayoutParams(params);
        mainLinearLayout.setOrientation(LinearLayout.VERTICAL); //or VERTICAL


        Button button = new Button(getActivity());
        //For buttons visibility, you must set the layout params in order to give some width and height:
        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
        button.setLayoutParams(params);
        button.setText("T1");
        button.setId(View.generateViewId());

        subLinearLayout.addView(button);

        button = new Button(getActivity());
        //For buttons visibility, you must set the layout params in order to give some width and height:
        params = new LinearLayout.LayoutParams(0, LinearLayout.LayoutParams.WRAP_CONTENT,1);
        button.setLayoutParams(params);
        button.setText("T2");
        button.setId(View.generateViewId());

        subLinearLayout.addView(button);

        mainLinearLayout.addView(subLinearLayout);

        return mainLinearLayout;

    }


    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri)
    {
        if (mListener != null)
        {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context)
    {
        super.onAttach(context);
        if (context instanceof OnFragmentInteractionListener)
        {
            mListener = (OnFragmentInteractionListener) context;
        }
        else
        {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
    }

    @Override
    public void onDetach()
    {
        super.onDetach();
        mListener = null;
    }

    /**
     * This interface must be implemented by activities that contain this
     * fragment to allow an interaction in this fragment to be communicated
     * to the activity and potentially other fragments contained in that
     * activity.
     * <p>
     * See the Android Training lesson <a href=
     * "http://developer.android.com/training/basics/fragments/communicating.html"
     * >Communicating with Other Fragments</a> for more information.
     */
    public interface OnFragmentInteractionListener
    {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
