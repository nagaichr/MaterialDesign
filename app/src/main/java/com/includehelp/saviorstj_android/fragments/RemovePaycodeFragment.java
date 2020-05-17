package com.includehelp.saviorstj_android.fragments;

import android.app.Dialog;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentRemovePaycodeBinding;

import androidx.annotation.Nullable;
import androidx.fragment.app.DialogFragment;

public class RemovePaycodeFragment extends DialogFragment implements View.OnClickListener {

    FragmentRemovePaycodeBinding binding;
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
        //View view =  inflater.inflate(R.layout.fragment_remove_paycode, container, false);

        binding = FragmentRemovePaycodeBinding.inflate(inflater,container,false);
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
            case R.id.btn_remove:
                if(binding.removePaycodeEditText.getText().toString().isEmpty()){
                    binding.removePayodeTextInput.setError("Must be Required!");
                }
                else{
                    binding.txtMessage.setText("Paycode To be Removed!! ");
                }
                break;
        }
    }

    private void init(View view){
        binding.btnRemove.setOnClickListener(this);
    }
}
