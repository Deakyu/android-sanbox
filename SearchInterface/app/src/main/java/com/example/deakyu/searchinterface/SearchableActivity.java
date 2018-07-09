package com.example.deakyu.searchinterface;

import android.app.SearchManager;
import android.content.Intent;
import android.provider.SearchRecentSuggestions;
import android.support.annotation.Nullable;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.widget.Toast;

public class SearchableActivity extends AppCompatActivity {

    SearchRecentSuggestions suggestions;

    // region Case 1: Creating new instance of this activity over and over (Not recommended)
    //    @Override
    //    protected void onCreate(Bundle savedInstanceState) {
    //        super.onCreate(savedInstanceState);
    //        setContentView(R.layout.activity_searchable);
    //
    //        // Get the intent, verify the action and get the query
    //        Intent intent = getIntent();
    //        String action = intent.getAction();
    //        if(action != null && action.equals(Intent.ACTION_SEARCH)) {
    //            String query = intent.getStringExtra(SearchManager.QUERY);
    //            doMySearch(query);
    //        }
    //    }
    // endregion

    // region Case 2: Setting the launchMode to singleTop to reuse the activity when search performed
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_searchable);

        suggestions = new SearchRecentSuggestions(this, MySuggestionsProvider.AUTHORITY,
                MySuggestionsProvider.MODE);

        handleIntent(getIntent());
    }

    @Override
    protected void onNewIntent(Intent intent) {
        setIntent(intent);
        handleIntent(intent);
    }

    private void handleIntent(Intent intent) {
        String action = intent.getAction();
        if(action != null && action.equals(Intent.ACTION_SEARCH)) {
            String query = intent.getStringExtra(SearchManager.QUERY);
            doMySearch(query);
        }
    }
    // endregion

    private void doMySearch(String query) {
        suggestions.saveRecentQuery(query, null);
        Toast.makeText(SearchableActivity.this, query + " searched", Toast.LENGTH_SHORT).show();
    }

    private void clearHistory() {
        suggestions.clearHistory();
    }
}
