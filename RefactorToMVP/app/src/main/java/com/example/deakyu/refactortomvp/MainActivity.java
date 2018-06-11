package com.example.deakyu.refactortomvp;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import java.util.List;

public class MainActivity extends AppCompatActivity implements BooksView {

    private BooksInteractor interactor;
    private BooksPresenter presenter;
    private EditText userInput;
    private Button searchButton;
    private RecyclerView recyclerView;
    private BookListAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        System.out.println("DEE onCreate");

        setRecyclerView();
        setPresenter();
        setUi();
    }

    private void setRecyclerView() {
        adapter = new BookListAdapter(this);
        recyclerView = findViewById(R.id.recycler_view);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
    }

    private void setPresenter() {
        interactor = new BooksInteractorImpl();
        presenter = (BooksPresenter)getLastCustomNonConfigurationInstance();
        if(presenter == null) {
            presenter = new BooksPresenterImpl(interactor);
        }
        presenter.bind(this);
    }

    private void setUi() {
        userInput = findViewById(R.id.user_input);
        searchButton = findViewById(R.id.search_button);
        searchButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                presenter.performSearch(getUserInput());
            }
        });
    }

    private String getUserInput() {
        return userInput.getText().toString();
    }

    @Override
    public void updateUi(List<Book> books) {
        adapter.setBooks(books);
    }

    @Override
    protected void onStart() {
        super.onStart();
        System.out.println("DEE onStart");
    }

    @Override
    protected void onRestoreInstanceState(Bundle savedInstanceState) {
        super.onRestoreInstanceState(savedInstanceState);
        if(adapter != null && presenter != null && presenter.getCurrentBookList() != null) {
            adapter.setBooks(presenter.getCurrentBookList());
        }
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
        System.out.println("DEE onSaveInstanceState");
    }

    @Override
    protected void onStop() {
        super.onStop();
        System.out.println("DEE onStop");
    }

    @Override
    protected void onDestroy() {
        presenter.unbind();
        super.onDestroy();

        System.out.println("DEE onDestroy");
    }

    @Override
    public Object onRetainCustomNonConfigurationInstance() {
        return presenter;
    }
}
