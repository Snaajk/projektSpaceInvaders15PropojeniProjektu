package com.example.pc.projektspaceinvaders15propojeniprojektu;

import android.app.Activity;
import android.content.Context;
import android.graphics.Point;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentActivity;
import android.view.Display;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;


//import com.example.pc.projektspaceinvaders15propojeniprojektu.Game.Stara__SpaceInvadersActivity;

import com.example.pc.projektspaceinvaders15propojeniprojektu.Game.Stara__SpaceInvadersView;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Hra.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Hra#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Hra extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    private Stara__SpaceInvadersView stara__spaceInvadersView;


    public Hra() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Hra.
     */
    // TODO: Rename and change types and number of parameters
    public static Hra newInstance(String param1, String param2) {
        Hra fragment = new Hra();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }

    private Stara__SpaceInvadersView staraSpaceInvadersView;                                                            //za2.

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


//        game();
    }

    private void game(){
//        Context context = ((MainActivity)getActivity()).getContext();
//        MainActivity activity =((MainActivity)getActivity()).getActivity();
        if(! getActivity().isFinishing()  ){

            Context context = getActivity();
            MainActivity activity = (MainActivity)getActivity();



            Display display = getActivity().getWindowManager().getDefaultDisplay();                                   //za3.
            Point size = new Point();                                                                   //za4.
            display.getSize(size);
            staraSpaceInvadersView = new Stara__SpaceInvadersView(context, size.x, size.y, activity);                    //za5.
//            activity.setContentView(staraSpaceInvadersView);


            System.out.println("private void game(){ @@@@@@@@@@@@@@@@@ je null   !!!!!!!!!!!!!!!!!!!!!!"+size.x+" "+size.y);

        }else{
            System.out.println("private void game(){ !!!!!!!!!!!!!!!! je null   !!!!!!!!!!!!!!!!!!!!!!");
        }


    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment



//        Context context = ((MainActivity)getActivity()).getContext();
//        MainActivity activity =((MainActivity)getActivity()).getActivity();
//        Display display = getActivity().getWindowManager().getDefaultDisplay();                                   //za3.
//        Point size = new Point();                                                                   //za4.
//        display.getSize(size);


        game();




//        return inflater.inflate(R.layout.fragment_hra, container, false);
//        return  new Stara__SpaceInvadersView(context, size.x, size.y, activity);
        return staraSpaceInvadersView;
//        return  staraSpaceInvadersView;
    }

    @Override
    public void onResume() {                                                                     //za6.
//        initListeners();
        super.onResume();
        staraSpaceInvadersView.resume();                                                                 //za7.
    }


    @Override
    public void onPause() {                                                                      //za8.
        /* mSensorManager.unregisterListener(this);*/
        super.onPause();
        staraSpaceInvadersView.pause();                                                                  //za9.
    }



    // TODO: Rename method, update argument and hook method into UI event
    public void onButtonPressed(Uri uri) {
        if (mListener != null) {
            mListener.onFragmentInteraction(uri);
        }
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        /*if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }*/
    }

    @Override
    public void onDetach() {
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
    public interface OnFragmentInteractionListener {
        // TODO: Update argument type and name
        void onFragmentInteraction(Uri uri);
    }
}
