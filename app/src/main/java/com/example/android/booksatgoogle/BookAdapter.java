package com.example.android.booksatgoogle;

import android.content.Context;
import android.graphics.Typeface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<BookConstructor> {

    final String LOG_TAG = BookAdapter.class.getSimpleName();

    public BookAdapter(Context context, List<BookConstructor> bookConstructors) {
        super(context, 0,bookConstructors);
        Log.v(LOG_TAG, "We are in the adapter");
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Log.e(LOG_TAG,"inflating recycler");
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(R.layout.book_list,parent,false);
        }
        BookConstructor currentBook = getItem(position);
        Log.e(LOG_TAG,"We just got book object"+position);
        TextView titleview = (TextView) listItemView.findViewById(R.id.title);
        TextView authorview =(TextView) listItemView.findViewById(R.id.author);
        TextView priceview = (TextView) listItemView.findViewById(R.id.price);
        TextView downview = (TextView) listItemView.findViewById(R.id.down);
        titleview.setText(currentBook.getmBookTitel);
        authorview.setText(currentBook.getmBookAuthor);
        priceview.setText(currentBook.getmBookPrice);
        Log.e(LOG_TAG,"Position: "+position+" set list view with string data");
        if (currentBook.getmBookURL != null){
            downview.setTypeface(null, Typeface.BOLD);//make the text bold from java
            downview.setText("↓");
        }
        return listItemView;
    }
}
