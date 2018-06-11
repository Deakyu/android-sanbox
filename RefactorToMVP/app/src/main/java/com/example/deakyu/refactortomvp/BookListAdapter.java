package com.example.deakyu.refactortomvp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.constraint.ConstraintLayout;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class BookListAdapter extends RecyclerView.Adapter<BookListAdapter.ViewHolder> {

    public class ViewHolder extends RecyclerView.ViewHolder {
        public ConstraintLayout row;
        public TextView title;
        public TextView author;

        public ViewHolder(View iv) {
            super(iv);

            row = iv.findViewById(R.id.book_row);
            title = iv.findViewById(R.id.title);
            author = iv.findViewById(R.id.author);
        }
    }

    private final LayoutInflater inflater;
    private List<Book> books;
    private Context c;

    public BookListAdapter(Context c) {
        this.inflater = LayoutInflater.from(c);
        this.c = c;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = inflater.inflate(R.layout.book_row, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder vh, int pos) {
        Book curBook = books.get(pos);

        vh.title.setText(curBook.getTitle());
        vh.author.setText(curBook.getAuthor());
    }

    public void setBooks(List<Book> books) {
        this.books = books;
        notifyDataSetChanged();
    }

    @Override
    public int getItemCount() {
        return books != null ? books.size() : 0;
    }
}