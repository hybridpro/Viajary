package com.pandehoz.viajary;

import android.app.Activity;
import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AbsListView;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.GridView;
import android.widget.ListAdapter;
import android.widget.TextView;

import com.pandehoz.viajary.dao.Trips;

/**
 * Created by test on 14.03.15.
 */
public class TripsItemFragment extends Fragment implements AbsListView.OnItemClickListener{

    private TripsAdapter mAdapter;

    private static final String TAG = "TripsItemFragment";

    private Button addTripbtn;

    public static TripsItemFragment newInstance() {
        return new TripsItemFragment();
    }

    public TripsItemFragment() {
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // This is the adapter we use to populate the grid.
        mAdapter = new TripsAdapter(inflater, R.layout.fragment_trips);
        // Inflate the layout with a GridView in it.
        return inflater.inflate(R.layout.trips_grid_view, container, false);

    }

    Button.OnClickListener addTripListener =
            new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    getActivity().getSupportFragmentManager()
                            .beginTransaction()
                            .replace(R.id.sample_content_fragment,
                                    new NewTripFragment()
                            )
                                    // We push the fragment transaction to back stack. User can go back to the
                                    // previous fragment by pressing back button.
                            .addToBackStack("New Trip")
                            .commit();
                }
            };

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        GridView grid = (GridView) view.findViewById(R.id.grid);
        addTripbtn = (Button) view.findViewById(R.id.grid_add_trip);
        addTripbtn.setOnClickListener(addTripListener);
        grid.setAdapter(mAdapter);
        grid.setOnItemClickListener(this);
    }

    @Override
    public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
        Trips.TripItem trip = mAdapter.getItem(position);
        Log.i(TAG, trip.tripTitle + " clicked. Replacing fragment.");
        // We start the fragment transaction here. It is just an ordinary fragment transaction.
        /*getActivity().getSupportFragmentManager()
                .beginTransaction()
                .replace(R.id.sample_content_fragment,
                        DetailFragment.newInstance(meat.resourceId, meat.title,
                                (int) view.getX(), (int) view.getY(),
                                view.getWidth(), view.getHeight())
                )
                        // We push the fragment transaction to back stack. User can go back to the
                        // previous fragment by pressing back button.
                .addToBackStack("detail")
                .commit();*/
    }

}
