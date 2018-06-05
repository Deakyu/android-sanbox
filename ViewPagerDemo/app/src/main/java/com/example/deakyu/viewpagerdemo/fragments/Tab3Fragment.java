package com.example.deakyu.viewpagerdemo.fragments;

import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.Toast;

import com.example.deakyu.viewpagerdemo.R;

public class Tab3Fragment extends Fragment {

    private static final String TAG = Tab3Fragment.class.getSimpleName();

    private Button tab3Btn;

    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.tab3_fragment, container, false);

        tab3Btn = view.findViewById(R.id.button_fragment3);
        tab3Btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Toast.makeText(getActivity(), TAG + " appeared!", Toast.LENGTH_SHORT).show();
            }
        });

        return view;
    }
}
