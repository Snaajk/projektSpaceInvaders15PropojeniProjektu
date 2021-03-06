package com.example.pc.projektspaceinvaders15propojeniprojektu;

import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.provider.MediaStore;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v4.app.ListFragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;




/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link Foto.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link Foto#newInstance} factory method to
 * create an instance of this fragment.
 */
public class Foto extends ListFragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private OnFragmentInteractionListener mListener;

    public Foto() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment Foto.
     */
    // TODO: Rename and change types and number of parameters
    public static Foto newInstance(String param1, String param2) {
        Foto fragment = new Foto();
        Bundle args = new Bundle();
        args.putString(ARG_PARAM1, param1);
        args.putString(ARG_PARAM2, param2);
        fragment.setArguments(args);
        return fragment;
    }



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            mParam1 = getArguments().getString(ARG_PARAM1);
            mParam2 = getArguments().getString(ARG_PARAM2);
        }


    }

    // -------------------------------------------------------------moje zacatek -----------------------------------
    // pozor findViewById tady msusi volat v meteode public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {

    private ImageView imageView;
    private boolean vyfoceno = false;

    @Override
    public void onViewCreated(View view, @Nullable Bundle savedInstanceState) {
//        ImageView imageView = (ImageView) getView().findViewById(R.id.foo);
        // or  (ImageView) view.findViewById(R.id.foo);

        Button btnCamera = (Button) getView().findViewById(R.id.button);
        imageView = (ImageView) getView().findViewById(R.id.imageView);

        btnCamera.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MediaStore.ACTION_IMAGE_CAPTURE);
                startActivityForResult(intent,0);
                vyfoceno = true;
            }
        });
    }

    private Bitmap bitmap;
    private Intent data;

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        this.bitmap = (Bitmap)data.getExtras().get("data");
        imageView.setImageBitmap(bitmap);

        ((MainActivity)getActivity()).setFoto(bitmap);                                       //za4.


    }



    public boolean getVyfoceno(){
        return vyfoceno;
    }
    public Bitmap getBitmap(){
        return bitmap;
    }

        // -------------------------------------------------------------moje konec -----------------------------------


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_foto, container, false);
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
