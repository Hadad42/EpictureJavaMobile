package com.java.demers_j.epicture;


import android.content.res.Configuration;
import android.content.res.Resources;
import android.os.Build;
import android.os.Bundle;
import android.os.LocaleList;
import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentTransaction;
import android.util.DisplayMetrics;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Spinner;

import com.java.demers_j.epicture.Entity.Settings;

import java.util.Locale;


/**
 * A simple {@link Fragment} subclass.
 */
public class SettingsFragment extends Fragment {
    private Settings settings;

    public SettingsFragment() {
        // Required empty public constructor
    }


    @SuppressWarnings("deprecation")
    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_settings, container, false);
        MainActivity activity = (MainActivity) getActivity();

        if (activity.getMenu() != null) {
            activity.getMenu().clear();
        }
        if (activity.getSupportActionBar() != null)
            activity.getSupportActionBar().setTitle(getString(R.string.action_settings));
        if (settings == null)
            settings = new Settings(getContext());
        final Spinner spinnerLanguage = (Spinner) view.findViewById(R.id.languageSpinner);
        ArrayAdapter spinnerArrayAdapter2 = ArrayAdapter.createFromResource(getContext(), R.array.language, android.R.layout.simple_spinner_item);
        spinnerArrayAdapter2.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item); // The drop down view
        spinnerLanguage.setAdapter(spinnerArrayAdapter2);
        if (settings.getLanguage().equals("fr"))
            spinnerLanguage.setSelection(1);
        spinnerLanguage.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                if (parent.getSelectedItemId() == 0 && settings.getLanguage().equals("en") ||
                        parent.getSelectedItemId() == 1 && settings.getLanguage().equals("fr")) {
                    return;
                }
                Configuration conf = new Configuration(getResources().getConfiguration());
                LocaleList localeList;
                if (parent.getSelectedItemId() == 0)
                    settings.setLanguage("en");
                else if (parent.getSelectedItemId() == 1)
                    settings.setLanguage("fr");
                if (Build.VERSION.SDK_INT >= 24) {
                    localeList = conf.getLocales();
                    System.out.println("LocalList = " + localeList);
                } else {
                    Resources res = getResources();
                    DisplayMetrics dm = res.getDisplayMetrics();
                    conf.locale = new Locale(settings.getLanguage());
                    res.updateConfiguration(conf, dm);
                }
                settings.saveSettingsToPrefs();
                FragmentTransaction ft = getFragmentManager().beginTransaction();
                ft.detach(SettingsFragment.this).attach(SettingsFragment.this).commit();
            }

            @Override
            public void onNothingSelected(AdapterView<?> parent) {

            }
        });
        return view;
    }

}
