package com.includehelp.saviorstj_android.fragments;

import android.app.DatePickerDialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.DatePicker;

import com.bumptech.glide.Glide;
import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentEnrollUserBinding;

import java.util.Calendar;

import androidx.fragment.app.Fragment;

public class EnrollUserFragment extends Fragment implements View.OnClickListener {

   FragmentEnrollUserBinding binding;
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_enroll_user, container, false);
        binding = FragmentEnrollUserBinding.inflate(inflater,container,false);

        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init(view);

        Glide.with(getActivity()).load(R.drawable.finger_scan).into(binding.imgFrame11);
        Glide.with(getActivity()).load(R.drawable.finger_scan).into(binding.imgFrame21);
        Glide.with(getActivity()).load(R.drawable.finger_scan).into(binding.imgFrame31);
        Glide.with(getActivity()).load(R.drawable.finger_scan).into(binding.imgFrame12);
        Glide.with(getActivity()).load(R.drawable.finger_scan).into(binding.imgFrame22);
        Glide.with(getActivity()).load(R.drawable.finger_scan).into(binding.imgFrame32);
    }


    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){

        }
    }

    private void init(View view){
        binding.userDobTextInput.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
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

                                String dob = String.format("%02d", dayOfMonth) +
                                        "/" + String.format("%02d", (monthOfYear + 1))+
                                        "/" + String.format("%04d", year);
                                binding.userDobEditText.setText(dob);
                            }
                        }, mYear, mMonth, mDay);
                datePickerDialog.show();
            }
        });
    }
}
