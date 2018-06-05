package com.example.deakyu.fragmenttofragment;

import android.app.Fragment;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

public class MainFragment extends Fragment implements CustomDialogFragment.OnItemClicked{

    private Button mOpenDialog;
    private TextView mOutput;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_main, container, false);

        mOpenDialog = view.findViewById(R.id.open_dialog_btn);
        mOutput = view.findViewById(R.id.output_message);

        mOpenDialog.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                CustomDialogFragment dialog = new CustomDialogFragment();
                dialog.setTargetFragment(MainFragment.this, 1);
                dialog.show(getFragmentManager(), CustomDialogFragment.class.getSimpleName());

            }
        });

        return view;
    }

    @Override
    public void sendInputToFragment(String input) {
        mOutput.setText(input);
    }
}
