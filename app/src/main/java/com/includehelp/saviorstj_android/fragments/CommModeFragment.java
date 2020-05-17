package com.includehelp.saviorstj_android.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.includehelp.saviorstj_android.MainActivity;
import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentCommModeBinding;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class CommModeFragment extends DialogFragment implements View.OnClickListener {
    FragmentCommModeBinding binding;

    @Override
    public Dialog onCreateDialog(@Nullable Bundle savedInstanceState) {
        return super.onCreateDialog(savedInstanceState);
    }

    @Override
    public void onStart() {
        super.onStart();
        Dialog dialog = getDialog();
        if (dialog != null) {
            int width = ViewGroup.LayoutParams.MATCH_PARENT;
            int height = ViewGroup.LayoutParams.WRAP_CONTENT;
            dialog.getWindow().setLayout(width, height);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_comm_mode, container, false);

        //View binding implementation
        binding = FragmentCommModeBinding.inflate(inflater, container, false);
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
            case R.id.btn_comm_submit:
                dismiss();

                NetworkSettingsFragments networkSettingsFragments    =  new NetworkSettingsFragments();
                ((MainActivity)getActivity()).setFragment(networkSettingsFragments);

            break;
            case R.id.btn_comm_cancel:
                dismiss();
                break;
        }
    }

    private void init(View view){
        binding.btnCommSubmit.setOnClickListener(this);
        binding.btnCommCancel.setOnClickListener(this);
    }
}
