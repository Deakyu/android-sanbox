package com.example.deakyu.dialogfragmentactivity;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity implements CustomDialogFragment.OnInputListener{

    private Button mOpenDialogBtn;
    private TextView mOutput;

    private String input;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        mOpenDialogBtn = findViewById(R.id.open_dialog_btn);
        mOutput = findViewById(R.id.output_message);

        mOpenDialogBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                CustomDialogFragment dialog = new CustomDialogFragment();
                dialog.show(getFragmentManager(), CustomDialogFragment.class.getSimpleName());
            }
        });
    }

    @Override
    public void sendInputToActivity(String input) {
        mOutput.setText(input);
    }
}
