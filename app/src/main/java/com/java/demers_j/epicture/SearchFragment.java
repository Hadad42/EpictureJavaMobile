package com.java.demers_j.epicture;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.inputmethod.EditorInfo;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "search engine";
    private EditText editText;
    private int searchEngine;

    public SearchFragment() {
        // Required empty public constructor
    }

    /**
     * Use this factory method to create a new instance of
     * this fragment using the provided parameters.
     *
     * @param param1 Parameter 1.
     * @return A new instance of fragment BlankFragment.
     */
    public static SearchFragment newInstance(int param1) {
        SearchFragment myFragment = new SearchFragment();

        Bundle args = new Bundle();
        args.putInt(ARG_PARAM1, param1);
        myFragment.setArguments(args);

        return myFragment;
    }


    @Override
    public void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        if (getArguments() != null) {
            searchEngine = getArguments().getInt(ARG_PARAM1);
        }
    }

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View view = inflater.inflate(R.layout.fragment_search, container, false);

        editText = (EditText) view.findViewById(R.id.searchEditText);

        ImageView imageLogo = (ImageView) view.findViewById(R.id.imageViewSearch);
        if (searchEngine == R.integer.Flickr)
            imageLogo.setImageResource(R.drawable.flickr_logo);
        else if (searchEngine == R.integer.Imgur)
            imageLogo.setImageResource(R.drawable.imgur_logo);


        editText.setOnEditorActionListener(new TextView.OnEditorActionListener() {
                    @Override
                    public boolean onEditorAction(TextView v, int actionId,
                                                  KeyEvent event) {
                        if (actionId == EditorInfo.IME_ACTION_SEARCH) {
                            launch_search();
                        }
                        return false;
                    }
                });
        ImageButton button = (ImageButton) view.findViewById(R.id.buttonSeach);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch_search();
            }
        });
        return view;
    }

    private void launch_search()
    {
        String str = editText.getText().toString();
        if (str.length() > 0) {
            Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
        }
    }
}
