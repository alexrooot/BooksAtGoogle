package com.example.android.booksatgoogle;

import android.content.Context;
import android.content.Intent;
import android.graphics.Typeface;
import android.net.Uri;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.TextView;

import java.util.List;

public class BookAdapter extends ArrayAdapter<BookConstructor> {

    final String LOG_TAG = BookAdapter.class.getSimpleName();
    private List<BookConstructor> data;
    public BookAdapter(Context context, List<BookConstructor> bookConstructors) {
        super(context, 0,bookConstructors);
        Log.v(LOG_TAG, "We are in the adapter");
        data = bookConstructors;
    }

    @Override
    public int getCount() {
        return data.size();
    }

    public void clearData() {
        //clear data
        Log.e(LOG_TAG, "Success clearing data");
        data.clear();
        Log.e(LOG_TAG, "size of data");
        int size = data.size();
        Log.e(LOG_TAG, String.valueOf(size));
        notifyDataSetChanged();
        Log.e(LOG_TAG, "Notify the system of changes");
    }

    @Override
    public View getView(int position, View convertView,  ViewGroup parent) {
        Log.e(LOG_TAG,"inflating recycler");
        View listItemView = convertView;
        if (listItemView == null){
            listItemView = LayoutInflater.from(getContext()).inflate(
                    R.layout.book_list,parent,false);
        }
        final BookConstructor currentBook = getItem(position);
        Log.e(LOG_TAG,position+": We just got book object");
        TextView titleview = listItemView.findViewById(R.id.title);
        TextView authorview = listItemView.findViewById(R.id.author);
        TextView priceview = listItemView.findViewById(R.id.price);
        TextView downview = listItemView.findViewById(R.id.down);


        titleview.setText(currentBook.getmBookTitel());
        authorview.setText(currentBook.getmBookAuthor());
        priceview.setText(currentBook.getmBookPrice());
        Log.e(LOG_TAG,"Position: "+position+" set list view with string data");
        if (currentBook.getmBookURL() != null){
            downview.setTypeface(null, Typeface.BOLD);//make the text bold from java
            downview.setText("⇩");


            downview.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    String tempUrl = currentBook.getmBookURL();
                    Intent openBrowser = new Intent(Intent.ACTION_VIEW);
                    openBrowser.setData(Uri.parse(tempUrl));
                    getContext().startActivity(openBrowser);
                }
            });
        }else{
            downview.setVisibility(View.GONE);
        }

        return listItemView;
    }

}
