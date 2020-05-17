package com.includehelp.saviorstj_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.includehelp.saviorstj_android.MainActivity;
import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentSettingScreenBinding;
import com.includehelp.saviorstj_android.fragments.camera.CameraFragment;

import androidx.fragment.app.Fragment;

public class SettingScreenFragments extends Fragment implements View.OnClickListener {

    FragmentSettingScreenBinding binding;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_setting_screen, container, false);

        binding = FragmentSettingScreenBinding.inflate(inflater,container,false);
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
            case R.id.card_network_setting:
                CommModeFragment commModeFragment    =  new CommModeFragment();
                ((MainActivity)getActivity()).setDialogFragment(commModeFragment);

                break;
            case R.id.card_reader_info:
                ReaderInfoFragment readerInfoFragment    =  new ReaderInfoFragment();
                ((MainActivity)getActivity()).setFragment(readerInfoFragment);
                break;
            case R.id.card_reset_password:
                ResetPasswordFragment resetPasswordFragment    =  new ResetPasswordFragment();
                ((MainActivity)getActivity()).setDialogFragment(resetPasswordFragment);
                break;
            case R.id.card_remove_paycode:
                RemovePaycodeFragment removePaycodeFragment    =  new RemovePaycodeFragment();
                ((MainActivity)getActivity()).setDialogFragment(removePaycodeFragment);
                break;
            case R.id.card_enroll_user:
                EnrollUserFragment enrollUserFragment =  new EnrollUserFragment();
                ((MainActivity)getActivity()).setFragment(enrollUserFragment);
                break;
            case R.id.card_date_time:
                DataTimeFragment dataTimeFragment    =  new DataTimeFragment();
                dataTimeFragment.show(getActivity().getSupportFragmentManager(), dataTimeFragment.getTag());
                break;
            case R.id.card_live_camera:
                CameraFragment cameraFragment    =  new CameraFragment();
                ((MainActivity)getActivity()).setFragment(cameraFragment);
                break;
        }
    }


    private void init(View view) {
        binding.cardNetworkSetting.setOnClickListener(this);
        binding.cardReaderInfo.setOnClickListener(this);
        binding.cardResetPassword.setOnClickListener(this);
        binding.cardRemovePaycode.setOnClickListener(this);
        binding.cardEnrollUser.setOnClickListener(this);
        binding.cardDateTime.setOnClickListener(this);
        binding.cardLiveCamera.setOnClickListener(this);
    }
}
