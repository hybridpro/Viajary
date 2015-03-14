package com.pandehoz.viajary;

import android.app.Activity;
import android.net.Uri;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;


/**
 * A simple {@link Fragment} subclass.
 * Activities that contain this fragment must implement the
 * {@link NoTripFragment.OnFragmentInteractionListener} interface
 * to handle interaction events.
 * Use the {@link NoTripFragment#newInstance} factory method to
 * create an instance of this fragment.
 */
public class NoTripFragment extends Fragment {

    public NoTripFragment() {
        // Required empty public constructor
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_no_trip, container, false);
    }

    Button.OnClickListener mAddTripListener =
            new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.sample_content_fragment,
                                    new NewTripFragment()
                            ).commit();

                }
            };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        Button addTripBtn = (Button) view.findViewById(R.id.add_trip);
        addTripBtn.setOnClickListener(mAddTripListener);

    }

}
