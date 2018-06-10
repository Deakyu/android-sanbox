package com.example.deakyu.lifecycledemo;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private TextView textView;
    private Button button;

    // LifeCycles are in order

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        editText = findViewById(R.id.edit_text);
        textView = findViewById(R.id.text_view);
        button = findViewById(R.id.button);

        button.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String value = editText.getText().toString();
                if(value != null) {
                    textView.setText(value);
                } else {
                    textView.setText("Value is null!");
                }
            }
        });

        System.out.println("DEE onCreate");
    }

    @Override
    protected void onStart() {
        super.onStart();

        System.out.println("DEE onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);

        String previousText = savedInstanceState.getString("currentText");
        textView.setText(previousText);

        System.out.println("DEE onRestoreInstanceState");
    }

    @Override
    protected void onResume() {
        super.onResume();

        System.out.println("DEE onResume");
    }

    @Override
    protected void onPause() {
        super.onPause();

        System.out.println("DEE onPause");
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);

        String currentText = textView.getText().toString();
        outState.putString("currentText", currentText);

        System.out.println("DEE onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();

        System.out.println("DEE onStop");
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();

        System.out.println("DEE onDestroy");
    }
}
