package com.java.demers_j.epicture;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.Window;
import android.view.WindowManager;
import android.view.inputmethod.EditorInfo;
import android.view.inputmethod.InputMethodManager;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.java.demers_j.epicture.Entity.Data;

import java.util.ArrayList;
import java.util.List;

public class SearchFragment extends Fragment {
    private static final String ARG_PARAM1 = "search engine";
    private EditText editText;
    private EditText editPassword;
    private EditText editUsername;
    private ImageView imageLogo;
    private int searchEngine;
    private Dialog dialog;
    private RecyclerView recyclerView;

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

        recyclerView = (RecyclerView) view.findViewById(R.id.recycler_view);
        recyclerView.setHasFixedSize(true);
        StaggeredGridLayoutManager gaggeredGridLayoutManager;
        gaggeredGridLayoutManager = new StaggeredGridLayoutManager(2, 1);
        recyclerView.setLayoutManager(gaggeredGridLayoutManager);


        imageLogo = (ImageView) view.findViewById(R.id.imageViewSearch);
        MainActivity activity = (MainActivity) getActivity();
        if (searchEngine == R.integer.Flickr) {
            imageLogo.setImageResource(R.drawable.flickr_logo);
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(getString(R.string.flickr_title_menu));
        } else if (searchEngine == R.integer.Imgur) {
            imageLogo.setImageResource(R.drawable.imgur_logo);
            if (activity.getSupportActionBar() != null)
                activity.getSupportActionBar().setTitle(getString(R.string.imgur_title_menu));
        }

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
        ImageButton button = (ImageButton) view.findViewById(R.id.buttonSearch);
        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                launch_search();
            }
        });
        return view;
    }

    private void launch_search() {
        String str = editText.getText().toString();
        Data data = new Data(getContext());

        imageLogo.setVisibility(View.GONE);
        try {
            MainActivity activity = (MainActivity) getActivity();
            InputMethodManager imm = (InputMethodManager) activity.getSystemService(Context.INPUT_METHOD_SERVICE);
            if (activity.getCurrentFocus() != null)
                imm.hideSoftInputFromWindow(activity.getCurrentFocus().getWindowToken(), 0);
        } catch (Exception e) {
            e.printStackTrace();
        }

        if (searchEngine == R.integer.Flickr && data.getUserFlickr() != null && data.getUserFlickrConnected()) {
            if (str.length() > 0) {
                Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();


                List<String> tmp = new ArrayList<>();
                tmp.add("0");
                tmp.add("1");
                tmp.add("2");
                tmp.add("3");
                tmp.add("4");
                tmp.add("5");
                tmp.add("6");
                tmp.add("7");
                tmp.add("8");
                tmp.add("9");
                tmp.add("10");
                tmp.add("11");
                tmp.add("12");
                tmp.add("13");
                tmp.add("14");
                tmp.add("15");
                RecyclerViewAdapter rcAdapter = new RecyclerViewAdapter(getContext(), tmp);
                recyclerView.setAdapter(rcAdapter);
            }
        } else if (searchEngine == R.integer.Imgur && data.getUserImgur() != null && data.getUserImgurConnected()) {
            if (str.length() > 0) {
                Toast.makeText(getContext(), str, Toast.LENGTH_LONG).show();
            }
        } else
            loginDialog();

    }

    private void loginDialog() {
        if (dialog != null)
            return;
        dialog = new Dialog(getContext());
        dialog.requestWindowFeature(Window.FEATURE_NO_TITLE);
        dialog.setContentView(R.layout.dialog_box_login);

        dialog.setCancelable(true);
        WindowManager.LayoutParams lp = new WindowManager.LayoutParams();
        try {
            if (dialog.getWindow() != null)
                lp.copyFrom(dialog.getWindow().getAttributes());
        } catch (NullPointerException e) {
            e.printStackTrace();
        }
        lp.width = WindowManager.LayoutParams.MATCH_PARENT;
        dialog.show();
        dialog.getWindow().setAttributes(lp);
        Button loginButton = (Button) dialog.findViewById(R.id.login_button);
        editPassword = (EditText) dialog.findViewById(R.id.password);
        editUsername = (EditText) dialog.findViewById(R.id.textViewUserName);
        loginButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Data data = new Data(getContext());
                if (editUsername != null && editPassword != null && editUsername.getText().toString().length() > 0 && editPassword.getText().toString().length() > 0) {
                    if (searchEngine == R.integer.Flickr)
                        data.NewFlickrUser(editUsername.getText().toString(), editPassword.getText().toString());
                    else if (searchEngine == R.integer.Imgur)
                        data.NewImgurUser(editUsername.getText().toString(), editPassword.getText().toString());
                    launch_search();
                    dialog.dismiss();
                }
            }
        });
    }
}
