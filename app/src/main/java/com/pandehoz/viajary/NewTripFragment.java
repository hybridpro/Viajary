package com.pandehoz.viajary;


import android.app.DatePickerDialog;
import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.v4.app.DialogFragment;
import android.support.v4.app.Fragment;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.DatePicker;
import android.widget.EditText;
import android.widget.TextView;

import com.pandehoz.viajary.pojos.Expense;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;


/**
 * A simple {@link Fragment} subclass.
 */
public class NewTripFragment extends Fragment {


    private static Button mstartDateBtn;
    private static Button mendDateBtn;
    private static TextView mstartDateTxt;
    private static TextView mendDateTxt;

    EditText mtriptitle;
    EditText mtripFrom;
    EditText mtripTo;

    CheckBox mchkTransport;
    CheckBox mchkCarrental;
    CheckBox mchkOthers;
    CheckBox mchkCityTransport;
    CheckBox mchkFuel;
    CheckBox mchkActivities;
    CheckBox mchkFood;
    CheckBox mchkHotel;

    DialogFragment mstartDateDialogFragment;
    DialogFragment mendDateDialogFragment;


    public NewTripFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_new_trip, container, false);
    }

    Button.OnClickListener mStartDateListener =
            new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showStartDatePickerDialog(v);
                }
            };

    Button.OnClickListener mEndDateListener =
            new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    showEndDatePickerDialog(v);
                }
            };

    Button.OnClickListener saveListener =
            new Button.OnClickListener() {

                @Override
                public void onClick(View v) {
                    saveAsJson(v);
                }
            };

    private void saveAsJson(View v){
        try {
            JSONObject jsonObject = new JSONObject();
            JSONArray expensesArray = new JSONArray();

            jsonObject.put("tripTitle", mtriptitle.getText());
            jsonObject.put("tripFrom", mtripFrom.getText());
            jsonObject.put("tripTo", mtripTo.getText());
            jsonObject.put("startDate", mstartDateTxt.getText());
            jsonObject.put("endDate", mendDateTxt.getText());

            List<Expense> expenses = new ArrayList<>();
            boolean isTransport = mchkTransport.isChecked();
            if(isTransport) {
                expenses.add(Expense.TRANSPORTTO);
                expenses.add(Expense.TRASNSPORTFROM);
                expensesArray.put("Transport To-From");
            }

            boolean isHotel = mchkHotel.isChecked();
            if(isHotel) {
                expenses.add(Expense.HOTEL);
                expensesArray.put("Hotel");
            }

            boolean isFood = mchkFood.isChecked();
            if(isFood) {
                expenses.add(Expense.FOOD);
                expensesArray.put("Food");
            }

            boolean isCarRental = mchkCarrental.isChecked();
            if(isCarRental) {
                expenses.add(Expense.CARRENTAL);
                expensesArray.put("Car Rental");
            }

            boolean isActivities = mchkActivities.isChecked();
            if(isActivities) {
                expenses.add(Expense.HOTEL);
                expensesArray.put("Activities");
            }

            boolean isCityTransport = mchkCityTransport.isChecked();
            if(isCityTransport) {
                expenses.add(Expense.CITYTRANSPORT);
                expensesArray.put("City Transport");
            }

            boolean isFuel = mchkFuel.isChecked();
            if(isFuel) {
                expenses.add(Expense.FUEL);
                expensesArray.put("Fuel");
            }

            boolean isOthers = mchkOthers.isChecked();
            if(isOthers) {
                expenses.add(Expense.OTHERS);
                expensesArray.put("Others");
            }

            jsonObject.put("listExpenses", expensesArray);
            String jsonToWrite = jsonObject.toString() + "\n";
            Log.d("json", jsonToWrite);

            String filename = "trips.json";
            FileOutputStream outputStream;


            try {
                outputStream = getActivity().openFileOutput(filename, Context.MODE_APPEND);
                outputStream.write(jsonToWrite.getBytes());
                outputStream.close();
            } catch (Exception e) {
                e.printStackTrace();
            }

            getActivity().getSupportFragmentManager()
                    .beginTransaction()
                    .replace(R.id.sample_content_fragment,
                            new TripsItemFragment()
                    ).commit();

        }catch (JSONException e) {
            e.printStackTrace();
        }
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        mstartDateBtn = (Button) view.findViewById(R.id.button_startDate);
        mendDateBtn = (Button) view.findViewById(R.id.button_endDate);
        mstartDateTxt = (TextView) view.findViewById(R.id.txt_start_date);
        mendDateTxt = (TextView) view.findViewById(R.id.txt_end_date);
        mtriptitle = (EditText) view.findViewById(R.id.editText_trip_title);
        mtripFrom = (EditText) view.findViewById(R.id.editText_trip_from);
        mtripTo = (EditText) view.findViewById(R.id.editText_trip_to);
        mchkTransport = (CheckBox) view.findViewById(R.id.checkBox_transport);
        mchkCarrental = (CheckBox) view.findViewById(R.id.checkBox_carrental);
        mchkOthers = (CheckBox) view.findViewById(R.id.checkBox_others);
        mchkCityTransport = (CheckBox) view.findViewById(R.id.checkBox_cityTransport);
        mchkFuel = (CheckBox) view.findViewById(R.id.checkBox_fuel);
        mchkActivities = (CheckBox) view.findViewById(R.id.checkBox_activities);
        mchkFood = (CheckBox) view.findViewById(R.id.checkBox_food);
        mchkHotel = (CheckBox) view.findViewById(R.id.checkBox_hotel);

        mstartDateBtn.setOnClickListener(mStartDateListener);
        mstartDateBtn.setVisibility(View.VISIBLE);

        mendDateBtn.setOnClickListener(mEndDateListener);
        mendDateBtn.setVisibility(View.VISIBLE);

        mstartDateTxt.setVisibility(View.INVISIBLE);
        mendDateTxt.setVisibility(View.INVISIBLE);

        Button saveButton = (Button) view.findViewById(R.id.save_trip);
        saveButton.setOnClickListener(saveListener);

    }

    public void showStartDatePickerDialog(View v) {
        mstartDateDialogFragment = new DatePickerFragment();
        Bundle startDialog = new Bundle();
        startDialog.putString("dateToUpdate", "start");
        mstartDateDialogFragment.setArguments(startDialog);
        mstartDateDialogFragment.show(getFragmentManager(), "startdatePicker");
    }

    public void showEndDatePickerDialog(View v) {
        mendDateDialogFragment = new DatePickerFragment();
        Bundle endDialog = new Bundle();
        endDialog.putString("dateToUpdate", "end");
        mendDateDialogFragment.setArguments(endDialog);
        mendDateDialogFragment.show(getFragmentManager(), "enddatePicker");
    }

    public static void updateStartDate(String date){
        mstartDateTxt.setVisibility(View.VISIBLE);
        mstartDateTxt.setText(date);

        mstartDateBtn.setVisibility(View.INVISIBLE);
    }

    public static void updateEndDate(String date){
        mendDateTxt.setVisibility(View.VISIBLE);
        mendDateTxt.setText(date);

        mendDateBtn.setVisibility(View.INVISIBLE);
    }

    public static class DatePickerFragment extends DialogFragment
            implements DatePickerDialog.OnDateSetListener {

        private static String dateToUpdate = "";

        @Override
        public Dialog onCreateDialog(Bundle savedInstanceState) {

            if(getArguments() != null){
                dateToUpdate = getArguments().getString("dateToUpdate");
            }
            // Use the current date as the default date in the picker
            final Calendar c = Calendar.getInstance();
            int year = c.get(Calendar.YEAR);
            int month = c.get(Calendar.MONTH);
            int day = c.get(Calendar.DAY_OF_MONTH);

            // Create a new instance of DatePickerDialog and return it
            return new DatePickerDialog(getActivity(), this, year, month, day);
        }

        public void onDateSet(DatePicker view, int year, int month, int day) {
            String dateText = day + "." + month + "." + year;

            if(dateToUpdate.equalsIgnoreCase("start"))
                NewTripFragment.updateStartDate(dateText);
            else if(dateToUpdate.equalsIgnoreCase("end"))
                NewTripFragment.updateEndDate(dateText);
        }
    }

}
