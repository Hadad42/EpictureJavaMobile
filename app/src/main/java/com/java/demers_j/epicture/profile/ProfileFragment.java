package com.java.demers_j.epicture.profile;

import android.os.Bundle;
import android.os.Handler;
import android.support.v4.app.Fragment;
import android.support.v4.view.ViewPager;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.astuetz.PagerSlidingTabStrip;
import com.java.demers_j.epicture.MainActivity;
import com.java.demers_j.epicture.R;

public class ProfileFragment extends Fragment {

    ProfileAdapter adapter;
    View view;

    private Handler handler;
    private Runnable timedTask = new Runnable() {
        @Override
        public void run() {
            handler.postDelayed(timedTask, 2000);
            onResume();
        }
    };

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        view = inflater.inflate(R.layout.fragment_profile, container, false);

        MainActivity activity = (MainActivity) getActivity();
        if (activity.getMenu() != null)
            activity.getMenu().clear();
        if (activity.getSupportActionBar() != null)
            activity.getSupportActionBar().setTitle(R.string.action_profile);
        if (adapter == null)
            adapter = new ProfileAdapter(getActivity().getSupportFragmentManager());
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        return view;
    }


    @Override
    public void onDetach() {
        super.onDetach();
    }

    @Override
    public void onResume() {
        super.onResume();
        ViewPager pager = (ViewPager) view.findViewById(R.id.pager);
        pager.setAdapter(adapter);
        PagerSlidingTabStrip tabs = (PagerSlidingTabStrip) view.findViewById(R.id.tabs);
        tabs.setViewPager(pager);
        if (handler == null) {
            handler = new Handler();
            handler.post(timedTask);
        } else
            handler.removeCallbacks(timedTask);
    }
}
