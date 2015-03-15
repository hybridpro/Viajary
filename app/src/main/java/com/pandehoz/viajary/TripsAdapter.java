package com.pandehoz.viajary;

import android.content.res.Resources;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import com.pandehoz.viajary.dao.Trips;

/**
 * Created by test on 14.03.15.
 */
public class TripsAdapter extends BaseAdapter {

    private final LayoutInflater mLayoutInflater;
    private final int mResourceId;
    private Resources res;

    public TripsAdapter(LayoutInflater inflater, int resourceId) {
        mLayoutInflater = inflater;
        mResourceId = resourceId;
        Trips.retrieveTrips();
    }

    @Override
    public int getCount() {
        return Trips.ITEMS.size();
    }

    @Override
    public Trips.TripItem getItem(int position) {
        return Trips.ITEMS.get(position);
    }

    @Override
    public long getItemId(int position) {
        return Trips.ITEMS.get(position).id;
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
        final View view;
        final ViewHolder holder;
        if (null == convertView) {
            view = mLayoutInflater.inflate(mResourceId, parent, false);
            holder = new ViewHolder();
            assert view != null;
            holder.tripTitle = (TextView) view.findViewById(R.id.tripTitle);
            holder.tripStartDate = (TextView) view.findViewById(R.id.tripStartDate);
            holder.tripEndDate = (TextView) view.findViewById(R.id.end_date);
            holder.expenses = (TextView) view.findViewById(R.id.expenses_textView);
            res = view.getResources();
            view.setTag(holder);
        } else {
            view = convertView;
            holder = (ViewHolder) view.getTag();
        }
        bindView(holder, position);
        return view;
    }

    public void bindView(ViewHolder holder, int position) {
        Trips.TripItem trip = getItem(position);
        holder.tripTitle.setText(trip.tripTitle);
        holder.tripStartDate.setText(String.format(res.getString(R.string.start_date), trip.startDate));
        holder.tripEndDate.setText(String.format(res.getString(R.string.end_date), trip.endDate));
        holder.expenses.setText(String.format(res.getString(R.string.expenses_list_item), trip.expenses));
    }

    public static class ViewHolder {
        public TextView tripTitle;
        public TextView tripStartDate;
        public TextView tripEndDate;
        public TextView expenses;
    }

}