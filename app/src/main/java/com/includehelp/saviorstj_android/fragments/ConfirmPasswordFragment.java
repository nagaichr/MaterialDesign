package com.includehelp.saviorstj_android.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.includehelp.saviorstj_android.MainActivity;
import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentConfirmPasswordBinding;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ConfirmPasswordFragment extends DialogFragment implements View.OnClickListener {

    FragmentConfirmPasswordBinding binding;
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
        //View view =  inflater.inflate(R.layout.fragment_confirm_password, container, false);

        binding = FragmentConfirmPasswordBinding.inflate(inflater,container,false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);

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
            case R.id.btn_auth:
                if(binding.passAuthEditText.getText().toString().isEmpty()){
                    binding.passwordAuthTextInput.setError("Filed is mandatory!");
                }
                else if(binding.passAuthEditText.getText().toString().equals("savior")) {

                    dismiss();

                    SettingScreenFragments settingScreenFragments = new SettingScreenFragments();
                    ((MainActivity) getActivity()).setFragment(settingScreenFragments);
                }
                else{
                    binding.txtAuthMsg.setText("Invalid Password !!!");
                }
                break;
        }
    }

    private void init(View view){
        binding.passAuthEditText.setText("savior");
        binding.btnAuth.setOnClickListener(this);
    }
}
