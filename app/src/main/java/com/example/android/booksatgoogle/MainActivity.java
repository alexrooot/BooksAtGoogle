package com.example.android.booksatgoogle;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.content.Loader;
import android.app.LoaderManager;
import android.app.LoaderManager.LoaderCallbacks;
import android.widget.ListAdapter;
import android.widget.ListView;
import android.widget.ProgressBar;

import java.util.List;

public class MainActivity extends AppCompatActivity implements LoaderCallbacks<List<BookConstructor>>{
    private List<BookConstructor> mdata ;
    public static String LOG_TAG = MainActivity.class.getSimpleName();
    public static String searchWorkd ="";
           // "https://www.googleapis.com/books/v1/volumes?q=airforce&filter=free-ebooks&libraryRestrict=no-restrict&maxResults=10&orderBy=relevance";



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        Log.e(LOG_TAG,"Started Main Activity");
        usertext();
        Log.e(LOG_TAG, "Set a click listener on button");


        ProgressBar mainProgress = (ProgressBar) findViewById(R.id.loading_spinner);
        mainProgress.setVisibility(View.INVISIBLE);



        
    }

    private void usertext() {
        final EditText userUIText = (EditText) findViewById(R.id.search_bar);
        final Button userUISearch = (Button) findViewById(R.id.button);


        userUISearch.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                ProgressBar visibleProgress = (ProgressBar) findViewById(R.id.loading_spinner);
                visibleProgress.setVisibility(View.VISIBLE);
                searchWorkd = userUIText.getText().toString();
                searchWorkd = "https://www.googleapis.com/books/v1/volumes?q="+searchWorkd+
                        "&filter=free-ebooks&libraryRestrict=no-restrict&maxResults=10&orderBy=relevance";

                Log.e(LOG_TAG, "we grab the search word :" + searchWorkd);
                userUIText.setText(null);
                startLoaderManager();

            }
        });

    }

    private void startLoaderManager() {
        LoaderManager loaderManager = getLoaderManager();
        loaderManager.initLoader(1, null, this);
    }


    @Override
    public Loader<List<BookConstructor>> onCreateLoader(int id, Bundle args) {
        Log.e(LOG_TAG,"Loader manger started calling on BookLoader");
       // String sample =
         //       "https://www.googleapis.com/books/v1/volumes?q=airforce&filter=free-ebooks&libraryRestrict=no-restrict&maxResults=10&orderBy=relevance";


        return new BookLoader(this, searchWorkd);
    }

    @Override
    public void onLoadFinished(Loader<List<BookConstructor>> loader, List<BookConstructor> data) {
        Log.e(LOG_TAG,"We send data out of tread");
        UI(data);


   }



    @Override
    public void onLoaderReset(Loader<List<BookConstructor>> loader) {
    //mdata.clear(); Log.e(LOG_TAG,"We have reset the loader and mdata");
    }
    public void UI(List<BookConstructor> resutls) {
        Log.e(LOG_TAG, "We are in UI method");
        ListView listView = (ListView) findViewById(R.id.list);
        Log.e(LOG_TAG, "Get Adapter");
        BookAdapter clearAdappter = (BookAdapter) listView.getAdapter();

        try {Log.e(LOG_TAG,"We are going to try and see adapter old data");
            //int listisold = clearAdappter.getCount();
           // Log.e(LOG_TAG, "we got old list with #: " + getString(listisold));
            clearAdappter.clearData();
            //clearAdappter.getCount();
            Log.e(LOG_TAG, "notify changes");
            clearAdappter.notifyDataSetChanged();
        } catch (Exception e) {
            Log.e(LOG_TAG, "could not clear");
        }



        BookAdapter bookAdapter = new BookAdapter(this,resutls);
        Log.e(LOG_TAG, "generated an adapter instance with data list");
        listView.setAdapter(bookAdapter);
        Log.e(LOG_TAG,"Started the adapter to show onto list view");
        ProgressBar doneProgress = (ProgressBar) findViewById(R.id.loading_spinner);
        doneProgress.setVisibility(View.INVISIBLE);
    }
}
