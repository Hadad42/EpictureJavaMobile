package com.java.demers_j.epicture.profile;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.java.demers_j.epicture.R;

/**
 * Created by demers_j on 23/02/2017.
 */

public class ProfileImgurFragment extends Fragment {

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View rootView = inflater.inflate(R.layout.fragment_profile_flickr, container, false);

        return rootView;
    }

}