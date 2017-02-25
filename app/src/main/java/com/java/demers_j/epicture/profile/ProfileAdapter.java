package com.java.demers_j.epicture.profile;


import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentPagerAdapter;

class ProfileAdapter extends FragmentPagerAdapter {
    private ProfileFlickrFragment profileFlickrFragment;
    private ProfileImgurFragment profileImgurFragment;

    ProfileAdapter(FragmentManager fm) {
        super(fm);
    }

    @Override
    public int getCount() {
        return 2;
    }

    @Override
    public Fragment getItem(int position) {
        switch (position) {
            case 0:
                if (profileFlickrFragment == null)
                    profileFlickrFragment = new ProfileFlickrFragment();
                return profileFlickrFragment;
            case 1:
                if (profileImgurFragment == null)
                    profileImgurFragment = new ProfileImgurFragment();
                return profileImgurFragment;
            default:
                return null;
        }
    }

    private final String[] titles = {"Flickr", "Imgur"};

    @Override
    public CharSequence getPageTitle(int position) {
        switch (position) {
            case 0:
                return titles[0];
            case 1:
                return titles[1];
        }
        return "";
    }
}
