package com.example.deakyu.fragmenttofragment;

import android.app.DialogFragment;
import android.content.Context;
import android.os.Bundle;
import android.support.annotation.Nullable;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.TextView;

public class CustomDialogFragment extends DialogFragment {

    public interface OnItemClicked {
        void sendInputToFragment(String input);
    }

    private EditText mInput;
    private TextView mOkBtn, mCancelBtn;
    public OnItemClicked mOnItemClicked;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.fragment_dialog, container, false);

        mInput = view.findViewById(R.id.input);
        mOkBtn = view.findViewById(R.id.ok_dialog_btn);
        mCancelBtn = view.findViewById(R.id.cancel_dialog_btn);

        mCancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        mOkBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mInput.getText().toString();

                if(!input.equals("")) {
                    mOnItemClicked.sendInputToFragment(input);
                }

                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnItemClicked = (OnItemClicked) getTargetFragment();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
