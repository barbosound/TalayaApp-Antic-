package com.example.pau.talaya;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.Toast;

import java.util.ArrayList;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link ListCases.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link ListCases#newInstance} factory method to
 * create an instance of this fragment.
 */
public class ListCases extends Fragment {
    // TODO: Rename parameter arguments, choose names that match
    // the fragment initialization parameters, e.g. ARG_ITEM_NUMBER
    private static final String ARG_PARAM1 = "param1";
    private static final String ARG_PARAM2 = "param2";

    // TODO: Rename and change types of parameters
    private String mParam1;
    private String mParam2;

    private ArrayList<String> id = new ArrayList<>();
    private ArrayList<String> nom = new ArrayList<>();
    private ArrayList<String> capacitat = new ArrayList<>();
    private ArrayList<String> comarca = new ArrayList<>();
    private ArrayList<String> rating = new ArrayList<>();

    private String Lnom ="";
    private String Lcapaitat="";
    private String Lcomarca="";
    private String Lrating="";

    private OnFragmentInteractionListener mListener;

    public ListCases() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @param param2 Parameter 2.
     * @return A new instance of fragment ListCases.
     */
    // TODO: Rename and change types and number of parameters
    public static ListCases newInstance(String param1, String param2) {
        ListCases fragment = new ListCases();
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

            id = getArguments().getStringArrayList("id");
            nom = getArguments().getStringArrayList("nom");
            capacitat = getArguments().getStringArrayList("capacitat");
            comarca = getArguments().getStringArrayList("comarca");
            rating = getArguments().getStringArrayList("rating");
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {

        View view = inflater.inflate(R.layout.fragment_list_cases, container, false);

        ListView llista = (ListView)view.findViewById(R.id.listCases);

        AdapterCasa adapter = new AdapterCasa(getContext(),R.layout.casa_row,nom,capacitat,comarca,rating);

        llista.setAdapter(adapter);

        llista.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

                Lnom = nom.get(i);

                Lcapaitat = capacitat.get(i);

                Lcomarca = comarca.get(i);

                Lrating = rating.get(i);

                Bundle b = new Bundle();

                b.putString("nom",Lnom);
                b.putString("capacitat",Lcapaitat);
                b.putString("comarca",Lcomarca);
                b.putString("rating",Lrating);

                Intent intencio = new Intent(getActivity(),DescCasa.class);

                intencio.putExtras(b);

                startActivity(intencio);
            }
        });

        // Inflate the layout for this fragment
        return view;
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
        if (context instanceof OnFragmentInteractionListener) {
            mListener = (OnFragmentInteractionListener) context;
        } else {
            throw new RuntimeException(context.toString()
                    + " must implement OnFragmentInteractionListener");
        }
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
