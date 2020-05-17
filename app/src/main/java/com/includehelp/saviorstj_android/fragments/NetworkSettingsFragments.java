package com.includehelp.saviorstj_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentNetworkSettingsBinding;

import androidx.fragment.app.Fragment;

public class NetworkSettingsFragments extends Fragment implements View.OnClickListener {

    FragmentNetworkSettingsBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_network_settings, container, false);

        binding = FragmentNetworkSettingsBinding.inflate(inflater,container,false);
        return binding.getRoot();
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
            case R.id.btn_submit:
                if(binding.readeripEditText.getText().toString().isEmpty()){
                    binding.readeripEditText.setError("Must be Required!");
                    return;
                }

                if(binding.netmaskEditText.getText().toString().isEmpty()){
                    binding.netmaskTextInput.setError("Empty!!");
                    return;
                }
       case R.id.btn_cancel:
                getActivity().onBackPressed();
                break;
        }
    }

    private void init(View view){
        binding.btnSubmit.setOnClickListener(this);

    }
}
