package com.example.deakyu.refactortomvvm;

import android.content.Context;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.Parcelable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;
import android.widget.Toast;

import com.example.deakyu.refactortomvvm.model.Book;
import com.example.deakyu.refactortomvvm.model.BookSearchResult;

import java.util.List;

import rx.Observer;
import rx.android.schedulers.AndroidSchedulers;
import rx.subscriptions.CompositeSubscription;

public class MainActivity extends AppCompatActivity {

    private EditText editText;
    private ImageButton searchBtn;
    private RecyclerView recyclerView;
    private BookListAdapter adapter;
    private TextView noDataFoundText;
    public static final String SEARCH_RESULT = "booksSearchResult";
    private CompositeSubscription subscriptions = new CompositeSubscription();
    private BooksViewModel booksViewModel;
    private List<Book> currentBooks;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        booksViewModel = new BooksViewModel(new BooksInteractorImple(), AndroidSchedulers.mainThread());

        editText = findViewById(R.id.editText);
        searchBtn = findViewById(R.id.search_button);
        recyclerView = findViewById(R.id.recycler_view);
        noDataFoundText = findViewById(R.id.text_no_data_found);

        adapter = new BookListAdapter(this);
        recyclerView.setAdapter(adapter);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));

        searchBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(isInternetAvailable()) {
                    performSearch();
                } else {
                    Toast.makeText(MainActivity.this, "Connection Error", Toast.LENGTH_SHORT).show();
                }
            }
        });

        if(savedInstanceState != null) {
            Book[] books = (Book[]) savedInstanceState.getParcelableArray(SEARCH_RESULT);
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        subscriptions.unsubscribe();
    }

    private void performSearch() {
        String formatUserInput = getUserInput().trim().replaceAll("\\s+", "+");
        subscriptions.add(booksViewModel.search(formatUserInput)
            .subscribe(new Observer<BookSearchResult>() {
                @Override
                public void onCompleted() {

                }

                @Override
                public void onError(Throwable e) {
                    e.printStackTrace();
                }

                @Override
                public void onNext(BookSearchResult bookSearchResult) {
                    updateUi(bookSearchResult.getBooks());
                    currentBooks = bookSearchResult.getBooks();
                }
            }));
    }

    private boolean isInternetAvailable() {
        ConnectivityManager cm = (ConnectivityManager) getSystemService(Context.CONNECTIVITY_SERVICE);
        NetworkInfo activeNetwork = cm.getActiveNetworkInfo();
        return activeNetwork.isConnectedOrConnecting();
    }

    private void updateUi(List<Book> books) {
        if(books.isEmpty()) {
            noDataFoundText.setVisibility(View.VISIBLE);
        } else {
            noDataFoundText.setVisibility(View.GONE);
        }
        adapter.setBooks(books);
    }

    private String getUserInput() {
        return editText.getText().toString();
    }

    @Override
    protected void onSaveInstanceState(Bundle outState) {
        super.onSaveInstanceState(outState);
        Book[] books = new Book[adapter.getItemCount()];
        if(currentBooks != null) {
            for(int i=0 ; i < books.length ; i++) {
                books[i] = currentBooks.get(i);
            }
        }
        outState.putParcelableArray(SEARCH_RESULT, books);
    }
}
