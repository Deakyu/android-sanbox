package com.example.deakyu.searchinterface;

import android.content.SearchRecentSuggestionsProvider;

public class MySuggestionsProvider extends SearchRecentSuggestionsProvider {
    public static final String AUTHORITY = "com.example.deakyu.searchinterface.MySuggestionsProvider";
    public static final int MODE = DATABASE_MODE_QUERIES;

    public MySuggestionsProvider() {
        setupSuggestions(AUTHORITY, MODE);
    }
}
