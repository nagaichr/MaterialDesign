package com.includehelp.saviorstj_android.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentResetPasswordBinding;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class ResetPasswordFragment extends DialogFragment implements View.OnClickListener {

    FragmentResetPasswordBinding binding;

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
        //View view =  inflater.inflate(R.layout.fragment_reset_password, container, false);

        binding = FragmentResetPasswordBinding.inflate(inflater,container,false);
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
            case R.id.btn_reset:
                if(binding.pass1ResetEditText.getText().toString().isEmpty()){
                    binding.password1ResetTextInput.setError("Filed is mandatory!");

                    return;
                }
                if(binding.pass1ResetEditText.getText().toString().length()<10){
                    binding.password1ResetTextInput.setError("Password must be 10 Char Long !");
                    return;
                }

                if(binding.pass2ResetEditText.getText().toString().isEmpty()){
                    binding.password2ResetTextInput.setError("Filed is mandatory!");
                    return;
                }

                if(binding.pass1ResetEditText.getText().toString().equals(binding.pass2ResetEditText.getText().toString())){
                    binding.txtMsg.setText("Password Reset Successfully !");
                }
                else{
                    binding.password2ResetTextInput.setError("password and confirmation password do not match !");
                }

                break;
        }
    }

    private void init(View view){
        binding.btnReset.setOnClickListener(this);

        binding.password1ResetTextInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.password1ResetTextInput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });

        binding.password2ResetTextInput.getEditText().addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {

            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                binding.password2ResetTextInput.setError(null);
            }

            @Override
            public void afterTextChanged(Editable s) {

            }
        });
    }
}
