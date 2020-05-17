package com.includehelp.saviorstj_android.fragments;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.bumptech.glide.Glide;
import com.includehelp.saviorstj_android.MainActivity;
import com.includehelp.saviorstj_android.R;
import com.includehelp.saviorstj_android.databinding.FragmentHomeBinding;

import androidx.fragment.app.Fragment;

public class HomeFragment extends Fragment implements View.OnClickListener {
    FragmentHomeBinding binding;


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {

        // Inflate the layout for this fragment
        //View view =  inflater.inflate(R.layout.fragment_home, container, false);

        //View binding implementation
        binding = FragmentHomeBinding.inflate(inflater, container, false);
        View view = binding.getRoot();

        return view;
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState) {
        init(view);

        //customization
        binding.clock
                .setDiameterInDp(256.0F)
                .setOpacity(16.0f)
                .setShowSeconds(true)
                .setColor(getActivity().getColor(R.color.colorAccent));

        Glide.with(getActivity())
                .load(R.drawable.finger_scan)
                .into(binding.fingerImageView);
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        binding = null;
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()){
            case R.id.btnSetting:
                ConfirmPasswordFragment confirmPasswordFragment    =  new ConfirmPasswordFragment();
                ((MainActivity)getActivity()).setDialogFragment(confirmPasswordFragment);
                break;
            }
    }

    private void init(View view){

        binding.btnSetting.setOnClickListener(this);
    }
}
