package com.example.deakyu.threadcheckformvpasynctask;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity implements MainContract.View, View.OnClickListener{

    private TextView tv;
    private Button button;
    private MainContract.Presenter presenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        tv = findViewById(R.id.tv);
        button = findViewById(R.id.button);
        button.setOnClickListener(this);
        if(presenter == null) {
            presenter = new MainPresenter();
            presenter.bind(this);
        }
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();
    }

    @Override
    public void displayMessage(String message) {
        if(message != null) tv.setText(message);
        else Toast.makeText(this, "Message Empty", Toast.LENGTH_SHORT).show();
    }

    @Override
    public void onClick(View v) {
        presenter.onButtonClicked();
    }
}
