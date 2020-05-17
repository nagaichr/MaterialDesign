package com.includehelp.saviorstj_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;

import com.includehelp.saviorstj_android.MainActivity;
import com.includehelp.saviorstj_android.R;

import androidx.fragment.app.Fragment;

public class LauncherFragment extends Fragment implements View.OnClickListener {


    Button btnCommMode;
    Button btnNetworkSetting;
    Button btnPassAuth;
    Button btnResetPass;
    Button btnRmvPaycode;
    Button btnReaderInfo;
    Button btnSettingScreen;
    Button btnHomeScreen;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        View view =  inflater.inflate(R.layout.fragment_launcher, container, false);
        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init(view);
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btn_frag_comm_mode:
                CommModeFragment commModeFragment    =  new CommModeFragment();
                ((MainActivity)getActivity()).setFragment(commModeFragment);
                break;
            case R.id.btn_frag_network_setting:
                NetworkSettingsFragments networkSettingsFragments    =  new NetworkSettingsFragments();
                ((MainActivity)getActivity()).setFragment(networkSettingsFragments);
                break;
            case R.id.btn_frag_confirm_pass:
                ConfirmPasswordFragment confirmPasswordFragment    =  new ConfirmPasswordFragment();
                ((MainActivity)getActivity()).setFragment(confirmPasswordFragment);
                break;
            case R.id.btn_frag_reset_pass:
                ResetPasswordFragment resetPasswordFragment    =  new ResetPasswordFragment();
                ((MainActivity)getActivity()).setFragment(resetPasswordFragment);
                break;
            case R.id.btn_frag_remove_paycode:
                RemovePaycodeFragment removePaycodeFragment    =  new RemovePaycodeFragment();
                ((MainActivity)getActivity()).setFragment(removePaycodeFragment);
                break;
            case R.id.btn_frag_reader_info:
                ReaderInfoFragment readerInfoFragment    =  new ReaderInfoFragment();
                ((MainActivity)getActivity()).setFragment(readerInfoFragment);
                break;
            case R.id.btn_frag_setting_screen:
                SettingScreenFragments settingScreenFragments    =  new SettingScreenFragments();
                ((MainActivity)getActivity()).setFragment(settingScreenFragments);
                break;
            case R.id.btn_frag_home_screen:
                HomeFragment homeFragment    =  new HomeFragment();
                ((MainActivity)getActivity()).setFragment(homeFragment);
                break;
        }

    }

    private void init(View view){
        btnCommMode = (view).findViewById(R.id.btn_frag_comm_mode);
        btnNetworkSetting=(view).findViewById(R.id.btn_frag_network_setting);
        btnPassAuth=(view).findViewById(R.id.btn_frag_confirm_pass);
        btnResetPass=(view).findViewById(R.id.btn_frag_reset_pass);
        btnRmvPaycode=(view).findViewById(R.id.btn_frag_remove_paycode);
        btnReaderInfo=(view).findViewById(R.id.btn_frag_reader_info);
        btnSettingScreen=(view).findViewById(R.id.btn_frag_setting_screen);
        btnHomeScreen=(view).findViewById(R.id.btn_frag_home_screen);


        btnCommMode.setOnClickListener(this);
        btnNetworkSetting.setOnClickListener(this);
        btnPassAuth.setOnClickListener(this);
        btnResetPass.setOnClickListener(this);
        btnRmvPaycode.setOnClickListener(this);
        btnReaderInfo.setOnClickListener(this);
        btnSettingScreen.setOnClickListener(this);
        btnHomeScreen.setOnClickListener(this);

    }
}
