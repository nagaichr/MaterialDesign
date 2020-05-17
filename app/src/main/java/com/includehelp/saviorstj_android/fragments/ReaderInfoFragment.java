package com.includehelp.saviorstj_android.fragments;

import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;

import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentReaderInfoBinding;

import androidx.fragment.app.Fragment;

public class ReaderInfoFragment extends Fragment implements View.OnClickListener {

    FragmentReaderInfoBinding binding;
    ArrayAdapter<String> arrayAdapter_auth,arrayAdapter_mode,arrayAdapter_type;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_reader_info, container, false);

        binding = FragmentReaderInfoBinding.inflate(inflater,container,false);
        return binding.getRoot();
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init(view);

        arrayAdapter_auth = new ArrayAdapter(getActivity(), R.layout.list_item, getActivity().getResources().getStringArray(R.array.array_auth_types));
        binding.authExposedDropdown.setThreshold(1);//will start working from first character
        binding.authExposedDropdown.setAdapter(arrayAdapter_auth);//setting the adapter data into the AutoCompleteTextView
       // binding.authExposedDropdown.setText("Only Card",false);

        arrayAdapter_mode = new ArrayAdapter(getActivity(), R.layout.list_item, getActivity().getResources().getStringArray(R.array.array_reader_modes));
        binding.modeExposedDropdown.setThreshold(1);//will start working from first character
        binding.modeExposedDropdown.setAdapter(arrayAdapter_mode);//setting the adapter data into the AutoCompleteTextView


        arrayAdapter_type = new ArrayAdapter(getActivity(), R.layout.list_item, getActivity().getResources().getStringArray(R.array.array_reader_types));
        binding.typeExposedDropdown.setThreshold(1);//will start working from first character
        binding.typeExposedDropdown.setAdapter(arrayAdapter_type);//setting the adapter data into the AutoCompleteTextView


    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_ok:

                if(binding.readerIdEditText.getText().toString().isEmpty()){
                    binding.readerIdTextInput.setError("Filed is mandatory!");
                    return;
                }
                if(binding.modelNoEditText.getText().toString().isEmpty()){
                    binding.modelNoTextInput.setError("Filed is mandatory!");
                    return;
                }
                break;
        }
    }

    private void init(View view){
        binding.btnOk.setOnClickListener(this);

        binding.readerIdEditText.addTextChangedListener(new GenericTextWatcher(binding.readerIdEditText)) ;

        binding.readerIdTextInput.setEndIconOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(!binding.readerIdEditText.isEnabled()){
                    binding.readerIdEditText.setEnabled(true);
                    binding.readerIdEditText.requestFocus();
                    binding.readerIdEditText.setSelection(binding.readerIdEditText.getText().length());
                }
            }
        });
    }


    private class GenericTextWatcher implements TextWatcher{

        private View view;
        private GenericTextWatcher(View view) {
            this.view = view;
        }

        @Override
        public void beforeTextChanged(CharSequence s, int start, int count, int after) { }

        @Override
        public void onTextChanged(CharSequence s, int start, int before, int count) {
            switch(view.getId()){
                case R.id.reader_id_edit_text:
                    binding.readerIdTextInput.setError(null);
                    break;
            }
        }
        @Override
        public void afterTextChanged(Editable s) { }
    }
}
