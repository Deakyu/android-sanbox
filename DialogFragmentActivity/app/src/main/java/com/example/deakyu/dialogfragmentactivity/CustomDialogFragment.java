package com.example.deakyu.dialogfragmentactivity;

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

    public interface OnInputListener {
        void sendInputToActivity(String input);
    }

    public OnInputListener mOnInputListener;

    private EditText mInput;
    private TextView cancelBtn;
    private TextView okBtn;

    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        View view = inflater.inflate(R.layout.dialog_fragment, container, false);

        cancelBtn = view.findViewById(R.id.cancel_dialog_btn);
        okBtn = view.findViewById(R.id.ok_dialog_btn);
        mInput = view.findViewById(R.id.input);

        cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getDialog().dismiss();
            }
        });

        okBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String input = mInput.getText().toString();

                mOnInputListener.sendInputToActivity(input);

                getDialog().dismiss();
            }
        });

        return view;
    }

    @Override
    public void onAttach(Context context) {
        super.onAttach(context);
        try {
            mOnInputListener = (OnInputListener) getActivity();
        } catch (ClassCastException e) {
            e.printStackTrace();
        }
    }
}
