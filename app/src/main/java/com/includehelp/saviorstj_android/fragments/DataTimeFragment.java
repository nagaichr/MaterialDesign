package com.includehelp.saviorstj_android.fragments;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;
import android.widget.TimePicker;
import android.widget.Toast;

import com.google.android.material.bottomsheet.BottomSheetDialogFragment;
import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmenDateTimeBinding;

import java.util.Calendar;

public class DataTimeFragment extends BottomSheetDialogFragment implements View.OnClickListener {

    FragmenDateTimeBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragmen_date_time, container, false);

        binding = FragmenDateTimeBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init(view);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.card_set_date:

                //Get Current Date
                final Calendar c = Calendar.getInstance();
                int mYear = c.get(Calendar.YEAR);
                int mMonth = c.get(Calendar.MONTH);
                int mDay = c.get(Calendar.DAY_OF_MONTH);
                DatePickerDialog datePickerDialog = new DatePickerDialog(getActivity(),
                        new DatePickerDialog.OnDateSetListener() {

                            @Override
                            public void onDateSet(DatePicker view, int year,
                                                  int monthOfYear, int dayOfMonth) {
                                Toast.makeText(getActivity(), dayOfMonth + "-" + (monthOfYear + 1) + "-" + year, Toast.LENGTH_SHORT).show();
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
                break;

            case R.id.card_set_time:

                // Get Current Time
                final Calendar cal = Calendar.getInstance();
                int mHour = cal.get(Calendar.HOUR_OF_DAY);
                int mMinute = cal.get(Calendar.MINUTE);

                // Launch Time Picker Dialog
                TimePickerDialog timePickerDialog = new TimePickerDialog(getActivity(),
                        new TimePickerDialog.OnTimeSetListener() {

                            @Override
                            public void onTimeSet(TimePicker view, int hourOfDay,int minute) {
                                Toast.makeText(getActivity(), hourOfDay + ":" + minute, Toast.LENGTH_SHORT).show();
                            }
                        }, mHour, mMinute, true);
                timePickerDialog.show();

                break;
        }
    }

    private void init(View view){
        binding.cardSetDate.setOnClickListener(this);
        binding.cardSetTime.setOnClickListener(this);
    }
}
